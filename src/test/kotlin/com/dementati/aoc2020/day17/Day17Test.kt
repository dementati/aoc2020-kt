package com.dementati.aoc2020.day17

import com.dementati.aoc2020.filereaders.inputAsLines
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day17Test {
    @Test
    fun example1() {
        val automata = CellAutomata(inputAsLines(17, "example"))
        automata.evolve(6)
        assertEquals(112, automata.active)
    }

    @Test
    fun example2() {
        val automata = CellAutomata4(inputAsLines(17, "example"))
        automata.evolve(6)
        assertEquals(848, automata.active)
    }
}