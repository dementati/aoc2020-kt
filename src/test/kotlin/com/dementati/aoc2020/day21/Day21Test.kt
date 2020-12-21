package com.dementati.aoc2020.day21

import com.dementati.aoc2020.filereaders.inputAsLines
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day21Test {
    @Test
    fun example1() {
        assertEquals(5, solveStar1(inputAsLines(21, "example")))
    }

    @Test
    fun example2() {
        assertEquals("mxmxvkd,sqjhc,fvjkl", solveStar2(inputAsLines(21, "example")))
    }
}