package day4

import utils.getInputAsList

fun main() {
    println("------Part 1-------")
    println("Number of Rolls: ${partOne(getInputAsList(4, false))}")
    println("------Part 2-------")
    println("Number of Rolls: ${partTwo(getInputAsList(4, false))}")
}

private fun partOne(input: List<String>): Int {
    var numberOfAccessibleRolls = 0
    val grid = input.map { it.toList() }
    for (i in 0 until grid.size) {
        for (j in 0 until grid[i].size) {
            if (grid[i][j] != '@') continue
            val rollsInGrid = grid
                .subList((i - 1).coerceAtLeast(0), (i + 2).coerceAtMost(grid.size))
                .map { it.subList((j - 1).coerceAtLeast(0), (j + 2).coerceAtMost(grid[i].size)) }
                .flatten()
                .sumOf { if (it == '@') 1 else 0 }
            if (rollsInGrid <= 4) numberOfAccessibleRolls++
        }
    }
    return numberOfAccessibleRolls
}


private fun partTwo(input: List<String>): Int {
    var numberOfAccessibleRolls = 0
    val grid = input.map { it.toMutableList() }.toMutableList()
    var rollsAreAccessible = true
    var runs = 0
    while (rollsAreAccessible) {
        var numberOfAccessibleRollsInRun = 0
        for (i in 0 until grid.size) {
            for (j in 0 until grid[i].size) {
                if (grid[i][j] != '@') continue
                val rollsInGrid = grid
                    .subList((i - 1).coerceAtLeast(0), (i + 2).coerceAtMost(grid.size))
                    .map { it.subList((j - 1).coerceAtLeast(0), (j + 2).coerceAtMost(grid[i].size)) }
                    .flatten()
                    .sumOf { if (it == '@') 1 else 0 }
                if (rollsInGrid <= 4) {
                    numberOfAccessibleRollsInRun++
                    grid[i][j] = 'x'
                }
            }
        }
        println("Run: #${++runs} - Removed Rolls: $numberOfAccessibleRollsInRun - Total: $numberOfAccessibleRolls")
        numberOfAccessibleRolls += numberOfAccessibleRollsInRun
        rollsAreAccessible = numberOfAccessibleRollsInRun != 0
    }
    return numberOfAccessibleRolls
}
