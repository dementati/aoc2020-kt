package com.dementati.aoc2020.day25

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day25Test {
    @Test
    fun example1() {
        assertEquals(8, calculateLoopSize(7, 5764801))
        assertEquals(11, calculateLoopSize(7, 17807724))
        assertEquals(14897079L, solveStar1(5764801L, 17807724L))
    }
}