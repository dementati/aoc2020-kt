package com.dementati.aoc2020.day17

typealias Pos = Triple<Long, Long, Long>
typealias State = Set<Pos>
typealias MutableState = HashSet<Pos>

fun Pos.add(pos: Pos): Pos {
    return Pos(this.first + pos.first, this.second + pos.second, this.third + pos.third)
}

fun Pos.scope(): Set<Pos> {
    return (-1L..1L).flatMap { x ->
        (-1L..1L).flatMap { y ->
            (-1L..1L).map { z ->
                this.add(Pos(x, y, z))
            }
        }
    }.toSet()
}

class CellAutomata constructor(lines: List<String>) {
    var state: State

    init {
        val initialState = MutableState()
        lines.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                if (c == '#') {
                    initialState.add(Pos(x.toLong(), y.toLong(), 0L))
                }
            }
        }

        state = initialState
    }

    fun evolve() {
        val newState = MutableState()

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

    val updateCandidates: Set<Pos>
        get() {
            return state.flatMap { it.scope() }.toSet()
        }

    val active: Int
        get() = state.size

    fun shouldActivate(pos: Pos): Boolean {
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

    fun activeNeighbours(pos: Pos): Int {
        var count = 0
        (-1L..1L).forEach { x ->
            (-1L..1L).forEach { y ->
                (-1L..1L).forEach { z ->
                    val cmp = pos.add(Pos(x, y, z))
                    if (pos != cmp && state.contains(cmp)) {
                        count++
                    }
                }
            }
        }

        return count
    }
}