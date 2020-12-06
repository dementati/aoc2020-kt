package com.dementati.aoc2020.day2

import com.dementati.aoc2020.filereaders.inputAsLines

fun main() {
    val pattern = "(\\d+)-(\\d+) ([a-z]): ([a-z]+)".toRegex()
    var count = 0
    inputAsLines(2)
        .map {
            pattern.matchEntire(it)
                ?.destructured
                ?.let { (min, max, lstr, p) ->
                    val l = lstr[0]
                    val lc = HashMap<Char, Int>()
                    p.forEach {
                        if (!lc.containsKey(it)) {
                            lc.put(it, 0)
                        }
                        val v = lc.get(it)!!
                        lc.put(it, v + 1)
                    }

                    if (lc.containsKey(l) && lc[l]!! >= min.toInt() && lc[l]!! <= max.toInt()) {
                        count++
                    }
                }
        }

    println(count)
}