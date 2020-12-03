package com.dementati.aoc2020.day3

import kotlin.math.ceil

class Forest constructor(lines: List<String>, val xstep: Int, val ystep: Int) {
    val coordinates = HashSet<Pair<Int, Int>>()
    val height = lines.size

    var rx = 0
    var ry = 0

    init {
        println("# xstep: $xstep, ystep: $ystep")

        val h = lines.size
        val w = lines[0].length
        val r = ceil(((h.toDouble() / ystep) * xstep) / w).toInt()

        lines.forEachIndexed { y, line ->
            (0..(w*r)).forEach { x ->
                val i = x % w

                if (x == rx + xstep && y == ry + ystep) {
                    rx += xstep
                    ry += ystep

                    if (line[i] == '#') {
                        print("X")
                    } else {
                        print("O")
                    }
                } else {
                    print(line[i])
                }

                if (line[i] == '#') {
                    coordinates.add(x to y)
                }
            }
            println()
        }
    }

    fun get(x: Int, y: Int): Boolean {
        return coordinates.contains(x to y)
    }
}