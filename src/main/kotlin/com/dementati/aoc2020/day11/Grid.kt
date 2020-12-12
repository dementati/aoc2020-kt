package com.dementati.aoc2020.day11

import java.lang.IllegalArgumentException

typealias Pos = Pair<Int, Int>
typealias GridMap = Map<Pos, Type>
typealias MutableGridMap = MutableMap<Pos, Type>
typealias GridHashMap = HashMap<Pos, Type>

enum class Type {
    FLOOR,
    VACANT,
    OCCUPIED
}

class Grid constructor(lines: List<String>) {
    var state: GridMap
    val dimensions: Pos

    init {
        val initialState = GridHashMap()
        lines.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                if (c == 'L')  {
                    initialState[x to y] = Type.VACANT
                } else {
                    initialState[x to y] = Type.FLOOR
                }
            }
        }
        state = initialState
        dimensions = lines[0].length to lines.size
    }

    fun evolve(): GridMap {
        val result = GridHashMap()
        state.keys.forEach { pos ->
            state[pos]?.also {
                result[pos] = when (it) {
                    Type.VACANT, Type.OCCUPIED -> when (seatedNeighbours(pos)) {
                        0 -> Type.OCCUPIED
                        in 1..3 -> it
                        else -> Type.VACANT
                    }
                    Type.FLOOR -> Type.FLOOR
                }
            }

        }
        return result
    }

    fun seatedNeighbours(pos: Pos): Int {
        var count = 0
        (-1..1).forEach { yoff ->
            (-1..1).forEach { xoff ->
                val x = pos.first + xoff
                val y = pos.second + yoff
                if (xoff != 0 || yoff != 0) {
                    count += when (state[x to y]) {
                        Type.FLOOR -> 0
                        Type.VACANT -> 0
                        Type.OCCUPIED -> 1
                        null -> 0
                    }
                }
            }
        }
        return count
    }

    fun evolve2(): GridMap {
        val result = GridHashMap()
        state.keys.forEach { pos ->
            state[pos]?.also {
                result[pos] = when (it) {
                    Type.VACANT, Type.OCCUPIED -> when (directionalNeighbours(pos)) {
                        0 -> Type.OCCUPIED
                        in 1..4 -> it
                        else -> Type.VACANT
                    }
                    Type.FLOOR -> Type.FLOOR
                }
            }

        }
        return result
    }

    fun directionalNeighbours(pos: Pos): Int {
        var count = 0
        (-1..1).forEach { yoff ->
            (-1..1).forEach { xoff ->
                if (xoff != 0 || yoff != 0) {
                    var k = 1
                    while (true) {
                        val x = pos.first + k*xoff
                        val y = pos.second + k*yoff

                        when (state[x to y]) {
                            Type.FLOOR -> Unit
                            Type.VACANT -> break
                            Type.OCCUPIED -> {
                                count++
                                break
                            }
                            null -> break
                        }

                        k++
                    }
                }
            }
        }
        return count
    }

    fun solveDay1(): Int {
        while (true) {
            val previousState = state.toMutableMap()
            val currentState = evolve()

            if (previousState == currentState) {
                return currentState.values.count { it == Type.OCCUPIED }
            } else {
                state = currentState
            }
        }
    }

    fun solveDay2(): Int {
        while (true) {
            val previousState = state.toMutableMap()
            val currentState = evolve2()

            if (previousState == currentState) {
                return currentState.values.count { it == Type.OCCUPIED }
            } else {
                state = currentState
            }
        }
    }

    fun printGrid() {
        (0 until dimensions.second).forEach { y ->
            (0 until dimensions.first).forEach { x ->
                print(when (state[x to y]) {
                    Type.FLOOR -> '.'
                    Type.VACANT -> 'L'
                    Type.OCCUPIED -> '#'
                    null -> throw IllegalArgumentException("Trying to print out of bounds index: ($x, $y)")
                })
            }
            println()
        }

        println()
    }
}