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
fun eval2(s: String): Long = eval(parse2(s))

fun parseTokens(s: String, parse: (String) -> Expr): Pair<MutableList<Expr>, MutableList<Char>> {
    val operands = mutableListOf<Expr>()
    val operators = mutableListOf<Char>()
    var remaining = s

    while (remaining.isNotEmpty()) {
        """(\d+)(.*)""".toRegex().matchEntire(remaining)
            ?.destructured
            ?.also { (n, r) ->
                operands.add(Const(n.toLong()))
                remaining = r.trim()
            }

        if (remaining.startsWith("(")) {
            val i = findMatchingBracket(remaining) ?: throw IllegalArgumentException("Couldn't parse: $remaining")
            operands.add(parse(remaining.substring(1, i)))
            remaining = remaining.substring(i + 1).trim()
        }

        if (remaining.isNotEmpty()) {
            operators.add(remaining.first())
            remaining = remaining.substring(1).trim()
        }
    }

    return operands to operators
}

fun parse(s: String): Expr {
    val (operands, operators) = parseTokens(s, ::parse)

    while (operators.isNotEmpty()) {
        val newOp = when (operators.first()) {
            '+' -> Sum(operands.first(), operands[1])
            '*' -> Product(operands.first(), operands[1])
            else -> throw IllegalArgumentException("Invalid operator")
        }
        operands.removeFirst()
        operands.removeFirst()
        operands.add(0, newOp)
        operators.removeFirst()

    }

    return operands[0]
}

fun parse2(s: String): Expr {
    val (operands, operators) = parseTokens(s, ::parse2)

    while (operators.isNotEmpty()) {
        var i = operators.indexOf('+')
        val newOp = if (i != -1) {
            Sum(operands[i], operands[i + 1])
        } else {
            i = operators.indexOf('*')
            Product(operands[i], operands[i + 1])
        }

        operands.removeAt(i)
        operands.removeAt(i)
        operands.add(i, newOp)
        operators.removeAt(i)
    }

    return operands[0]
}


fun findMatchingBracket(s: String): Int? {
    var d = 0
    s.forEachIndexed { i, c ->
        when (c) {
            '(' -> d++
            ')' -> {
                d--
                if (d == 0) {
                    return i
                }
            }
        }
    }

    return null
}

fun solveStar1(lines: List<String>): Long {
    return lines.map { eval(it) }.sum()
}

fun solveStar2(lines: List<String>): Long {
    return lines.map { eval2(it) }.sum()
}