package com.dementati.aoc2020.day20

import com.dementati.aoc2020.filereaders.inputAsDividedLineGroups

fun main() {
    val tiles = parseInput(inputAsDividedLineGroups(20))
    val tilemap = TileMap(tiles)
    tilemap.solve()
    println(tilemap.image.solveStar2())
}