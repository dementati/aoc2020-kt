package com.dementati.aoc2020.day20

import com.dementati.aoc2020.Direction
import com.dementati.aoc2020.Rotation
import com.dementati.aoc2020.Tile
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TileTest {
    @Test
    fun `get 1x1 empty`() {
        val tile = Tile(listOf("."))
        assertFalse(tile.get(0, 0))
    }

    @Test
    fun `get 1x1 filled`() {
        val tile = Tile(listOf("#"))
        assertTrue(tile.get(0, 0))
    }

    @Test
    fun `get 2x2`() {
        val tile = Tile(listOf(
            ".#",
            ".#"
        ))
        assertFalse(tile.get(0, 0))
        assertTrue(tile.get(1, 0))
        assertFalse(tile.get(0, 1))
        assertTrue(tile.get(1, 1))
    }

    @Test
    fun `get 2x2 flipped`() {
        val tile = Tile(listOf(
            ".#",
            ".#"
        ))
        tile.flipped = true
        assertTrue(tile.get(0, 0))
        assertFalse(tile.get(1, 0))
        assertTrue(tile.get(0, 1))
        assertFalse(tile.get(1, 1))
    }

    @Test
    fun `get 2x2 rotated 90 degrees`() {
        val tile = Tile(listOf(
            "##",
            ".."
        ))
        tile.rotation = Rotation.D90
        assertFalse(tile.get(0, 0))
        assertTrue(tile.get(1, 0))
        assertFalse(tile.get(0, 1))
        assertTrue(tile.get(1, 1))
    }

    @Test
    fun `get 2x2 rotated 180 degrees`() {
        val tile = Tile(listOf(
            "##",
            ".."
        ))
        tile.rotation = Rotation.D180
        assertFalse(tile.get(0, 0))
        assertFalse(tile.get(1, 0))
        assertTrue(tile.get(0, 1))
        assertTrue(tile.get(1, 1))
    }

    @Test
    fun `get 2x2 rotated 270 degrees`() {
        val tile = Tile(listOf(
            "##",
            ".."
        ))
        tile.rotation = Rotation.D270
        assertTrue(tile.get(0, 0))
        assertFalse(tile.get(1, 0))
        assertTrue(tile.get(0, 1))
        assertFalse(tile.get(1, 1))
    }

    @Test
    fun `get 2x2 flipped and rotated 90 degrees`() {
        val tile = Tile(listOf(
            "#.",
            ".."
        ))
        tile.flipped = true
        tile.rotation = Rotation.D90
        assertFalse(tile.get(0, 0))
        assertFalse(tile.get(1, 0))
        assertFalse(tile.get(0, 1))
        assertTrue(tile.get(1, 1))
    }

    @Test
    fun `get 2x2 flipped and rotated 180 degrees`() {
        val tile = Tile(listOf(
            "#.",
            ".."
        ))
        tile.flipped = true
        tile.rotation = Rotation.D180
        assertFalse(tile.get(0, 0))
        assertFalse(tile.get(1, 0))
        assertTrue(tile.get(0, 1))
        assertFalse(tile.get(1, 1))
    }

    @Test
    fun `get 2x2 flipped and rotated 270 degrees`() {
        val tile = Tile(listOf(
            "#.",
            ".."
        ))
        tile.flipped = true
        tile.rotation = Rotation.D270
        assertTrue(tile.get(0, 0))
        assertFalse(tile.get(1, 0))
        assertFalse(tile.get(0, 1))
        assertFalse(tile.get(1, 1))
    }

    @Test
    fun `get 3x3 flipped and rotated 90 degrees`() {
        val tile = Tile(listOf(
            "##.",
            "...",
            "..."
        ))
        tile.flipped = true
        tile.rotation = Rotation.D90
        assertFalse(tile.get(2, 0))
        assertTrue(tile.get(2, 2))
        assertTrue(tile.get(2, 1))
    }

    @Test
    fun `match 2x2`() {
        val tile1 = Tile(listOf(
            ".#",
            ".."
        ))

        val tile2 = Tile(listOf(
            "#.",
            ".."
        ))

        assertTrue(tile1.matches(tile2, Direction.RIGHT))
        assertTrue(tile1.matches(tile2, Direction.LEFT))
        assertFalse(tile1.matches(tile2, Direction.UP))
        assertFalse(tile1.matches(tile2, Direction.DOWN))
    }

    @Test
    fun `match 2x2 flipped and rotated`() {
        val tile1 = Tile(listOf(
            ".#",
            ".."
        ))

        tile1.flipped = true
        tile1.rotation = Rotation.D90

        val tile2 = Tile(listOf(
            "#.",
            ".."
        ))
        tile2.rotation = Rotation.D180

        assertFalse(tile1.matches(tile2, Direction.RIGHT))
        assertFalse(tile1.matches(tile2, Direction.LEFT))
        assertTrue(tile1.matches(tile2, Direction.UP))
        assertTrue(tile1.matches(tile2, Direction.DOWN))
    }

    @Test
    fun `match 3x3`() {
        val tile1 = Tile(listOf(
            ".##",
            "...",
            "#.#"
        ))

        val tile2 = Tile(listOf(
            "#.#",
            "...",
            "#.."
        ))

        assertTrue(tile1.matches(tile2, Direction.RIGHT))
        assertFalse(tile1.matches(tile2, Direction.LEFT))
        assertFalse(tile1.matches(tile2, Direction.UP))
        assertTrue(tile1.matches(tile2, Direction.DOWN))
    }
}