package com.dementati.aoc2015

import java.io.File

fun inputAsString(day: Int): String {
    return inputAsString(day, "input")
}

fun inputAsString(day: Int, name: String): String {
    val path = "input/day$day/$name.txt"
    return File(path).readText(Charsets.UTF_8)
}

fun inputAsLines(day: Int): List<String> {
    return inputAsLines(day, "input")
}

fun inputAsLines(day: Int, name: String): List<String> {
    val path = "input/day$day/$name.txt"
    return File(path).readLines()
}
