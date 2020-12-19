package com.dementati.aoc2020.day19

import com.dementati.aoc2020.filereaders.inputAsDividedLineGroups
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day19Test {
    @Test
    fun expandPattern() {
        assertEquals("a", expandPattern(listOf("0: \"a\"")))
        assertEquals(
            "a",
            expandPattern(listOf(
                "0: 1",
                "1: \"a\"",
            ))
        )
        assertEquals(
            "ab",
            expandPattern(listOf(
                "0: 1 2",
                "1: \"a\"",
                "2: \"b\"",
            ))
        )
        assertEquals(
            """(?:ab|ba)""",
            expandPattern(listOf(
                "0: 1 2 | 2 1",
                "1: \"a\"",
                "2: \"b\"",
            ))
        )
        assertEquals(
            """a(?:ab|ba)""",
            expandPattern(listOf(
                "0: 1 2",
                "1: \"a\"",
                "2: 1 3 | 3 1",
                "3: \"b\"",
            ))
        )
    }

    @Test
    fun example1() {
        assertEquals(2, solveStar1(inputAsDividedLineGroups(19, "example")))
    }

    @Test
    fun example2() {
        assertEquals(12, solveStar2(inputAsDividedLineGroups(19, "example2")))
    }
}