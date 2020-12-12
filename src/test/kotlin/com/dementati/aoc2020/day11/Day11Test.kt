package com.dementati.aoc2020.day11

import com.dementati.aoc2020.filereaders.inputAsLines
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day11Test {
    @Test
    fun testExample() {
        assertEquals(37, Grid(inputAsLines(11, "example")).solveDay1())
    }

    @Test
    fun testStar1() {
        assertEquals(2113, Grid(inputAsLines(11)).solveDay1())
    }

    @Test
    fun testExample2() {
        assertEquals(26, Grid(inputAsLines(11, "example")).solveDay2())
    }

}