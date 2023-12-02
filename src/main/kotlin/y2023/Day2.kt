package y2023

import utils.Day
import kotlin.math.max

class Day2(useSampleInput: Boolean = false) : Day(2, 2023, useSampleInput) {
    override fun partOne(): Any {
        return inputList
            .mapToGame()
            .filter { game ->
                game.grabs.fold(true) { acc1, cubes ->
                    acc1 && cubes.fold(true) { acc2, cube ->
                        acc2 && when (cube) {
                            is Cube.Blue -> cube.amount <= winningBlues
                            is Cube.Green -> cube.amount <= winningGreens
                            is Cube.Red -> cube.amount <= winningReds
                        }
                    }
                }
            }.sumOf { it.id }
    }

    override fun partTwo(): Any {
        return inputList.mapToGame()
            .map { it.grabs }
            .fold(0) { power, game ->
                val (red, green, blue) = game.fold(Triple(0, 0, 0)) { acc, cubes ->
                    val grab = cubes.fold(Triple(0, 0, 0)) { grab, cube ->
                        when (cube) {
                            is Cube.Blue -> grab.copy(third = cube.amount)
                            is Cube.Green -> grab.copy(second = cube.amount)
                            is Cube.Red -> grab.copy(first = cube.amount)
                        }
                    }
                    Triple(max(acc.first, grab.first), max(acc.second, grab.second), max(acc.third, grab.third))
                }
                power + (red * green * blue)
            }
    }
}

private fun List<String>.mapToGame() = map { line ->
    val game = line.split(":")
    val id = game[0].drop(5).toInt()
    val grabs = game[1].split(";").map { grab ->
        grab.split(",").map { cubes ->
            val c = cubes.trim().split(" ")
            val amount = c[0].toInt()
            when (c[1]) {
                "red" -> Cube.Red(amount)
                "blue" -> Cube.Blue(amount)
                "green" -> Cube.Green(amount)
                else -> error("Something wrong here: $line, $grab, $cubes, $c")
            }
        }
    }
    Game(id, grabs)
}

private data class Game(
    val id: Int,
    val grabs: List<List<Cube>>
)

private sealed interface Cube {
    val amount: Int

    data class Red(override val amount: Int) : Cube
    data class Blue(override val amount: Int) : Cube
    data class Green(override val amount: Int) : Cube
}


val winningReds = 12
val winningGreens = 13
val winningBlues = 14