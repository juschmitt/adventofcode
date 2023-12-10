package y2023

import utils.Day
import kotlin.math.max

class Day8 : Day(8, 2023, false) {

    override fun partOne(): Any {
        val (directions, map) = inputString.parseDirectionsAndMap()
        return map.findStepsToEnd(directions.toCharArray())
    }

    override fun partTwo(): Any {
        val (directions, map) = inputString.parseDirectionsAndMap()
        return map.keys.filter { it.endsWith('A') }
            .map { map.findStepsToEndAsGhost(it, directions.toCharArray()) }
            .findLcm()
    }
}

private fun List<Long>.findLcm(): Long {
    tailrec fun inner(idx: Int, acc: Long): Long {
        return when {
            idx == size -> acc
            else -> inner(idx+1, acc lcm get(idx))
        }
    }
    return inner(0, first())
}

private infix fun Long.lcm(other: Long): Long {
    val max = max(this, other)
    tailrec fun inner(lcm: Long): Long = when {
        lcm == this * other -> lcm
        lcm % this == 0L && lcm % other == 0L -> lcm
        else -> inner(lcm + max)
    }
    return inner(max)
}

private fun Map<String, Pair<String, String>>.findStepsToEndAsGhost(startLocation: String, directions: CharArray): Long {
    tailrec fun inner(location: String, curDir: Int, steps: Long): Long {
        val newDir = if (curDir + 1 == directions.size) 0 else curDir + 1
        val nextLocation = this[location]!!.nextLocation(directions[curDir])
        return when {
            curDir == directions.size - 1 && nextLocation.endsWith('Z') -> steps
            else -> {
                inner(nextLocation, newDir, steps + 1)
            }
        }
    }
    return inner(startLocation, 0, 1)
}

private fun Map<String, Pair<String, String>>.findStepsToEnd(directions: CharArray): Int {
    tailrec fun inner(location: String, curDir: Int, steps: Int): Int {
        val newDir = if (curDir + 1 == directions.size) 0 else curDir + 1
        val nextLocation = this[location]!!.nextLocation(directions[curDir])
        return when {
            curDir == directions.size - 1 && nextLocation == "ZZZ" -> steps
            else -> {
                inner(nextLocation, newDir, steps + 1)
            }
        }
    }
    return inner("AAA", 0, 1)
}

private fun Pair<String, String>.nextLocation(direction: Char): String {
    return when (direction) {
        'L' -> first
        'R' -> second
        else -> error("Not a direction: $direction")
    }
}

private fun String.parseDirectionsAndMap(): Pair<String, Map<String, Pair<String, String>>> {
    val (directions, mapString) = split("\n\n")
    val map = mapString.split("\n").associate { line ->
        val (key, destinations) = line.split(" = ")
        val value = destinations.replace("(", "").replace(")", "").split(", ").zipWithNext().first()
        key to value
    }
    return directions to map
}
