package com.dementati.aoc2020.day18

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day18Test {
    @Test
    fun example1() {
        assertEquals(3, eval("1 + 2"))
        assertEquals(71, eval("1 + 2 * 3 + 4 * 5 + 6"))
        assertEquals(51, eval("1 + (2 * 3) + (4 * (5 + 6))"))
        assertEquals(13632, eval("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"))
    }

    @Test
    fun example1_withBrackets() {
        assertEquals(51, eval("1 + (2 * 3) + (4 * (5 + 6))"))
    }

    @Test
    fun parseTokens() {
        assertEquals(Const(1), parse2("1"))
        assertEquals(Sum(Const(1), Const(1)), parse2("1 + 1"))
        assertEquals(Const(1), parse2("(1)"))
        assertEquals(
            Sum(
                Const(1),
                Sum(
                    Const(2),
                    Const(3)
                )
            ),
            parse2("1 + (2 + 3)")
        )
        assertEquals(
            Product(
                Sum(
                    Const(1),
                    Const(2)
                ),
                Const(3)
            ),
            parse2("1 + 2 * 3")
        )
    }

    @Test
    fun example2() {
        assertEquals(3, eval2("1 + 2"))
        assertEquals(231, eval2("1 + 2 * 3 + 4 * 5 + 6"))
        assertEquals(51, eval2("1 + (2 * 3) + (4 * (5 + 6))"))
        assertEquals(23340, eval2("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"))
    }
}