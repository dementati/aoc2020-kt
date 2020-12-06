package com.dementati.aoc2020.day5

import com.dementati.aoc2015.inputAsLines
import java.lang.IllegalArgumentException

fun main() {
    val allSeats = (0..127 * 8 + 7).toSet()

    val result = inputAsLines(5)
        .map { locateSeat(it) }
        .toSet()

    println(allSeats.minus(result))
}