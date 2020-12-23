package com.dementati.aoc2020.day23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day23Test {
    @Test
    fun example1() {
        assertEquals("67384529", solveStar1("389125467"))
    }

    @Test
    fun example2() {
        assertEquals(149245887792L, solveStar2("389125467"))
    }
}