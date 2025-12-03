package day2

import utils.getInputAsString

fun main() {

    println("------Part 1-------")
    println("Sum of invalid IDs: ${partOne(getInputAsString(2, false))}")
    println("------Part 2-------")
    println("Sum of invalid IDs: ${partTwo(getInputAsString(2, false))}")
}

private fun partOne(input: String): Long = input
    .split(",")
    .map { range ->
        val (x, y) = range.split("-")
        (x.toLong()..y.toLong()).map { "$it" }
    }
    .flatten()
    .filterNot {
        if (it.length % 2 != 0) true
        else if (it[0] == '0') {
            false
        } else {
            val y = it.substring(0, it.length / 2)
            val x = it.substring(it.length / 2)
            x != y
        }
    }
    .sumOf { it.toLong() }

private fun partTwo(input: String): Long = input
    .split(",")
    .map { range ->
        val (x, y) = range.split("-")
        (x.toLong()..y.toLong()).map { "$it" }
    }
    .flatten()
    .filter {
        for (i in 1..<it.length) {
            if (it.length % i != 0) continue
            val windows = it.windowed(size = i, step = i).map { it.toInt() }
            val (_, r) = windows.fold(windows[0] to true) { (cur, result), win -> win to (result && cur == win) }
            if (r) return@filter true
        }
        false
    }
    .sumOf { it.toLong() }
