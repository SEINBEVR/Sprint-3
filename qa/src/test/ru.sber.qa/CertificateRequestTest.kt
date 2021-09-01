package ru.sber.qa

import io.mockk.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.random.Random

internal class CertificateRequestTest {
    private val employeeNumber = 246L
    private val certificateType = mockk<CertificateType>(relaxed = true)
    private val certificateRequest = CertificateRequest(employeeNumber, certificateType)
    private val hrEmployeeNumber = 135L
    private val data = Random.nextBytes(100)

    @Test
    fun process() {
        mockkConstructor(Certificate::class)
        mockkObject(Scanner)
        every { Scanner.getScanData() } returns data

        val certificate = certificateRequest.process(hrEmployeeNumber)

        assertEquals(data, certificate.data)
        assertEquals(hrEmployeeNumber, certificate.processedBy)
        assertEquals(certificateRequest, certificate.certificateRequest)

        unmockkAll()
    }

    @Test
    fun getEmployeeNumber() {
        assertEquals(employeeNumber, certificateRequest.employeeNumber)
    }

    @Test
    fun getCertificateType() {
        assertEquals(certificateType, certificateRequest.certificateType)
    }
}