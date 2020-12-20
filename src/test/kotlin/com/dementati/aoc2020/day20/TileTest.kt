package com.dementati.aoc2020.day20

import com.dementati.aoc2020.Tile
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class TileTest {
    @Test
    fun `get 1x1 empty`() {
        val tile = Tile(listOf("."))
        assertFalse(tile.get(0, 0))
    }
}