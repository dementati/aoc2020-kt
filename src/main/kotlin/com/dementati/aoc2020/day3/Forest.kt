package com.dementati.aoc2020.day3

import kotlin.math.ceil

class Forest constructor(lines: List<String>, xstep: Int, ystep: Int) {
    val coordinates = HashSet<Pair<Int, Int>>()
    val height = lines.size

    init {
        val h = lines.size
        val w = lines[0].length
        val r = ceil(((h.toDouble() / ystep) * xstep) / w).toInt()

        lines.forEachIndexed { y, line ->
            (0..(w*r)).forEach { x ->
                val i = x % w

                if (line[i] == '#') {
                    coordinates.add(x to y)
                }
            }
        }
    }

    fun get(x: Int, y: Int): Boolean {
        return coordinates.contains(x to y)
    }
}