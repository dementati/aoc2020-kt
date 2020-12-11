package com.dementati.aoc2020.day10

import com.dementati.aoc2020.filereaders.inputAsIntegers
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day10Test {
    @Test
    fun day1example1() {
        assertEquals(7*5, solveStar1(inputAsIntegers(10, "example1")))
    }

    @Test
    fun day1example2() {
        assertEquals(22*10, solveStar1(inputAsIntegers(10, "example2")))
    }

    @Test
    fun day2example1() {
        assertEquals(8, solveStar2(inputAsIntegers(10, "example1")))
    }

    @Test
    fun day2example2() {
        assertEquals(19208, solveStar2(inputAsIntegers(10, "example2")))
    }

    @Test
    fun sanityCheck() {
        assertTrue(1000_000_000_000 < solveStar2(inputAsIntegers(10)))
    }

    @Test
    fun sanityCheck2() {
        assertNotEquals(2071986176, solveStar2(inputAsIntegers(10)))
    }
}