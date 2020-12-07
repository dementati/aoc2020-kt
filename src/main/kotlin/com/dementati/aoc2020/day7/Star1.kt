package com.dementati.aoc2020.day7

import com.dementati.aoc2020.filereaders.inputAsLines

fun main() {
    val graph = Graph(inputAsLines(7))
    println(graph.allWithPathsTo("shiny gold").size)
}