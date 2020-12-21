package com.dementati.aoc2020.day20

import com.dementati.aoc2020.filereaders.inputAsDividedLineGroups
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TileMapTest {
    @Test
    fun test() {
        val tiles = listOf(
            Tile.fromLines(0, listOf(
                "####",
                "...#",
                "....",
                "...#"
            )),
            Tile.fromLines(0, listOf(
                "#..#",
                "#...",
                "...#",
                "#.#.",
            )),
            Tile.fromLines(0, listOf(
                "....",
                "....",
                "....",
                "#..#"
            )),
            Tile.fromLines(0, listOf(
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

        val image = tileMap.image
        assertEquals(2, image.countAllSeamonsters())
        assertEquals(273, image.solveStar2())
    }

    @Test
    fun star1() {
        val tiles = parseInput(inputAsDividedLineGroups(20))
        val tileMap = TileMap(tiles)
        tileMap.solve()
        assertEquals(7492183537913L, tileMap.star1Solution)
    }
}