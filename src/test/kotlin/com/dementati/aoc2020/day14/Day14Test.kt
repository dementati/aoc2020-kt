package com.dementati.aoc2020.day14

import com.dementati.aoc2020.filereaders.inputAsLines
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day14Test {
    @Test
    fun testParseMask() {
        assertEquals(0b1111L to 0b0000L, parseMask("XXXX"))
        assertEquals(0b1110L to 0b0000L, parseMask("XXX0"))
        assertEquals(0b1111L to 0b0001L, parseMask("XXX1"))
        assertEquals(0b1010L to 0b1010L, parseMask("1010"))
    }

    @Test
    fun testApplyMask() {
        val mask = parseMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")
        assertEquals(73L, applyMask(11L, mask))
        assertEquals(101L, applyMask(101L, mask))
        assertEquals(64L, applyMask(0L, mask))
    }

    @Test
    fun testExampleStar1() {
        assertEquals(165, solveStar1(inputAsLines(14, "example")))
    }

    @Test
    fun testApplyQuantumMask() {
        assertEquals(
            setOf(0b011010L, 0b011011L, 0b111010L, 0b111011L),
            applyQuantumMask(0b101010, 0, "X1001X")
        )
    }

    @Test
    fun testExampleStar2() {
        assertEquals(208, solveStar2(inputAsLines(14, "example2")))
    }
}