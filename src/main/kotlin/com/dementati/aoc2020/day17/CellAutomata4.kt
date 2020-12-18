package com.dementati.aoc2020.day17

data class Pos4(val x: Long, val y: Long, val z: Long, val w: Long)

typealias State4 = Set<Pos4>
typealias MutableState4 = HashSet<Pos4>

fun Pos4.add(pos: Pos4): Pos4 {
    return Pos4(this.x + pos.x, this.y + pos.y, this.z + pos.z, this.w + pos.w)
}

fun Pos4.scope(): Set<Pos4> {
    return (-1L..1L).flatMap { x ->
        (-1L..1L).flatMap { y ->
            (-1L..1L).flatMap { z ->
                (-1L..1L).map { w ->
                    this.add(Pos4(x, y, z, w))
                }
            }
        }
    }.toSet()
}

class CellAutomata4 constructor(lines: List<String>) {
    var state: State4

    init {
        val initialState = MutableState4()
        lines.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                if (c == '#') {
                    initialState.add(Pos4(x.toLong(), y.toLong(), 0L, 0L))
                }
            }
        }

        state = initialState
    }

    fun evolve() {
        val newState = MutableState4()

        this.updateCandidates.forEach {
            if (shouldActivate(it)) {
                newState.add(it)
            }
        }

        state = newState
    }

    fun evolve(times: Int) {
        (1..times).forEach {
            evolve()
        }
    }

    val updateCandidates: Set<Pos4>
        get() {
            return state.flatMap { it.scope() }.toSet()
        }

    val active: Int
        get() = state.size

    fun shouldActivate(pos: Pos4): Boolean {
        return if (state.contains(pos)) {
            when (activeNeighbours(pos)) {
                in 2..3 -> true
                else -> false
            }
        } else {
            when (activeNeighbours(pos)) {
                3 -> true
                else -> false
            }
        }
    }

    fun activeNeighbours(pos: Pos4): Int {
        var count = 0
        (-1L..1L).forEach { x ->
            (-1L..1L).forEach { y ->
                (-1L..1L).forEach { z ->
                    (-1L..1L).forEach { w ->
                        val cmp = pos.add(Pos4(x, y, z, w))
                        if (pos != cmp && state.contains(cmp)) {
                            count++
                        }
                    }
                }
            }
        }

        return count
    }
}