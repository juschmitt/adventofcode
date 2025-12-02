package day2

import utils.getInputAsString

fun main() {

    println("------Part 1-------")
    println("Sum of invalid IDs: ${partOne(getInputAsString(2, false))}")
    println("------Part 2-------")
//    println("Number of Zeros: ${partTwo(getInputAsList(1, false))}")
}

private fun partOne(input: String): Long = input
    .split(",")
    .map { range ->
        val (x,y) = range.split("-")
        (x.toLong()..y.toLong()).map { "$it" }
    }
    .flatten()
    .filterNot {
        println(it)
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