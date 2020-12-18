package com.dementati.aoc2020.day18

import java.lang.IllegalArgumentException

sealed class Expr
data class Const(val number: Long): Expr()
data class Sum(val e1: Expr, val e2: Expr): Expr()
data class Product(val e1: Expr, val e2: Expr): Expr()

fun eval(expr: Expr): Long = when(expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    is Product -> eval(expr.e1) * eval(expr.e2)
}

fun eval(s: String): Long = eval(parse(s))

fun parse(s: String): Expr {
    """(\d+)""".toRegex().matchEntire(s)
        ?.destructured
        ?.also { (n) ->
            return Const(n.toLong())
        }

    val (a, op, b) = if (s.last() == ')') {
        val i = findMatchingBracket(s) ?: throw IllegalArgumentException("Couldn't parse: $s")

        if (i == 1) {
            return parse(s.substring(1, s.lastIndex))
        }

        try {
            Triple(
                parse(s.substring(0, i - 4)),
                s.substring(i - 3, i - 2),
                parse(s.substring(i, s.lastIndex)),
            )
        } catch(e: StringIndexOutOfBoundsException) {
            throw IllegalArgumentException("Couldn't parse: $s")
        }
    } else {
        """(.*) ([+*]) (\d+)""".toRegex().matchEntire(s)
            ?.destructured
            ?.let { (rest, op, n) -> Triple(parse(rest), op, Const(n.toLong())) }
            ?: throw IllegalArgumentException("Couldn't parse: $s")
    }

    return when(op) {
        "+" -> Sum(a, b)
        "*" -> Product(a, b)
        else -> throw IllegalArgumentException("Couldn't parse: $s")
    }
}

fun findMatchingBracket(s: String): Int? {
    assert(s.last() == ')')
    var d = 0
    s.reversed().forEachIndexed { i, c ->
        when (c) {
            ')' -> d++
            '(' -> {
                d--
                if (d == 0) {
                    return s.length - i
                }
            }
        }
    }

    return null
}

fun solveDay1(lines: List<String>): Long {
    return lines.map { eval(it) }.sum()
}