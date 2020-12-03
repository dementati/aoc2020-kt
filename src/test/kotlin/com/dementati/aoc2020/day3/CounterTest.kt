package com.dementati.aoc2020.day3

import com.dementati.aoc2015.inputAsLines
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CounterTest {
    @Test
    fun countTreesExample() {
        // GIVEN
        val exampleLines = inputAsLines(3, "example")

        // WHEN
        val count = countTrees(exampleLines, 3, 1)

        // THEN
        assertEquals(7, count)
    }

    @Test
    fun countSlopesExample() {
        // GIVEN
        val exampleLines = inputAsLines(3, "example")

        // WHEN
        val count = countSlopes(exampleLines)

        // THEN
        assertEquals(336, count)
    }

    @Test
    fun test_1to1_1x1() {
        verify(0, 1 to 1, listOf("."))
        verify(0, 1 to 1, listOf("#"))

    }

    @Test
    fun test_1to1_2x2() {
        verify(0, 1 to 1, listOf(
            "..",
            ".."
        ))
        verify(0, 1 to 1, listOf(
            ".#",
            "#."
        ))
        verify(0, 1 to 1, listOf(
            ".#",
            "#."
        ))
        verify(0, 1 to 1, listOf(
            "##",
            "#."
        ))
        verify(1, 1 to 1, listOf(
            "..",
            ".#"
        ))
        verify(1, 1 to 1, listOf(
            ".#",
            "##"
        ))
        verify(1, 1 to 1, listOf(
            ".#",
            "##"
        ))
        verify(1, 1 to 1, listOf(
            "##",
            "##"
        ))
    }

    @Test
    fun test_2to1() {
        verify(0, 2 to 1, listOf(
            "."
        ))
        verify(1, 2 to 1, listOf(
            "...",
            "..#"
        ))
        verify(0, 2 to 1, listOf(
            ".##",
            "##.",
        ))
        verify(1, 2 to 1, listOf(
            ".",
            "#",
        ))
        verify(0, 2 to 1, listOf(
            ".",
            ".",
        ))
        verify(1, 2 to 1, listOf(
            "..",
            "#.",
        ))
        verify(0, 2 to 1, listOf(
            "..",
            ".#",
        ))
    }

    fun verify(expectedCount: Int, slope: Pair<Int, Int>, lines: List<String>) {
        // WHEN
        val count = countTrees(lines,slope.first, slope.second)

        // THEN
        assertEquals(expectedCount, count)
    }
}