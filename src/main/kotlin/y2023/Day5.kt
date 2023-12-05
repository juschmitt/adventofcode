package y2023

import utils.Day
import kotlin.math.min

class Day5(useSampleInput: Boolean = false) : Day(5, 2023, useSampleInput) {
    override fun partOne(): Any {
        return inputString.parseAlmanac().run {
            seeds.fold(Long.MAX_VALUE) { location, seed ->
                print("$seed -> ")
                val other = maps.fold(seed) { cur, map ->
                    val mapping = map.find { (source, _) -> source.contains(cur) }
                    (mapping?.let { (source, dest) -> dest.first + (cur - source.first) } ?: cur).also { print("$it -> ") }
                }.also { print("$it\n") }
                min(location, other)
            }
        }
    }

    override fun partTwo(): Any {
        return Unit
    }
}

private fun String.parseAlmanac(): Almanac {
    val (head, tail) = split("\n\n").run { Pair(first(), drop(1)) }
    val seeds = head.split(":")[1].trim().split(" ").map { it.toLong() }
    val maps = tail.map { map ->
        map.split("\n").drop(1).map { line ->
            val (dest, source, range) = line.split(" ").map { it.toLong() }
            source..<(source + range) to dest..<(dest + range)
        }
    }
    return Almanac(seeds, maps)
}

private data class Almanac(
    val seeds: List<Long>,
    val maps: List<List<Pair<LongRange, LongRange>>>
)