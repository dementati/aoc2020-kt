package com.dementati.aoc2020.day5

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SeatLocatorTest {
    @Test
    fun testLocatePosition() {
        assertEquals(44, locatePosition("FBFBBFF", 0, 127, 'F'))
        assertEquals(70, locatePosition("BFFFBBF", 0, 127, 'F'))
        assertEquals(14, locatePosition("FFFBBBF", 0, 127, 'F'))
        assertEquals(102, locatePosition("BBFFBBF", 0, 127, 'F'))
    }

    @Test
    fun testLocateSeat() {
        assertEquals(567, locateSeat("BFFFBBFRRR"))
        assertEquals(119, locateSeat("FFFBBBFRRR"))
        assertEquals(820, locateSeat("BBFFBBFRLL"))
    }
}