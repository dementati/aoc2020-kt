package com.dementati.aoc2020.day1

import com.dementati.aoc2015.inputAsLines

fun main() {
    val numbers = inputAsLines(1)
        .map { l -> l.toInt() }

    numbers.forEach { x ->
        numbers.forEach { y ->
            if (x + y == 2020) {
                println(x * y)
            }
        }
    }
}