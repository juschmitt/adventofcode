package day1

import utils.getInputAsList
import kotlin.math.abs

fun main() {
    println("------Part 1-------")
    println("Number of Zeros: ${partOne(getInputAsList(1, false))}")
    println("------Part 2-------")
    println("Number of Zeros: ${partTwo(getInputAsList(1, false))}")
}

private fun partOne(input: List<String>): Int = input
    .toRotation()
    .fold(50 to 0) { (dial, zeros), distance ->
        val newPointingAt = (dial + distance) % 100
        newPointingAt to if (newPointingAt == 0) zeros + 1 else zeros
    }.second

private fun partTwo(input: List<String>): Int = input
    .toRotation()
    .fold(50 to 0) { (dial, zeros), distance ->
        val nextDial = (dial + distance).mod(100)

        val untilNextZero = when {
            dial == 0 -> 100
            distance > 0 -> 100 - dial
            else -> dial
        }

        val nextZeros = zeros + when {
            abs(distance) >= untilNextZero -> 1 + (abs(distance) - untilNextZero) / 100
            else -> 0
        }

        nextDial to nextZeros
    }.second

private fun List<String>.toRotation(): List<Int> = map { it.replace("L", "-") }
    .map { it.replace("R", "+") }
    .map { it.toInt() }