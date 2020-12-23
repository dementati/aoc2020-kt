package com.dementati.aoc2020.filereaders

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

fun inputAsLineGroups(day: Int): List<String> {
    return inputAsLineGroups(day, "input")
}

fun inputAsLineGroups(day: Int, name: String): List<String> {
    return inputAsString(day, name).trim().split("""\r\n\r\n|\n\n""".toRegex())
}

fun inputAsDividedLineGroups(day: Int): List<List<String>> {
    return inputAsDividedLineGroups(day, "input")
}

fun inputAsDividedLineGroups(day: Int, name: String): List<List<String>> {
    return inputAsLineGroups(day, name)
        .map { it.split("""\n|\r\n""".toRegex()) }
}

fun inputAsIntegers(day: Int): List<Int> {
    return inputAsIntegers(day, "input")
}

fun inputAsIntegers(day: Int, name: String): List<Int> {
    return inputAsLines(day, name).map { it.toInt() }
}
