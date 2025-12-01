package utils

import java.io.BufferedReader
import java.io.File

fun getInputAsString(day: Int, useSampleInput: Boolean): String = fileReader(day, useSampleInput).readText()

fun getInputAsList(day: Int, useSampleInput: Boolean): List<String> = fileReader(day, useSampleInput).readLines()

private fun fileReader(day: Int, useSampleInput: Boolean): BufferedReader =
    (if (useSampleInput) "sample_input" else "input").run {
        File("src/main/kotlin/day$day/$this").bufferedReader()
    }