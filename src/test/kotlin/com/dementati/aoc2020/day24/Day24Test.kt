package com.dementati.aoc2020.day24

import com.dementati.aoc2020.filereaders.inputAsLines
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day24Test {
    @Test
    fun example1() {
        assertEquals(10, solveStar1(inputAsLines(24, "example")))
    }

    @Test
    fun example2() {
        assertEquals(2208, solveStar2(inputAsLines(24, "example")))
    }
}