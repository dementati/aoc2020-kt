package com.dementati.aoc2020.day4

import com.dementati.aoc2020.filereaders.inputAsLineGroups
import com.dementati.aoc2020.filereaders.inputAsString
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PassportValidatorTest {
    @Test
    fun testExample1() {
        assertEquals(2, countValidPassports(inputAsLineGroups(4, "example")))
    }

    @Test
    fun test1() {
        assertEquals(1, countValidPassports(inputAsLineGroups(4, "test1")))
    }

    @Test
    fun testExample2() {
        assertEquals(4, countValidPassports2(inputAsLineGroups(4, "example2")))
    }

    @Test
    fun testSolution2() {}
}