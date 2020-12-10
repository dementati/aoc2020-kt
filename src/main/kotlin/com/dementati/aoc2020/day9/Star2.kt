package com.dementati.aoc2020.day9

import com.dementati.aoc2020.filereaders.inputAsLines
import com.dementati.aoc2020.findContiguousSet
import com.dementati.aoc2020.findNonAdding

fun main() {
    val numbers = inputAsLines(9).map { it.toLong() }
    println(findContiguousSet(numbers, 57195069))
}