package ru.sber.qa

import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.random.Random

internal class CertificateTest {

    private val certificateRequest = mockk<CertificateRequest>(relaxed = true)
    private val processedBy = 246L
    private val data = Random.Default.nextBytes(100)
    private val certificate = Certificate(certificateRequest, processedBy, data)

    @Test
    fun getCertificateRequest() {
        assertEquals(certificateRequest, certificate.certificateRequest)
    }

    @Test
    fun getProcessedBy() {
        assertEquals(processedBy, certificate.processedBy)
    }

    @Test
    fun getData() {
        assertEquals(data, certificate.data)
    }
}