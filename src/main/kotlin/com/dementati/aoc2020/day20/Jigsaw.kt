package com.dementati.aoc2020

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

class Tile constructor(val lines: List<String>) {
    var rotation = Rotation.D0
    var flipped = false

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
        return false
    }
}