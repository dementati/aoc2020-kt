package com.dementati.aoc2020.day17

import com.dementati.aoc2020.filereaders.inputAsLines

fun main() {
    val automata = CellAutomata(inputAsLines(17))
    automata.evolve(6)
    println(automata.active)
}