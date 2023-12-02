package y2023

import utils.Day

class Day1(useSampleInput: Boolean = false) : Day(1, 2023, useSampleInput) {
    override fun partOne(): Any {
        return inputList.findSumOfFirstAndLastDigit()
    }

    override fun partTwo(): Any {
        return inputList.mapNumberNameToNumeric().findSumOfFirstAndLastDigit()
    }
}

private fun List<String>.findSumOfFirstAndLastDigit(): Int = map { line ->
    "${line.first { it.isDigit() }}${line.last { it.isDigit() }}"
}.sumOf { it.toInt() }

private fun List<String>.mapNumberNameToNumeric(): List<String> = map { line ->
    var newline = line
    Number.entries.forEach { num -> newline = newline.replace(num.name, num.numeric, true) }
    newline
}

private enum class Number(val numeric: String) {
    ONE("o1e"),
    TWO("t2o"),
    THREE("t3e"),
    FOUR("f4r"),
    FIVE("f5e"),
    SIX("s6x"),
    SEVEN("s7n"),
    EIGHT("e8t"),
    NINE("n9e"),
}