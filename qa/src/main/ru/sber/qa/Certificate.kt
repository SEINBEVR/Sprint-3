package ru.sber.qa

import ru.sber.qa.CertificateRequest

/**
 * Готовая справка.
 */
class Certificate(
    val certificateRequest: CertificateRequest,
    val processedBy: Long,
    val data: ByteArray,
) {

}
