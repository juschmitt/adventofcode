package y2023

import utils.Day

class Day5 : Day(5, 2023, false) {
    override fun partOne(): Any {
        return inputString.parseAlmanac().run {
            seeds.minOf { seed ->
                maps.findLocationForSeed(seed)
            }
        }
    }

    override fun partTwo(): Any {
        return inputString.parseAlmanac().run {
            locations().first {
                val seed = maps.findSeedForLocation(it)
                seeds.chunked(2).fold(false) { acc, (start, range) ->
                    acc || (start..<(start + range)).contains(seed)
                }
            }
        }
    }
}

private fun locations(): Sequence<Long> = generateSequence(1) { it + 1 }

private fun List<List<Pair<LongRange, LongRange>>>.findLocationForSeed(seed: Long) =
    fold(seed) { cur, map ->
        val mapping = map.find { (source, _) -> source.contains(cur) }
        mapping?.let { (source, dest) -> dest.first + (cur - source.first) } ?: cur
    }

private fun List<List<Pair<LongRange, LongRange>>>.findSeedForLocation(location: Long) =
    reversed().fold(location) { cur, map ->
        val mapping = map.find { (_, dest) -> dest.contains(cur) }
        mapping?.let { (source, dest) -> source.first + (cur - dest.first) } ?: cur
    }

private fun String.parseAlmanac(): Almanac {
    val (head, tail) = split("\n\n").run { Pair(first(), drop(1)) }
    val seeds = head.split(":")[1].trim().split(" ").map { it.toLong() }
    val maps = tail.parseMappings()
    return Almanac(seeds, maps)
}

private fun List<String>.parseMappings() = map { map ->
    map.split("\n").drop(1).map { line ->
        val (dest, source, range) = line.split(" ").map { it.toLong() }
        source..<(source + range) to dest..<(dest + range)
    }
}

private data class Almanac(
    val seeds: List<Long>,
    val maps: List<List<Pair<LongRange, LongRange>>>
)