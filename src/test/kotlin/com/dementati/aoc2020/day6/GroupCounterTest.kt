package com.dementati.aoc2020.day6

import com.dementati.aoc2020.filereaders.inputAsString
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GroupCounterTest {
    @Test
    fun testExample1() {
        assertEquals(11, countGroupsAny(6, "example"))
    }

    @Test
    fun testExample2() {
        assertEquals(6, countGroupsAll(6, "example"))
    }

    @Test
    fun testInput1() {
        assertEquals(6587, countGroupsAny(6, "input"))
    }

    @Test
    fun testInput2() {
        assertEquals(3235, countGroupsAll(6, "input"))
    }
}