package y2023

import utils.Day

class Day6 : Day(6, 2023, false) {
    override fun partOne(): Any {
        return inputString.split("\n").map { line ->
            line.split(":").last().trim().split(" ").mapNotNull { it.toIntOrNull() }
        }.zipWithNext { times, distances -> times.zip(distances) { time, distance -> time to distance } }
            .flatten()
            .map { (time, recordDist) ->
                (1..<time)
                    .map { press -> press * (time - press) }
                    .count { dist -> dist > recordDist }
            }
            .reduce { acc, i -> acc * i }
    }

    override fun partTwo(): Any {
        return Unit
    }
}
