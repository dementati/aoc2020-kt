package com.dementati.aoc2020.day3

import com.dementati.aoc2015.inputAsLines

fun main() {
    val lines = inputAsLines(3)
    val count = countSlopes(lines)
    println(count)
}