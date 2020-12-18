package com.dementati.aoc2020.day18

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day18Test {
    @Test
    fun parse() {
        assertEquals(
            Sum(Const(1), Const(2)),
            parse("1 + 2")
        )

        assertEquals(
            Product(
                Sum(
                    Const(1),
                    Const(2)
                ),
                Const(3)
            ),
            parse("1 + 2 * 3")
        )

        assertEquals(
            Sum(
                Const(1),
                Sum(
                    Const(2),
                    Const(3)
                )
            ),
            parse("1 + (2 + 3)")
        )
    }

    @Test
    fun parse_withTwoBrackets() {
        assertEquals(
            Sum(
                Sum(
                    Const(1),
                    Sum(
                        Const(2),
                        Const(3)
                    )
                ),
                Sum(
                    Const(4),
                    Const(5)
                )
            ),
            parse("1 + (2 + 3) + (4 + 5)")
        )
    }

    @Test
    fun parse_startWithBracket() {
        assertEquals(
            Sum(
                Const(1),
                Const(2)
            ),
            parse("(1 + 2)")
        )
    }

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
}