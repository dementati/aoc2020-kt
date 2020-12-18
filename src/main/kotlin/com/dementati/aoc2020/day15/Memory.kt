package com.dementati.aoc2020.day15

data class Item(var lastTurn: Long, var prevTurn: Long, var count: Long)

fun play(numbers: List<Long>, max: Long): Long {
    val memory = HashMap<Long, Item>()
    var last = 0L
    (0L until max).forEach { turn ->
        if (turn < numbers.size) {
            last = numbers[turn.toInt()]
            memory[last] = Item(turn, 0L, 1L)
        } else {
            last = if (memory[last]!!.count == 1L) {
                0L
            } else {
                (turn - 1) - memory[last]!!.prevTurn
            }

            memory.putIfAbsent(last, Item(0L, 0L, 0L))
            val item = memory[last]!!
            item.prevTurn = item.lastTurn
            item.lastTurn = turn
            item.count++
        }
    }

    return last
}