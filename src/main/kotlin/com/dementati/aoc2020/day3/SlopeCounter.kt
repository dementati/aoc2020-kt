package com.dementati.aoc2020.day3

fun countSlopes(lines: List<String>): Long {
    val slopes = listOf(
        1 to 1,
        3 to 1,
        5 to 1,
        7 to 1,
        1 to 2
    )

    return slopes
        .map { (xstep, ystep) -> countTrees(lines, xstep, ystep) }
        .reduce { a, b -> a * b }
}