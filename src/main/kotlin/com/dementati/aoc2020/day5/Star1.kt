package com.dementati.aoc2020.day5

import com.dementati.aoc2015.inputAsLines
import java.lang.IllegalArgumentException

fun main() {
    val result = inputAsLines(5)
        .map { locateSeat(it) }
        .maxOrNull()
        ?: throw IllegalArgumentException("No max seat found")

    println(result)
}