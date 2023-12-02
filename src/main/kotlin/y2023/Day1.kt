package y2023

import utils.Day

class Day1(useSampleInput: Boolean = false) : Day(1, 2023, useSampleInput) {
    override fun partOne(): Any {
        return inputList.findSumOfFirstAndLastDigit().sum()
    }

    override fun partTwo(): Any {
        return inputList.findSumOfFirstAndLastNumber().sum()
    }
}

private fun List<String>.findSumOfFirstAndLastDigit(): List<Int> = map { line ->
    line.first { it.isDigit() }.digitToInt() * 10 + line.last { it.isDigit() }.digitToInt()
}

private fun List<String>.findSumOfFirstAndLastNumber(): List<Int> = map { line ->
    line.findFirstNumber() * 10 + line.findLastNumber()
}

private fun String.findFirstNumber(): Int {
    var startIdx = 0
    (1 .. length).forEach { i ->
        val current = substring(startIdx, i)
        Number.entries.forEach { if(current.contains(it.name.lowercase())) { return it.ordinal+1 } }
        current.firstOrNull { it.isDigit() }?.let { return it.digitToInt() }
        if (i > 5) startIdx++
    }
    error("Something wrong here. No numbers in $this")
}

private fun String.findLastNumber(): Int {
    var endIdx = length
    (length - 1 downTo 0).forEach { i ->
        val current = substring(i, endIdx)
        Number.entries.forEach { if(current.contains(it.name.lowercase())) { return it.ordinal+1 } }
        current.lastOrNull { it.isDigit() }?.let { return it.digitToInt() }
        if (i < length-5) endIdx--
    }
    error("Something wrong here. No numbers in $this")
}

private enum class Number {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
}