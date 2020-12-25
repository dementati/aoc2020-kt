package com.dementati.aoc2020.day25

fun calculateLoopSize(subject: Long, solution: Long): Long {
    var value = 1L
    var loopSize = 0L
    while (value != solution) {
        value = value * subject % 20201227L
        loopSize++
    }

    return loopSize
}

fun transform(subject: Long, loopSize: Long): Long {
    var value = 1L
    (1..loopSize).forEach {
        value = value * subject % 20201227L
    }
    return value
}

fun solveStar1(cardKey: Long, doorKey: Long): Long {
    val doorLoopSize = calculateLoopSize(7, doorKey)
    return transform(cardKey, doorLoopSize)
}

