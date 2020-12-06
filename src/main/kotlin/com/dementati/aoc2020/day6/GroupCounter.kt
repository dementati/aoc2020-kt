package com.dementati.aoc2020.day6

import com.dementati.aoc2020.filereaders.inputAsLineGroups

fun countGroupsAny(day: Int, name: String): Int {
    return inputAsLineGroups(day, name)
        .map { group ->
            group.split("""\s+""".toRegex())
                .map { line -> line.toSet() }
                .reduce { a, b -> a.union(b) }
                .size
        }
        .sum()
}

fun countGroupsAll(day: Int, name: String): Int {
    return inputAsLineGroups(day, name)
        .map { group ->
            group.split("""\s+""".toRegex())
                .map { line -> line.toSet() }
                .reduce { a, b -> a.intersect(b) }
                .size
        }
        .sum()
}