package com.dementati.aoc2020.day22

import com.dementati.aoc2020.filereaders.inputAsDividedLineGroups
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day22Test {
    @Test
    fun example1() {
        assertEquals(306L, solveStar1(inputAsDividedLineGroups(22, "example")))
    }
}