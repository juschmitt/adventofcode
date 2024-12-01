package utils

import java.io.BufferedReader
import java.io.File

abstract class Day(
    private val dayNumber: Int,
    private val year: Int,
    private val useSampleInput: Boolean = true
) {
    val inputList: List<String> by lazy {
        InputReader.getInputAsList(dayNumber, year, useSampleInput)
    }
    val inputString: String by lazy {
        InputReader.getInputAsString(dayNumber, year, useSampleInput)
    }

    abstract fun partOne(): Any
    abstract fun partTwo(): Any
    fun printDay() {
        val header = "Day $dayNumber, $year"
        val footer = "—".repeat(header.length)

        println(header)
        val startTimeP1 = System.currentTimeMillis()
        println("Part 1: ${partOne()} | Runtime: ${System.currentTimeMillis() - startTimeP1}")
        val startTimeP2 = System.currentTimeMillis()
        println("Part 2: ${partTwo()} | Runtime: ${System.currentTimeMillis() - startTimeP2}")
        println(footer)
    }
}

private object InputReader {

    fun getInputAsString(day: Int, year: Int, useSampleInput: Boolean): String =
        fileReader(day, year, useSampleInput).readText()

    fun getInputAsList(day: Int, year: Int, useSampleInput: Boolean): List<String> =
        fileReader(day, year, useSampleInput).readLines()

    private fun fileReader(day: Int, year: Int, useSampleInput: Boolean): BufferedReader =
        (if (useSampleInput) "day${day}_input_test" else "day${day}_input").let {
            File("src/main/kotlin/y$year/inputs/$it.txt")
                .bufferedReader()
        }
}