package y2023

import utils.Day

class Day8 : Day(8, 2023, false) {

    override fun partOne(): Any {
        val (directions, mapString) = inputString.split("\n\n")
        val map = mapString.split("\n").associate { line ->
            val (key, destinations) = line.split(" = ")
            val value = destinations.replace("(", "").replace(")", "").split(", ").zipWithNext().first()
            key to value
        }
        return map.findStepsToEnd(directions.toCharArray())
    }

    override fun partTwo(): Any {
        return Unit
    }
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