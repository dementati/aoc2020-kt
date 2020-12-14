package com.dementati.aoc2020.day14

fun parseMask(mask: String): Pair<Long, Long> {
    var andMask = 0L
    var orMask = 0L
    mask.forEach {
        andMask = andMask shl 1
        orMask = orMask shl 1

        when (it) {
            'X' -> {
                andMask = (1L or andMask)
            }
            '1' -> {
                andMask = (1L or andMask)
                orMask = (1L or orMask)
            }
        }
    }

    return andMask to orMask
}

fun parseQuantumMask(prefix: Long, mask: String): List<Long> {
    val result: List<Long> = mutableListOf()

    var newPrefix = prefix
    for (it in mask) {
        newPrefix = newPrefix shl 1
        when (it) {
            '1' -> {
                newPrefix = newPrefix or 1
            }
            'X' -> {
                return parseQuantumMask(newPrefix, mask)
            }
        }
    }

    return result
}

fun applyMask(value: Long, mask: Pair<Long, Long>): Long {
    var result = value and mask.first
    result = result or mask.second
    return result
}

fun solveStar1(lines: List<String>): Long {
    val maskPattern = """mask = (\w+)""".toRegex()
    val memPattern = """mem\[(\d+)\] = (\d+)""".toRegex()

    var mask = 0L to 0L
    val memory = HashMap<Long, Long>()
    lines.forEach {
        if (maskPattern.matches(it)) {
            maskPattern.matchEntire(it)
                ?.destructured
                ?.let { (maskStr) ->
                    mask = parseMask(maskStr)
                }
        } else if (memPattern.matches(it)) {
            memPattern.matchEntire(it)
                ?.destructured
                ?.let { (iStr, valueStr) ->
                    val i = iStr.toLong()
                    val value = valueStr.toLong()
                    memory[i] = applyMask(value, mask)
                }
        }
    }

    return memory.values.sum()
}