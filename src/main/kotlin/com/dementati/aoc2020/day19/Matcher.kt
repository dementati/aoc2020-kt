package com.dementati.aoc2020.day19

fun expandPattern(lines: List<String>, star2: Boolean = false): String {
    val rules = lines.map {
        val (k, v) = it.split(":")
        k.trim() to v.trim()
    }
        .toMap()

    return expandPattern("0", rules, mutableMapOf(), star2)
}

fun expandPattern(
    key: String,
    rules: Map<String, String>,
    cache: MutableMap<String, String>,
    star2: Boolean = false
): String {
    if (cache.containsKey(key)) {
        return cache[key]!!
    }

    if (star2) {
        if (key == "8") {
            val _42 = expandPattern("42", rules, cache, star2)
            cache[key] = """(?:$_42)+"""
            return cache[key]!!
        }

        if (key == "11") {
            val r42 = expandPattern("42", rules, cache, star2)
            val r31 = expandPattern("31", rules, cache, star2)
            val result = (1..5).map { """${r42.repeat(it)}${r31.repeat(it)}""" }
                .joinToString("|")
            cache[key] = """(?:$result)"""
            return cache[key]!!
        }
    }

    val rule = rules[key]!!

    fun expandLinear(pattern: String): String {
        return pattern.split(" ")
            .map { expandPattern(it, rules, cache, star2) }
            .joinToString("")
    }

    if (rule.contains("|")) {
        val (left, right) = rule.split("|")
        val leftExpanded = expandLinear(left.trim())
        val rightExpanded = expandLinear(right.trim())
        cache[key] = """(?:$leftExpanded|$rightExpanded)"""
        return cache[key]!!
    }

    """"([a-z])"""".toRegex().matchEntire(rule)
        ?.destructured
        ?.also { (c) ->
            cache[key] = c
            return c
        }

    cache[key] = expandLinear(rule)

    return cache[key]!!
}

fun solveStar1(groups: List<List<String>>): Int {
    val (rules, messages) = groups
    val pattern = expandPattern(rules).toRegex()
    return messages.count { pattern.matches(it) }
}

fun solveStar2(groups: List<List<String>>): Int {
    val (rules, messages) = groups
    val pattern = expandPattern(rules, true).toRegex()
    return messages.count { pattern.matches(it) }
}