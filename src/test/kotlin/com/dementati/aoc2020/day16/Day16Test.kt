package com.dementati.aoc2020.day16

import com.dementati.aoc2020.filereaders.inputAsLineGroups
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day16Test {
    @Test
    fun example1() {
        assertEquals(71, solveStar1(InputSpec(inputAsLineGroups(16, "example"))))
    }

    @Test
    fun example2() {
        solveStar2(InputSpec(inputAsLineGroups(16, "example2")))
    }
}