package com.dementati.aoc2020.day21

import java.lang.IllegalArgumentException

fun parseInput(lines: List<String>): Map<String, List<Set<String>>> {
    val result = mutableMapOf<String, MutableList<Set<String>>>()

    lines.forEach { line ->
        """(.*) \(contains (.*)\)""".toRegex().matchEntire(line)
            ?.destructured
            ?.also { (ingredientStr, allergenStr) ->
                val ingredients = ingredientStr.split(" ").toSet()
                allergenStr.split(", ").forEach { allergen ->
                    result.putIfAbsent(allergen, mutableListOf())
                    result[allergen]!!.add(ingredients)
                }
            }
    }

    return result
}

fun getCompleteIngredientList(lines: List<String>): List<String> {
    return lines.flatMap { line ->
        """(.*) \(contains .*\)""".toRegex().matchEntire(line)
            ?.destructured
            ?.let { (ingredientStr) -> ingredientStr.split(" ").toList() }
            ?: throw IllegalArgumentException("Couldn't parse: $line")
    }
}

fun deriveAllergens(data: Map<String, List<Set<String>>>): Map<String, String> {
    val result = mutableMapOf<String, String>()

    var remaining = data
    while (remaining.isNotEmpty()) {
        for ((allergen, candidateSets) in remaining.entries) {
            val candidateIntersection = candidateSets.reduce { a, b -> a.intersect(b) }
            if (candidateIntersection.size == 1) {
                val ingredient = candidateIntersection.first()
                result[ingredient] = allergen
                remaining = removeAllergenAndIngredient(remaining, allergen, ingredient)
            }
        }
    }

    return result
}

fun removeAllergenAndIngredient(
    data: Map<String, List<Set<String>>>,
    allergen: String,
    ingredient: String
): Map<String, List<Set<String>>> {
    return data.entries
        .filter { (k, _) -> k != allergen  }
        .map { (k, v) ->
            k to v.map { candidateSet -> candidateSet.minus(ingredient) }
        }
        .toMap()
}

fun solveStar1(lines: List<String>): Int {
    val data = parseInput(lines)
    val result = deriveAllergens(data)
    val ingredients = getCompleteIngredientList(lines)
    return ingredients.count { !result.containsKey(it) }
}

fun solveStar2(lines: List<String>): String {
    val data = parseInput(lines)
    val result = deriveAllergens(data)
    return result.entries.sortedBy { (_, v) -> v }
        .map { (k, _) -> k }
        .joinToString(",")
}