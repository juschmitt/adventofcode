package y2023

import utils.Day

class Day1(useSampleInput: Boolean) : Day(1, 2023, useSampleInput) {
    override fun partOne(): Any {
        return inputList.map { line ->
            "${line.first { it.isDigit() }}${line.last { it.isDigit() }}"
        }.sumOf { it.toInt() }
    }

    override fun partTwo(): Any {
        return Unit
    }
}