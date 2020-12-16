package com.dementati.aoc2020.day15

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day15Test {
    @Test
    fun example1() {
        assertEquals(436, play(listOf(0,3,6), 2020L))
    }

    @Test
    fun star2() {
        assertEquals(10613991, play(listOf(1,0,18,10,19,6), 30000000L))
    }
}