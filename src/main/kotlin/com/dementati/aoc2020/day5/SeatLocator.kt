package com.dementati.aoc2020.day5

fun locateSeat(spec: String): Int {
    val row = locateRow(spec.substring(0, 7))
    val column = locateColumn(spec.substring(7))
    return row * 8 + column
}

fun locateRow(spec: String): Int {
    return locatePosition(spec, 0, 127, 'F')
}

fun locateColumn(spec: String): Int {
    return locatePosition(spec, 0, 7, 'L')
}

fun locatePosition(spec: String, min: Int, max: Int, down: Char): Int {
    assert(spec.isNotEmpty())
    val next = spec[0]

    return if (spec.length == 1) {
        assert(max - min == 1)
        if (next == down) min else max
    } else if (next == down) {
        locatePosition(spec.substring(1), min, min + (max - min) / 2, down)
    } else {
        locatePosition(spec.substring(1), min + (max - min) / 2 + 1, max, down)
    }
}