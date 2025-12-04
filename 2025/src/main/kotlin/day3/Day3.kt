package day3

import utils.getInputAsList

fun main() {
    println("------Part 1-------")
    println("Sum of invalid IDs: ${partOne(getInputAsList(3, false))}")
    println("------Part 2-------")
    println("Sum of invalid IDs: ${partTwo(getInputAsList(3, false))}")
}

private fun partOne(input: List<String>): Int = input.sumOf { bank ->
    var maxDigit1 = 0 to 0
    var maxDigit2 = 0 to 0
    val digits = bank.map { it.digitToInt() }
    for (i in 0 until digits.size - 1) {
        if (digits[i] > maxDigit1.first) {
            maxDigit1 = digits[i] to i
        }
    }
    for (i in digits.size - 1 downTo maxDigit1.second + 1) {
        if (digits[i] > maxDigit2.first) {
            maxDigit2 = digits[i] to i
        }
    }
    maxDigit1.first * 10 + maxDigit2.first
}

private fun partTwo(input: List<String>): Long = input.sumOf { bank ->
    val digits = bank.map { it.digitToInt() }
    val toRemove = digits.size - 12
    val stack = ArrayDeque<Int>()
    var removed = 0
    for (digit in digits) {
        while (removed < toRemove && stack.isNotEmpty() && stack.last() < digit) {
            stack.removeLast()
            removed++
        }
        stack.addLast(digit)
    }
    while (removed < toRemove) {
        stack.removeLast()
        removed++
    }
    stack.joinToString("").toLong()
}