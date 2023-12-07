package y2023

import utils.Day

class Day7 : Day(7, 2023, false) {
    override fun partOne(): Any {
        return inputList
            .map {
                val (hand, bid) = it.split(" ")
                hand to bid
            }.sortedWith(RegularCardComparator())
            .mapIndexed { index, (_, bid) -> (index + 1) * bid.toInt() }
            .sum()

    }

    override fun partTwo(): Any {
        return inputList
            .map {
                val (hand, bid) = it.split(" ")
                hand to bid
            }.sortedWith(JokerRuleCardComparator())
            .mapIndexed { index, (_, bid) -> (index + 1) * bid.toInt() }
            .sum()
    }
}

private class JokerRuleCardComparator : CardComparator() {

    override fun Map<Char, Int>.mapOccurrencesToStrength(): Int {
        val j = getOrDefault('J', 0)
        if (j == 5) return 6
        val max = filterNot { it.key == 'J' }.values.max()
        val min = filterNot { it.key == 'J' }.values.min()
        val count = values.count { it == 2 }
        return when {
            max + j == 5 -> 6
            max + j == 4 -> 5
            max + j == 3 && min == 2 -> 4
            max == 3 && min + j == 2 -> 4
            max + j == 3 -> 3
            max + j == 2 && count == 2 -> 2
            max == 2 && count + j == 2 -> 2
            max + j == 2 -> 1
            max + j == 1 -> 0
            else -> -1
        }
    }

    override fun Char.cardToValue(): Int = when {
        this == 'A' -> 14
        this == 'K' -> 13
        this == 'Q' -> 12
        this == 'J' -> 0
        this == 'T' -> 10
        isDigit() -> digitToInt()
        else -> -1
    }
}

private class RegularCardComparator : CardComparator() {

    override fun Map<Char, Int>.mapOccurrencesToStrength(): Int = when {
        values.max() == 5 -> 6
        values.max() == 4 -> 5
        values.max() == 3 && values.min() == 2 -> 4
        values.max() == 3 -> 3
        values.max() == 2 && values.count { it == 2 } == 2 -> 2
        values.max() == 2 -> 1
        values.max() == 1 -> 0
        else -> -1
    }

    override fun Char.cardToValue(): Int = when {
        this == 'A' -> 14
        this == 'K' -> 13
        this == 'Q' -> 12
        this == 'J' -> 11
        this == 'T' -> 10
        isDigit() -> digitToInt()
        else -> -1
    }
}

private abstract class CardComparator : Comparator<Pair<String, String>> {
    override fun compare(o1: Pair<String, String>?, o2: Pair<String, String>?): Int {
        return when {
            o1 == null -> -1
            o2 == null -> 1
            else -> compare(o1.first, o2.first)
        }
    }

    private fun compare(o1: String, o2: String): Int {
        val strengthHand1 = mapCharOccurrences(o1).mapOccurrencesToStrength()
        val strengthHand2 = mapCharOccurrences(o2).mapOccurrencesToStrength()

        val strengthByType = strengthHand1 - strengthHand2
        if (strengthByType != 0) return strengthByType

        o1.zip(o2)
            .forEach { (a, b) ->
                val comparison = a.cardToValue() - b.cardToValue()
                if (comparison != 0) return comparison
            }
        return 0
    }

    private fun mapCharOccurrences(s: String): Map<Char, Int> = s.fold(mutableMapOf()) { map, char ->
        map.merge(char, 1, Int::plus)
        map
    }

    abstract fun Map<Char, Int>.mapOccurrencesToStrength(): Int

    abstract fun Char.cardToValue(): Int
}
