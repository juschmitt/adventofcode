package y2023

import utils.Day

class Day1(useSampleInput: Boolean) : Day(1, 2023, useSampleInput) {
    override fun partOne(): Any {
        return inputList.findSumOfFirstAndLast { it.isDigit() }
    }

    override fun partTwo(): Any {
        return Unit
    }
}

private fun List<String>.findSumOfFirstAndLast(comp: (Char) -> Boolean): Int = map { line ->
    "${line.first { comp(it) }}${line.last { comp(it) }}"
}.sumOf { it.toInt() }