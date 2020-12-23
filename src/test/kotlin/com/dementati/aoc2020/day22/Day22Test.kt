package com.dementati.aoc2020.day22

import com.dementati.aoc2020.filereaders.inputAsDividedLineGroups
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day22Test {
    @Test
    fun example1() {
        assertEquals(306L, solveStar1(inputAsDividedLineGroups(22, "example")))
    }

    @Test
    fun example2() {
        assertEquals(291L, solveStar2(inputAsDividedLineGroups(22, "example")))
    }

    @Test
    fun star2Infinite() {
        solveStar2(inputAsDividedLineGroups(22, "infinite"))
    }
}