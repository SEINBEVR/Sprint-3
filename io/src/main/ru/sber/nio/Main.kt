package ru.sber.nio

import org.apache.commons.io.FileUtils
import java.io.File

fun main(args: Array<String>) {
    val grep = Grep()
    grep.find("22/Jan/2001:14:27:46")
}
