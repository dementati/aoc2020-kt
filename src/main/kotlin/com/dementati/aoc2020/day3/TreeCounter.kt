package com.dementati.aoc2020.day3

fun countTrees(lines: List<String>, xstep: Int, ystep: Int): Long {
    val forest = Forest(lines, xstep, ystep)

    var x = 0
    var y = 0

    var count = 0L
    while (y < forest.height) {
        x += xstep
        y += ystep

        if (forest.get(x, y)) {
            count++
        }
    }

    return count
}