package com.dementati.aoc2020.day1

import com.dementati.aoc2020.filereaders.inputAsLines

fun main() {
    val numbers = inputAsLines(1)
        .map { l -> l.toInt() }

    numbers.forEach { x ->
        numbers.forEach { y ->
            numbers.forEach { z ->
                if (x + y + z == 2020) {
                    println(x * y * z)
                }
            }
        }
    }
}