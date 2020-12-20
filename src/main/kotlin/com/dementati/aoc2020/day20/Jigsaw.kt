package com.dementati.aoc2020

import java.lang.IllegalArgumentException

typealias Pos = Pair<Long, Long>

enum class Rotation {
    D0,
    D90,
    D180,
    D270,
}

enum class Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN,
}

class Tile constructor(
    val id: Long,
    val lines: List<String>,
    val rotation: Rotation = Rotation.D0,
    val flipped: Boolean = false,
) {
    init {
        assert(lines.isNotEmpty())
        assert(lines.size == lines[0].length)
    }

    val dim = lines.size - 1

    fun get(x: Int, y: Int): Boolean {
        var xm: Double = x.toDouble()
        var ym: Double = y.toDouble()
        xm -= dim / 2.0
        ym -= dim / 2.0

        when (rotation) {
            Rotation.D0 -> Unit
            Rotation.D90 -> { xm = ym.also { ym = -xm } }
            Rotation.D180 -> { xm = -xm.also { ym = -ym } }
            Rotation.D270 -> { xm = -ym.also { ym = xm } }
        }

        if (flipped) {
            xm = -xm
        }

        xm += dim / 2.0
        ym += dim / 2.0
        return lines[ym.toInt()][xm.toInt()] == '#'
    }

    fun matches(other: Tile, dir: Direction): Boolean {
        assert(dim == other.dim)

        return when (dir) {
            Direction.LEFT -> (0..dim).all { get(0, it) == other.get(dim, it) }
            Direction.RIGHT -> (0..dim).all { get(dim, it) == other.get(0, it) }
            Direction.UP -> (0..dim).all { get(it, 0) == other.get(it, dim) }
            Direction.DOWN -> (0..dim).all { get(it, dim) == other.get(it, 0) }
        }
    }

    val orientations: List<Tile>
        get() {
            return listOf(
                Tile(id, lines),
                Tile(id, lines, Rotation.D90),
                Tile(id, lines, Rotation.D180),
                Tile(id, lines, Rotation.D270),
                Tile(id, lines, Rotation.D0, true),
                Tile(id, lines, Rotation.D90, true),
                Tile(id, lines, Rotation.D180, true),
                Tile(id, lines, Rotation.D270, true),
            )
        }
}

class TileMap constructor(tiles: List<Tile>) {
    val tiles = tiles.toMutableList()
    val map = mutableMapOf<Pos, Tile>()
    val border = mutableListOf<Pair<Pos, Direction>>()

    val done: Boolean
        get() = tiles.isEmpty()

    fun place() {
        if (done) {
            return
        }

        if (border.isEmpty()) {
            val tile = tiles.removeAt(0)
            val pos = 0L to 0L
            map[pos] = tile
            border.add(pos to Direction.UP)
            border.add(pos to Direction.DOWN)
            border.add(pos to Direction.LEFT)
            border.add(pos to Direction.RIGHT)
            return
        }

        while (border.isNotEmpty()) {
            val (pos, dir) = border.removeAt(0)

            val pos2 = when (dir) {
                Direction.LEFT -> pos.first - 1L to pos.second
                Direction.RIGHT -> pos.first + 1L to pos.second
                Direction.UP -> pos.first to pos.second - 1L
                Direction.DOWN -> pos.first to pos.second + 1L
            }

            tiles.forEachIndexed { i, tile2 ->
                canPlace(tile2, pos2)?.also { placable ->
                    tiles.removeAt(i)
                    map[pos2] = placable
                    return
                }
            }
        }

        throw IllegalArgumentException("No solution found")
    }

    fun canPlace(tile: Tile, pos: Pos): Tile? {
        val neighbours = listOf(
            Direction.LEFT to (pos.first - 1L to pos.second),
            Direction.RIGHT to (pos.first + 1L to pos.second),
            Direction.UP to (pos.first to pos.second - 1L),
            Direction.DOWN to (pos.first to pos.second + 1L),
        )

        tile.orientations.forEach { candidate ->
            val fits = neighbours.all { (nDir, nPos) ->
                map[nPos]
                    ?.let { neighbour -> candidate.matches(neighbour, nDir) }
                    ?: true
            }

            if (fits) {
                return candidate
            }
        }

        return null
    }
}