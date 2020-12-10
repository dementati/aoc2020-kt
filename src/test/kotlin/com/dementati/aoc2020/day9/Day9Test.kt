package com.dementati.aoc2020.day9

import com.dementati.aoc2020.filereaders.inputAsLines
import com.dementati.aoc2020.findNonAdding
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day9Test {
    @Test
    fun testFindNonAdding() {
        val numbers = inputAsLines(9, "example").map { it.toLong() }
        assertEquals(127, findNonAdding(numbers, 5))
    }
}