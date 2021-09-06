package ru.sber.io

import io.mockk.mockkClass
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.apache.commons.io.FileUtils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class ArchivatorTest{

    @Test
    fun compareOriginalAndUnzippedLog() {
        val archivator = Archivator()
        mockkObject(archivator)
        val originalFileName = File("logfile.log")
        archivator.zipLogfile(originalFileName.path, "zippedLogfile.zip")
        archivator.unzipLogfile("zippedLogfile.zip", "unzippedlogfile.log")
        assertEquals(FileUtils.readLines(originalFileName), FileUtils.readLines(File("unzippedlogfile.log")))
        unmockkAll()
    }

}