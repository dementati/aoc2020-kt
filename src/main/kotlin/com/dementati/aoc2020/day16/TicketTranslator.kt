package com.dementati.aoc2020.day16

import java.lang.IllegalArgumentException

typealias Range = Pair<Long, Long>
typealias Ticket = List<Long>

data class FieldSpec(val name: String, val ranges: List<Range>)

val newlinePattern = """\n|\r\n""".toRegex()
val fieldPattern = """(.+): (\d+)-(\d+) or (\d+)-(\d+)""".toRegex()


class InputSpec(groups: List<String>) {
    val fieldSpecs: List<FieldSpec>
    val ticket: Ticket
    val otherTickets: List<Ticket>
    val allTickets: List<Ticket>
    val validTickets: List<Ticket>
        get() {
            return allTickets.filter { ticket ->
                ticket.all { field ->
                    fieldSpecs.any { fs ->
                        matches(fs, field)
                    }
                }
            }
        }

    init {
        assert(groups.size == 3)

        fieldSpecs = groups[0].split(newlinePattern)
            .map {
                fieldPattern.matchEntire(it)
                    ?.destructured
                    ?.let { (c, s1, e1, s2, e2) ->
                        FieldSpec(c, listOf(s1.toLong() to e1.toLong(), s2.toLong() to e2.toLong()))
                    }
                    ?: throw IllegalArgumentException("Couldn't parse field: $it")
            }


        ticket = groups[1].split(newlinePattern)
            .last()
            .split(",")
            .map { it.toLong() }

        otherTickets = groups[2].split(newlinePattern).drop(1)
            .map { line ->
                line.split(",")
                    .map { it.toLong() }
            }

        allTickets = mutableListOf()
        allTickets.add(ticket)
        allTickets.addAll(otherTickets)
    }

    fun getInvalidFields(ticket: Ticket): List<Long> {
        return ticket.filter { f ->
            fieldSpecs.all { fs ->
                fs.ranges.all { r ->
                    !contains(r, f)
                }
            }
        }
    }
}

fun contains(range: Range, n: Long): Boolean {
    return n >= range.first && n <= range.second
}

fun matches(fieldSpec: FieldSpec, n: Long): Boolean {
    return fieldSpec.ranges.any { contains(it, n) }
}

fun solveStar1(input: InputSpec): Long {
    return input.allTickets.flatMap { input.getInvalidFields(it) }.sum()
}

fun solveStar2(input: InputSpec): Long {
    val possibilities = mutableMapOf<Int, Set<FieldSpec>>()

    input.ticket.indices.forEach { index ->
        possibilities[index] = input.fieldSpecs
            .filter { fs ->
                input.validTickets.all { ticket ->
                    matches(fs, ticket[index])
                }
            }
            .toSet()
    }

    val fieldMap = mapPossibilities(possibilities).entries.map { (k, v) -> v to k }.toMap()

    return input.fieldSpecs.filter { it.name.startsWith("departure") }
        .map { fs ->
            val index = fieldMap[fs]!!
            input.ticket[index]
        }
        .reduce { a, b -> a * b }
}

fun mapPossibilities(possibilities: Map<Int, Set<FieldSpec>>): MutableMap<Int, FieldSpec> {
    val mappedEntry = possibilities.entries.first { (_, v) -> v.size == 1 }
    val mappedIndex = mappedEntry.key
    val mappedField = mappedEntry.value.first()
    val newPossibilities = possibilities
        .mapValues { (_, possibleFields) -> possibleFields.minus(mappedField) }
        .filter { (_, v) -> v.isNotEmpty() }

    return if (newPossibilities.isEmpty()) {
        mutableMapOf(mappedIndex to mappedField)
    } else {
        val result = mapPossibilities(newPossibilities)
        result[mappedIndex] = mappedField
        result
    }
}