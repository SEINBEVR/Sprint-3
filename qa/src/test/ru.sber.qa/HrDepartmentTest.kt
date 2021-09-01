package ru.sber.qa

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset


internal class HrDepartmentTest {
    private val certificate = mockk<Certificate>()
    private val certificateRequest = mockk<CertificateRequest>()
    private val hrEmployeeNumber = 135L

    @BeforeEach
    fun setUp() {
        mockkObject(CertificateType.NDFL)
        mockkObject(CertificateType.LABOUR_BOOK)
    }

    @Test
    fun receiveRequestDoesNotThrow() {
        //given
        HrDepartment.clock = Clock.fixed(
            Instant.parse("2021-09-01T20:00:03Z"), ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.NDFL

        //then
        assertDoesNotThrow({HrDepartment.receiveRequest(certificateRequest)})
    }

    @Test
    fun processNextRequestDoesNotThrow() {
        //given
        HrDepartment.clock = Clock.fixed(
            Instant.parse("2021-09-01T20:00:03Z"), ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.NDFL
        every { certificateRequest.process(hrEmployeeNumber) } returns certificate

        //when
        HrDepartment.receiveRequest(certificateRequest)

        //then
        assertDoesNotThrow({HrDepartment.processNextRequest(hrEmployeeNumber)})
    }

    @Test
    fun recieveRequestThrowsOnWeekEnd() {
        //given SUNDAY
        HrDepartment.clock = Clock.fixed(
            Instant.parse("2021-09-05T10:00:00Z"), ZoneOffset.UTC)

        every { certificateRequest.certificateType } returns CertificateType.NDFL

        //then
        assertThrows(
            WeekendDayException::class.java,
            {HrDepartment.receiveRequest(certificateRequest)},
            "Must throw on any day off"
        )
    }

    @Test
    fun recieveRequestThrowsOnEvenDays() {
        //given THURSDAY
        HrDepartment.clock = Clock.fixed(
            Instant.parse("2021-09-02T10:00:00Z"), ZoneOffset.UTC)

        every { certificateRequest.certificateType } returns CertificateType.NDFL

        //then
        assertThrows(
            NotAllowReceiveRequestException::class.java,
            {HrDepartment.receiveRequest(certificateRequest)},
            "Must throw on any even days"
        )
    }

    @Test
    fun recieveRequestThrowsOnOddDays() {
        //given WEDNESDAY
        HrDepartment.clock = Clock.fixed(
            Instant.parse("2021-09-01T10:00:00Z"), ZoneOffset.UTC)

        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK

        //then
        assertThrows(
            NotAllowReceiveRequestException::class.java,
            {HrDepartment.receiveRequest(certificateRequest)},
            "Must throw on any odd days"
        )
    }

    @AfterEach
    fun unMockkAll() {
        unmockkAll()
    }
}