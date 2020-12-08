package com.dementati.aoc2020.day8

import com.dementati.aoc2020.filereaders.inputAsLines
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day8Test {
    @Test
    fun testFindTerminatingProgram() {
        assertEquals(8, findTerminatingProgram(inputAsLines(8, "example")))
        assertEquals(501, findTerminatingProgram(inputAsLines(8)))
    }
}