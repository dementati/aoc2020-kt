package com.dementati.aoc2020.day13

import com.dementati.aoc2020.filereaders.inputAsLines
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day13Test {
    @Test
    fun example1() {
        assertEquals(295, solveStar1(inputAsLines(13, "example")))
    }

    @Test
    fun star1() {
        assertEquals(4135, solveStar1(inputAsLines(13)))
    }
}