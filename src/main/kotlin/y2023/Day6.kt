package y2023

import utils.Day

class Day6 : Day(6, 2023, false) {
    override fun partOne(): Any {
        return inputString.split("\n").map { line ->
            line.split(":").last().trim().split(" ").mapNotNull { it.toIntOrNull() }
        }.zipWithNext { times, distances -> times.zip(distances) { time, distance -> Race(time, distance) } }
            .flatten()
            .map { race ->
                (1..<race.time)
                    .map { press -> press * (race.time - press) }
                    .count { dist -> dist > race.recordDist }
            }
            .reduce { acc, i -> acc * i }
    }

    override fun partTwo(): Any {
        return Unit
    }
}

private data class Race(
    val time: Int,
    val recordDist: Int
)
