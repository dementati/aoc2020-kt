package com.dementati.aoc2020.day20

import com.dementati.aoc2020.Tile
import com.dementati.aoc2020.TileMap
import com.dementati.aoc2020.filereaders.inputAsDividedLineGroups
import com.dementati.aoc2020.parseInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TileMapTest {
    @Test
    fun test() {
        val tiles = listOf(
            Tile(0, listOf(
                "####",
                "...#",
                "....",
                "...#"
            )),
            Tile(0, listOf(
                "#..#",
                "#...",
                "...#",
                "#.#.",
            )),
            Tile(0, listOf(
                "....",
                "....",
                "....",
                "#..#"
            )),
            Tile(0, listOf(
                "....",
                "....",
                "....",
                "####"
            ))
        )

        val tilemap = TileMap(tiles)

        tilemap.place()
        tilemap.place()
        tilemap.place()
        tilemap.place()

        println()
    }

    @Test
    fun example1() {
        val tiles = parseInput(inputAsDividedLineGroups(20, "example"))
        val tileMap = TileMap(tiles)
        tileMap.solve()
        assertEquals(20899048083289L, tileMap.star1Solution)
    }
}