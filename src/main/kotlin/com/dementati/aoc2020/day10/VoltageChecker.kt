package com.dementati.aoc2020.day10

fun adjustAdapters(adapters: List<Int>): List<Int> {
    val adjAdapters = adapters.toMutableList()
    adjAdapters.add(0)
    adjAdapters.add(adapters.maxOrNull()!! + 3)
    return adjAdapters.sorted()
}

fun findDifferenceCounts(adapters: List<Int>): MutableMap<Int, Int> {
    return adjustAdapters(adapters).sorted()
        .zipWithNext { a, b -> b - a }
        .groupBy { it }
        .entries.associate { (k, v) -> k to v.size }
        .toMutableMap()
}

fun solveStar1(adapters: List<Int>): Int {
    val diffCounts = findDifferenceCounts(adapters)
    return diffCounts[1]!! * diffCounts[3]!!
}

fun isRemovable(adapters: List<Int>, index: Int): Boolean {
    return index > 0
        && index < adapters.lastIndex
        && adapters[index + 1] - adapters[index - 1] <= 3
}

fun waysToRemove(adapters: List<Int>, depth: Int, cache: MutableMap<List<Int>, Int>): Int {
    if (cache.containsKey(adapters)) {
        return cache[adapters]!!
    }

    val result = (0 until adapters.lastIndex)
        .map { i ->
            if (depth < 10) {
                // println("${"\t".repeat(depth)}Processing index $i out of ${adapters.lastIndex}")
            }

            if (isRemovable(adapters, i)) {
                val adjAdapters = adapters.toMutableList()
                adjAdapters.removeAt(i)
                1 + waysToRemove(adjAdapters.subList(i - 1, adjAdapters.size), depth + 1, cache)
            } else {
                0
            }
        }
        .sum()

    cache[adapters] = result

    return result
}

fun solveStar2(adapters: List<Int>): Int {
    return waysToRemove(adjustAdapters(adapters), 0, HashMap()) + 1
}