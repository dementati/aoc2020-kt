package com.dementati.aoc2020.day15

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day15Test {
    @Test
    fun example1() {
        assertEquals(436, play(listOf(0,3,6), 2020L))
    }
}