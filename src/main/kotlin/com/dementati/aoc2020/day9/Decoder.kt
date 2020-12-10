package com.dementati.aoc2020

import java.lang.IllegalArgumentException

fun findNonAdding(numbers: List<Long>, seq: Int): Long {
    (seq..numbers.lastIndex).forEach { i ->
        val n = numbers[i]
        val preamble = numbers.subList(i - seq, i)
        val s = preamble
            .map { n - it }
            .filter { n != it + it }
            .toSet()

        if (preamble.all { !s.contains(it) }) {
            return n
        }
    }

    throw IllegalArgumentException("Invalid encoding")
}

fun findContiguousSet(numbers: List<Long>, target: Long): Long {
    (0..numbers.lastIndex).forEach { start ->
        var sum = 0L
        for (i in start..numbers.lastIndex) {
            sum += numbers[i]
            if (sum == target) {
                val cs = numbers.subList(start, i + 1)
                return cs.maxOrNull()!! + cs.minOrNull()!!
            } else if (sum > target) {
                break
            }
        }
    }

    throw IllegalArgumentException("Invalid encoding")
}