package y2023

import utils.Day
import kotlin.math.pow

class Day4 : Day(4, 2023, false) {
    override fun partOne(): Any {
        return inputList.mapToCards().calculatePointsPerCard().sum()
    }

    override fun partTwo(): Any {
        val cards = inputList.mapToCards()
        return IntArray(cards.size) { 1 }.apply {
            for (idx in cards.indices) {
                repeat(cards[idx].amountOfWinnings) { i ->
                    this[idx + i + 1] += this[idx]
                }
            }
        }.sum()
    }
}

private fun List<String>.mapToCards(): List<Card> = map { line ->
    val (cardNum, tail) = line.split(": ")
    val (winningNums, myNums) = tail.split("|").map { it.mapToNumbers(" ") }
    Card(cardNum, myNums.intersect(winningNums.toSet()).size)
}

private fun List<Card>.calculatePointsPerCard(): List<Int> = map { card ->
    2.0.pow(card.amountOfWinnings-1).toInt()
}

private fun String.mapToNumbers(splitter: String): List<Int> =
    trim().split(splitter).mapNotNull { it.toIntOrNull() }

private data class Card(
    val cardNumber: String,
    val amountOfWinnings: Int
)