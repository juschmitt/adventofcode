package day5

import utils.getInputAsList

fun main() {
    println("------Part 1-------")
    println("Number of fresh ingredients: ${partOne(getInputAsList(5, false))}")
    println("------Part 2-------")
    println("Number of fresh ingredients: ${partTwo(getInputAsList(5, false))}")
}

private fun partOne(input: List<String>): Int {
    val blankLine = input.indexOf("")
    val ranges = input.subList(0, blankLine).map {
        val (x, y) = it.split("-").map(String::toLong)
        x..y
    }
    val ids = input.subList(blankLine + 1, input.size).map(String::toLong)
    return ids.map { id -> ranges.any { range -> id in range } }.filter { it }.size
}

private fun partTwo(input: List<String>): Long {
    val blankLine = input.indexOf("")
    val ranges = input.subList(0, blankLine).map {
        val (x, y) = it.split("-").map(String::toLong)
        x..y
    }

    return ranges.mergeOverlappingRanges().sumOf { it.last - it.first + 1 }
}

private fun List<LongRange>.mergeOverlappingRanges(): List<LongRange> =
    sortedBy { it.first }.fold(mutableListOf()) { acc, range ->
        when {
            acc.isEmpty() -> acc.add(range)
            acc.last().last < range.first-1 -> acc.add(range)
            else -> {
                val last = acc.removeLast()
                acc.add(last.first..maxOf(last.last, range.last))
            }
        }
        acc
    }