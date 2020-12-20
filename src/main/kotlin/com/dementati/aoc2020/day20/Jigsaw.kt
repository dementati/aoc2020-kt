package com.dementati.aoc2020.day20

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
    private val data: MutableMap<Pos, Char>,
    val size: Long,
    var rotation: Rotation = Rotation.D0,
    var flipped: Boolean = false,
) {
    companion object {
        fun fromLines(
            id: Long,
            lines: List<String>,
            rotation: Rotation = Rotation.D0,
            flipped: Boolean = false,
        ): Tile {
            assert(lines.isNotEmpty())
            assert(lines.size == lines[0].length)

            val data = mutableMapOf<Pos, Char>()

            lines.indices.forEach { y ->
                lines.indices.forEach { x ->
                    data[x.toLong() to y.toLong()] = lines[y][x]
                }
            }

            return Tile(id, data, lines.size.toLong(), rotation, flipped)
        }
    }

    val lastIndex = size - 1

    fun get(x: Long, y: Long): Boolean? {
        var xm: Double = x.toDouble()
        var ym: Double = y.toDouble()
        xm -= lastIndex / 2.0
        ym -= lastIndex / 2.0

        when (rotation) {
            Rotation.D0 -> Unit
            Rotation.D90 -> { xm = ym.also { ym = -xm } }
            Rotation.D180 -> { xm = -xm.also { ym = -ym } }
            Rotation.D270 -> { xm = -ym.also { ym = xm } }
        }

        if (flipped) {
            xm = -xm
        }

        xm += lastIndex / 2.0
        ym += lastIndex / 2.0
        return data[xm.toLong() to ym.toLong()]?.let { it == '#'}
    }

    fun matches(other: Tile, dir: Direction): Boolean {
        assert(lastIndex == other.lastIndex)

        return when (dir) {
            Direction.LEFT -> (0..lastIndex).all { get(0, it) == other.get(lastIndex, it) }
            Direction.RIGHT -> (0..lastIndex).all { get(lastIndex, it) == other.get(0, it) }
            Direction.UP -> (0..lastIndex).all { get(it, 0) == other.get(it, lastIndex) }
            Direction.DOWN -> (0..lastIndex).all { get(it, lastIndex) == other.get(it, 0) }
        }
    }

    val orientations: List<Tile>
        get() {
            return listOf(
                Tile(id, data, size),
                Tile(id, data, size, Rotation.D90),
                Tile(id, data, size, Rotation.D180),
                Tile(id, data, size, Rotation.D270),
                Tile(id, data, size, Rotation.D0, true),
                Tile(id, data, size, Rotation.D90, true),
                Tile(id, data, size, Rotation.D180, true),
                Tile(id, data, size, Rotation.D270, true),
            )
        }

    private fun calculateMinPos(): Pos {
        return data.keys.minOf { (x, _) -> x } to data.keys.minOf { (_, y) -> y }
    }

    private fun calculateMaxPos(): Pos {
        return data.keys.maxOf { (x, _) -> x } to data.keys.maxOf { (_, y) -> y }
    }

    fun draw() {
        val (minX, minY) = calculateMinPos()
        val (maxX, maxY) = calculateMaxPos()

        (minY..maxY).forEach { y ->
            (minX..maxX).forEach { x ->
                val c = get(x, y)
                    ?.let { if (it) '#' else '.' }
                    ?: ' '
                print(c)
            }
            println()
        }
    }
}

fun getNeighbour(pos: Pos, dir: Direction): Pos {
    return when (dir) {
        Direction.LEFT -> pos.first - 1L to pos.second
        Direction.RIGHT -> pos.first + 1L to pos.second
        Direction.UP -> pos.first to pos.second - 1L
        Direction.DOWN -> pos.first to pos.second + 1L
    }
}

class TileMap constructor(tiles: List<Tile>) {
    private val tiles = tiles.toMutableList()
    val map = mutableMapOf<Pos, Tile>()
    private val border = mutableListOf<Pair<Pos, Direction>>()

    init {
        assert(tiles.isNotEmpty())
    }

    private val tileSize = tiles[0].size

    private val done: Boolean
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

            val candidatePos = getNeighbour(pos, dir)
            tiles.forEachIndexed { i, candidateTile ->
                findPlacableOrientation(candidateTile, candidatePos)?.also { orientedCandidateTile ->
                    tiles.removeAt(i)
                    map[candidatePos] = orientedCandidateTile

                    Direction.values().forEach { dir ->
                        val nPos = getNeighbour(candidatePos, dir)
                        if (!map.containsKey(nPos)) {
                            border.add(candidatePos to dir)
                        }
                    }

                    return
                }
            }
        }

        throw IllegalArgumentException("No solution found")
    }

    private fun findPlacableOrientation(tile: Tile, pos: Pos): Tile? {
        tile.orientations.forEach { candidate ->
            val fits = Direction.values().all { dir ->
                val nPos = getNeighbour(pos, dir)
                map[nPos]
                    ?.let { neighbour -> candidate.matches(neighbour, dir) }
                    ?: true
            }

            if (fits) {
                return candidate
            }
        }

        return null
    }

    fun solve() {
        while (!done) {
            place()
        }
    }

    private fun calculateMinPos(): Pos {
        return map.keys.minOf { (x, _) -> x } to map.keys.minOf { (_, y) -> y }
    }

    private fun calculateMaxPos(): Pos {
        return map.keys.maxOf { (x, _) -> x } to map.keys.maxOf { (_, y) -> y }
    }

    private val cornerIds: List<Long>
        get() {
            val (minX, minY) = calculateMinPos()
            val (maxX, maxY) = calculateMaxPos()

            return listOf(
                map[minX to minY]?.id ?: throw IllegalArgumentException("No solution found"),
                map[minX to maxY]?.id ?: throw IllegalArgumentException("No solution found"),
                map[maxX to minY]?.id ?: throw IllegalArgumentException("No solution found"),
                map[maxX to maxY]?.id ?: throw IllegalArgumentException("No solution found"),
            )
        }

    val star1Solution: Long
        get() = cornerIds.reduce { a, b -> a * b }

    val image: Tile
        get() {
            val (minX, minY) = calculateMinPos()
            val (maxX, maxY) = calculateMaxPos()

            val data = mutableMapOf<Pos, Char>()

            val nTileSize = tileSize - 2
            var nTileY = 0L
            (minY..maxY).forEach { tileY ->
                var nTileX = 0L
                (minX..maxX).forEach { tileX ->
                    val tilePos = tileX to tileY
                    val tile = map[tilePos] ?: throw IllegalArgumentException("Corrupted tilemap")

                    var ny = 0L
                    (1 until tile.lastIndex).forEach { y ->
                        var nx = 0L
                        (1 until tile.lastIndex).forEach { x ->
                            val pos = nTileX * nTileSize + nx to nTileY * nTileSize + ny
                            tile.get(x, y)?.also { data[pos] = if (it) '#' else '.' }
                            nx++
                        }
                        ny++
                    }

                    nTileX++
                }
                nTileY++
            }

            return Tile(0L, data, (maxX - minX + 1) * nTileSize)
        }
}

fun parseInput(groups: List<List<String>>): List<Tile> {
    return groups.map { lines ->
        val id = """Tile (\d+):""".toRegex().matchEntire(lines[0])
            ?.destructured
            ?.let { (n) -> n.toLong() }
            ?: throw IllegalArgumentException("Couldn't parse: ${lines[0]}")

        Tile.fromLines(id, lines.drop(1))
    }
}