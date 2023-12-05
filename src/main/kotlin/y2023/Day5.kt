package y2023

import utils.Day

class Day5(useSampleInput: Boolean = false) : Day(5, 2023, useSampleInput) {
    override fun partOne(): Any {
        return inputString.parseAlmanac().findNearestLocation()
    }


    override fun partTwo(): Any {
        return inputString.parseAlmanac().findNearestLocation2()
    }
}

private fun Almanac.findNearestLocation() = seeds.minOf { seed ->
    maps.fold(seed) { cur, map ->
        val mapping = map.find { (source, _) -> source.contains(cur) }
        mapping?.let { (source, dest) -> dest + (cur - source.first) } ?: cur
    }
}

private fun Almanac.findNearestLocation2() = seeds.chunked(2).minOf { (start, range) ->
    (start..<(start+range)).minOf { seed ->
        maps.fold(seed) { cur, map ->
            val mapping = map.find { (source, _) -> source.contains(cur) }
            mapping?.let { (source, dest) -> dest + (cur - source.first) } ?: cur
        }
    }
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
        source..<(source + range) to dest
    }
}

private data class Almanac(
    val seeds: List<Long>,
    val maps: List<List<Pair<LongRange, Long>>>
)