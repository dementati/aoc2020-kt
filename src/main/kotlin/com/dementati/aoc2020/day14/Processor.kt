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

fun applyQuantumMask(address: Long, index: Int, mask: String): Set<Long> {
    var newAddress = address
    var i = index
    while (i != mask.length) {
        val bi = mask.length - i - 1
        val bm = (1L shl bi)
        when (mask[i]) {
            '1' -> {
                newAddress = newAddress or bm
            }
            'X' -> {
                val newAddress1 = newAddress or bm
                val newAddress2 = newAddress and bm.inv()
                return applyQuantumMask(newAddress1, i + 1, mask)
                    .union(
                        applyQuantumMask(newAddress2, i + 1, mask)
                    )
            }
        }

        i++
    }

    return setOf(newAddress)
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

fun solveStar2(lines: List<String>): Long {
    val maskPattern = """mask = (\w+)""".toRegex()
    val memPattern = """mem\[(\d+)\] = (\d+)""".toRegex()

    var mask = ""
    val memory = HashMap<Long, Long>()
    lines.forEach {
        if (maskPattern.matches(it)) {
            maskPattern.matchEntire(it)
                ?.destructured
                ?.let { (maskStr) ->
                    mask = maskStr
                }
        } else if (memPattern.matches(it)) {
            memPattern.matchEntire(it)
                ?.destructured
                ?.let { (iStr, valueStr) ->
                    val i = iStr.toLong()
                    val value = valueStr.toLong()
                    applyQuantumMask(i, 0, mask)
                        .forEach { qi -> memory[qi] = value }
                }
        }
    }

    return memory.values.sum()
}