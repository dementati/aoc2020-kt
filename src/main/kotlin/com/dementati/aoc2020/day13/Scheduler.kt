package com.dementati.aoc2020.day13

import kotlin.math.ceil

fun solveStar1(lines: List<String>): Long {
    val earliestDeparture = lines[0].toLong()
    val (bus, wait) = lines[1].split(",")
        .filter { it != "x" }
        .map { it.toLong() }
        .map { it to ceil(earliestDeparture.toDouble() / it).toLong() * it - earliestDeparture }
        .minByOrNull { it.second }
        ?: throw IllegalArgumentException("Data couldn't be parsed")
    return bus * wait
}