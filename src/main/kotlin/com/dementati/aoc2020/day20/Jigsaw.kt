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

    fun get(x: Int, y: Int): Boolean {
        return false
    }

    fun matches(other: Tile, dir: Direction): Boolean {
        return false
    }
}