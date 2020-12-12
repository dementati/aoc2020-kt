package com.dementati.aoc2020.day12

import com.dementati.aoc2020.filereaders.inputAsLines
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day12Test {
    @Test
    fun testExample() {
        assertEquals(25, navigate(inputAsLines(12, "example")))
    }

    @Test
    fun testExample2() {
        assertEquals(286, navigate2(inputAsLines(12, "example")))
    }

    @Test
    fun testRotation() {
        assertEquals(7L to -5L, turnLeft(-5L to -7L, 270L))
    }

    @Test
    fun testStar1() {
        assertEquals(381, navigate(inputAsLines(12)))
    }

    @Test
    fun testStar2() {
        assertEquals(28591, navigate2(inputAsLines(12)))
    }
}