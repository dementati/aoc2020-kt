package com.dementati.aoc2020.day7

import com.dementati.aoc2020.filereaders.inputAsLines
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GraphTest {
    @Test
    fun testGraphParsing() {
        val lines = listOf("dull bronze bags contain 2 plaid aqua bags, 4 shiny magenta bags, 2 faded green bags, 3 dotted gold bags.")
        val graph = Graph(lines)
        assertEquals(5, graph.vertices.size)
    }

    @Test
    fun testPathsTo() {
        val graph = Graph(inputAsLines(7, "example"))
        assertEquals(4, graph.allWithPathsTo("shiny gold").size)
    }

    @Test
    fun testBagCount() {
        val graph = Graph(inputAsLines(7, "example"))
        assertEquals(32, graph.computeBagCount("shiny gold"))
    }

    @Test
    fun testBagCount2() {
        val graph = Graph(inputAsLines(7, "example2"))
        assertEquals(126, graph.computeBagCount("shiny gold"))
    }
}