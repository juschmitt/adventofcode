package y2023

import utils.Day

class Day6 : Day(6, 2023, false) {
    override fun partOne(): Any {
        return inputString.split("\n").map { line ->
            line.split(":").last().trim().split(" ").mapNotNull { it.toIntOrNull() }
        }.zipWithNext { times, distances -> times.zip(distances) { time, distance -> time to distance } }
            .flatten()
            .map { (time, dist) -> calculateNumOfWaysToWin(time.toLong(), dist.toLong()) }
            .reduce { acc, i -> acc * i }
    }

    override fun partTwo(): Any {
        return inputString.split("\n").map { line ->
            line.split(":").last().filter { it.isDigit() }.toLong()
        }.zipWithNext { time, dist -> calculateNumOfWaysToWin(time, dist) }
            .reduce { acc, i -> acc * i }
    }
}

private fun calculateNumOfWaysToWin(time: Long, distance: Long) =
    (1..<time)
        .map { press -> press * (time - press) }
        .count { dist -> dist > distance }
