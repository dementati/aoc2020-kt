package com.dementati.aoc2020.day20

import com.dementati.aoc2020.TileMap
import com.dementati.aoc2020.filereaders.inputAsDividedLineGroups
import com.dementati.aoc2020.parseInput

fun main() {
    val tiles = parseInput(inputAsDividedLineGroups(20))
    val tilemap = TileMap(tiles)
    tilemap.solve()
    println(tilemap.star1Solution)
}