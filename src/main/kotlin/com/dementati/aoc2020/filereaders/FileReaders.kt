package com.dementati.aoc2015

import java.io.File

fun inputAsString(day: Int): String {
    val path = "input/day$day/input.txt"
    return File(path).readText(Charsets.UTF_8)
}

fun inputAsLines(day: Int): List<String> {
    val path = "input/day$day/input.txt"
    return File(path).readLines()
}
