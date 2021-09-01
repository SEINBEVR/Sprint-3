package ru.sber.qa

import ru.sber.qa.Certificate
import ru.sber.qa.CertificateType
import ru.sber.qa.Scanner

/**
 * Запрос на изготовление справки.
 */
class CertificateRequest(
    val employeeNumber: Long,
    val certificateType: CertificateType,
) {

    fun process(hrEmployeeNumber: Long): Certificate =
        Certificate(certificateRequest = this, processedBy = hrEmployeeNumber, data = Scanner.getScanData())
}
