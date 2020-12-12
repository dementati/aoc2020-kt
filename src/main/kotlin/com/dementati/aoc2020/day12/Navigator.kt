package com.dementati.aoc2020.day12

import kotlin.math.*

typealias Pos = Pair<Long, Long>

fun navigate(lines: List<String>): Long {
    val pattern = """([NSEWLRF])(\d+)""".toRegex()

    var current = 0L to 0L
    var dir = 1L to 0L

    lines.forEach {
        pattern.matchEntire(it)
            ?.destructured
            ?.let { (op, pstr) ->
                val p = pstr.toLong()
                val (x, y) = current
                when(op) {
                    "N" -> current = x to y - p
                    "S" -> current = x to y + p
                    "E" -> current = x + p to y
                    "W" -> current = x - p to y
                    "R" -> dir = turnRight(dir, p)
                    "L" -> dir = turnLeft(dir, p)
                    "F" -> {
                        val (dx, dy) = dir
                        current = x + p*dx to y + p*dy
                    }
                }
            }
    }

    return manhattan(current)
}

fun navigate2(lines: List<String>): Long {
    val pattern = """([NSEWLRF])(\d+)""".toRegex()

    var current = 0L to 0L
    var waypoint = 10L to -1L

    lines.forEach {
        pattern.matchEntire(it)
            ?.destructured
            ?.let { (op, pstr) ->
                val p = pstr.toLong()
                val (wx, wy) = waypoint
                when(op) {
                    "N" -> waypoint = wx to wy - p
                    "S" -> waypoint = wx to wy + p
                    "E" -> waypoint = wx + p to wy
                    "W" -> waypoint = wx - p to wy
                    "R" -> waypoint = turnRight(waypoint, p)
                    "L" -> waypoint = turnLeft(waypoint, p)
                    "F" -> {
                        val (x, y) = current
                        current = x + p*wx to y + p*wy
                    }
                }
            }
    }

    return manhattan(current)
}

fun turnRight(dir: Pos, d: Long): Pos {
    val (x1, y1) = dir
    val r = PI * d.toDouble() / 180.0
    val cs = cos(r)
    val sn = sin(r)
    val x2 = x1 * cs - y1 * sn
    val y2 = x1 * sn + y1 * cs
    return round(x2).toLong() to round(y2).toLong()
}

fun turnLeft(dir: Pos, d: Long): Pos {
    return turnRight(dir, -d)
}

fun manhattan(pos: Pos): Long {
    return abs(pos.first) + abs(pos.second)
}