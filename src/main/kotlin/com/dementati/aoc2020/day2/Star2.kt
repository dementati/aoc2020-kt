package com.dementati.aoc2020.day2

import com.dementati.aoc2020.filereaders.inputAsLines

fun main() {
    val pattern = "(\\d+)-(\\d+) ([a-z]): ([a-z]+)".toRegex()
    var count = 0
    inputAsLines(2)
        .map {
            pattern.matchEntire(it)
                ?.destructured
                ?.let { (bstr, estr, lstr, p) ->
                    val b = bstr.toInt()
                    val e = estr.toInt()
                    val l = lstr[0]

                    val bvalid = b <= p.length && p[b - 1] == l
                    val evalid = e <= p.length && p[e - 1] == l

                    if (!(bvalid && evalid) && (bvalid || evalid)) {
                        count++
                    }
                }
        }

    println(count)
}