package ru.sber.io

import java.io.*
import java.util.zip.*

/**
 * Реализовать методы архивации и разархивации файла.
 * Для реализиации использовать ZipInputStream и ZipOutputStream.
 */
class Archivator {

    /**
     * Метод, который архивирует файл logfile.log в архив logfile.zip.
     * Архив должен располагаться в том же каталоге, что и исходной файл.
     */
    fun zipLogfile(originalFileName: String, zippedFileName: String) {
        FileInputStream(originalFileName).use { input ->
            ZipOutputStream(FileOutputStream(zippedFileName)).use { output ->
                try {
                    val entry = ZipEntry(originalFileName)
                    output.putNextEntry(entry)
                    val buffer = ByteArray(input.available())
                    input.read(buffer)
                    output.write(buffer)
                    output.closeEntry()
                } catch(e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    /**
     * Метод, который извлекает файл из архива.
     * Извлечь из архива logfile.zip файл и сохарнить его в том же каталоге с именем unzippedLogfile.log
     */
    fun unzipLogfile(zippedFileName: String, unzippedFileName: String) {
                ZipInputStream(FileInputStream(zippedFileName)).use { input ->
                    FileOutputStream(unzippedFileName).use { output ->
                        try {
                            while (input.nextEntry != null) {
                                var readInfo = input.read()
                                while (readInfo != -1) {
                                    output.write(readInfo)
                                    readInfo = input.read()
                                }
                                output.flush()
                                input.closeEntry()
                            }
                        } catch(e: Exception) {
                            e.printStackTrace()
                        }
                }
            }
    }
}