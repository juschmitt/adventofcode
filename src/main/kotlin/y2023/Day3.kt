package y2023

import utils.Day

class Day3 : Day(3, 2023, false) {
    override fun partOne(): Any {
        return inputList.mapToSchema().numbersWithAdjacentSymbol().sumOf { it.value }
    }

    override fun partTwo(): Any {
        return inputList.mapToSchema().findGearRatios().sum()
    }
}

private fun Map<Int, List<Schema>>.findGearRatios(): List<Int> = map { entry ->
    entry.value.filter { schema -> schema is Schema.Symbol && schema.isGear(this, entry.key) }
        .mapNotNull { schema -> (schema as? Schema.Symbol)?.getAdjacentNumbers(this, entry.key) }
        .map { it.first().value * it.last().value }
}.flatten()

private fun Schema.Symbol.isGear(schemaMap: Map<Int, List<Schema>>, lineIdx: Int): Boolean =
    symbol == "*" && getAdjacentNumbers(schemaMap, lineIdx).size == 2

private fun Schema.Symbol.getAdjacentNumbers(schemaMap: Map<Int, List<Schema>>, lineIdx: Int): List<Schema.Number> =
    schemaMap.getSurroundingLines(lineIdx)
        .filterInRange { this.index in it.startIndex-1..it.endIndex+1 }

private fun Map<Int, List<Schema>>.numbersWithAdjacentSymbol(): List<Schema.Number> = map { entry ->
    entry.value.filter { schema -> schema is Schema.Number && schema.hasAdjacentSymbol(this, entry.key) }
        .mapNotNull { it as? Schema.Number }
}.flatten()

private fun Schema.Number.hasAdjacentSymbol(schemaMap: Map<Int, List<Schema>>, lineIdx: Int): Boolean =
    schemaMap.getSurroundingLines(lineIdx)
        .filterInRange<Schema.Symbol> { it.index in this.startIndex-1..this.endIndex+1 }.isNotEmpty()

private inline fun <reified T : Schema> List<Schema>.filterInRange(filter: (T) -> Boolean): List<T> =
    filterIsInstance<T>().filter(filter)

private fun Map<Int, List<Schema>>.getSurroundingLines(lineIdx: Int): List<Schema> =
    getOrDefault(lineIdx, emptyList()) +
            getOrDefault(lineIdx+1, emptyList()) +
            getOrDefault(lineIdx-1, emptyList())

private fun List<String>.mapToSchema(): Map<Int, List<Schema>> = mapIndexed { index, line ->
    val symbols: List<Schema.Symbol> = line.mapIndexedNotNull { i, c ->
        if (c != '.' && !c.isDigit()) Schema.Symbol(i, c.toString()) else null
    }
    val numbers: List<Schema.Number> = line.foldIndexed(emptyList()) { i, acc, char ->
        when {
            char.isDigit() && line.getOrNull(i-1)?.isDigit() == true -> {
                val last = acc.last()
                acc.dropLast(1) + last.copy(endIndex = i, value = last.value*10 + char.digitToInt())
            }
            char.isDigit() -> acc + Schema.Number(i, i, char.digitToInt())
            else -> acc
        }
    }

    index to symbols + numbers
}.toMap()

private sealed interface Schema {
    data class Number(val startIndex: Int, val endIndex: Int, val value: Int) : Schema
    data class Symbol(val index: Int, val symbol: String) : Schema
}