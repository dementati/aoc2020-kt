package com.dementati.aoc2020.day24

import java.lang.IllegalArgumentException

typealias Pos = Triple<Long, Long, Long>

fun Pos.add(x: Long, y: Long, z: Long): Pos {
    val result = Pos(this.first + x, this.second + y, this.third + z)
    assert(result.sum() == 0L)
    return result
}

fun Pos.sum(): Long {
    return this.first + this.second + this.third
}

fun Pos.east(): Pos {
    return this.add(1, -1, 0)
}

fun Pos.west(): Pos {
    return this.add(-1, 1, 0)
}

fun Pos.northEast(): Pos {
    return this.add(1, 0, -1)
}

fun Pos.northWest(): Pos {
    return this.add(0, 1, -1)
}

fun Pos.southEast(): Pos {
    return this.add(0, -1, 1)
}

fun Pos.southWest(): Pos {
    return this.add(-1, 0, 1)
}

fun Pos.neighbours(): Set<Pos> {
    return setOf(
        this.east(),
        this.west(),
        this.northEast(),
        this.northWest(),
        this.southEast(),
        this.southWest(),
    )
}

fun parseGrid(lines: List<String>): Set<Pos> {
    val result = mutableSetOf<Pos>()

    lines.map { line ->
        var pos = Pos(0L, 0L, 0L)
        var i = 0
        while (i < line.length) {
            pos = when (line[i]) {
                'e' -> {
                    i++
                    pos.east()
                }
                'w' -> {
                    i++
                    pos.west()
                }
                'n' -> {
                    when (line[i + 1]) {
                        'e' -> {
                            i += 2
                            pos.northEast()
                        }
                        'w' -> {
                            i += 2
                            pos.northWest()
                        }
                        else -> throw IllegalArgumentException("Couldn't parse: $line")
                    }
                }
                's' -> {
                    when (line[i + 1]) {
                        'e' -> {
                            i += 2
                            pos.southEast()
                        }
                        'w' -> {
                            i += 2
                            pos.southWest()
                        }
                        else -> throw IllegalArgumentException("Couldn't parse: $line")
                    }
                }
                else -> throw IllegalArgumentException("Couldn't parse: $line")
            }
        }
        pos
    }.forEach {
        if (result.contains(it)) {
            result.remove(it)
        } else {
            result.add(it)
        }
    }

    return result
}

fun countNeighbours(grid: Set<Pos>, pos: Pos): Int {
    return pos.neighbours().count { grid.contains(it) }
}

fun evolve(grid: Set<Pos>): Set<Pos> {
    val result = mutableSetOf<Pos>()

    grid.flatMap { it.neighbours() }
        .forEach {
            val count = countNeighbours(grid, it)
            if (grid.contains(it)) {
                if (count == 1 || count == 2) {
                    result.add(it)
                }
            } else {
                if (count == 2) {
                    result.add(it)
                }
            }
        }

    return result
}

fun evolve(grid: Set<Pos>, n: Int): Set<Pos> {
    var result = grid
    (1..n).forEach { result = evolve(result) }
    return result
}

fun solveStar1(lines: List<String>): Int {
    return parseGrid(lines).size
}

fun solveStar2(lines: List<String>): Int {
    return evolve(parseGrid(lines), 100).size
}