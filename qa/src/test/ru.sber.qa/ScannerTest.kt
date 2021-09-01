package ru.sber.qa

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.random.Random

internal class ScannerTest {

    @Test
    fun getScanDataException() {
        mockkObject(Random)
        every { Random.nextLong(5000L, 15000L) } returns Scanner.SCAN_TIMEOUT_THRESHOLD + 1

        assertThrows(
            ScanTimeoutException::class.java,
            { Scanner.getScanData() },
            "With random = 10 001L always throws ScanTimeoutException"
        )

        unmockkAll()
    }

    @Test
    fun getScanData() {
        mockkObject(Random)
        val data = Random.nextBytes(100)

        every { Random.nextLong(5000L, 15000L) } returns 5000L
        every { Random.nextBytes(100) } returns data

        assertEquals(data, Scanner.getScanData())

        unmockkAll()
    }
}