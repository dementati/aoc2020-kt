package com.dementati.aoc2020.day23

data class Node(val value: Int, var next: Node?)

fun parseRing(input: String): Node {
    var current: Node? = null
    input.toList().reversed().forEach {
        current = Node(it.toString().toInt(), current)
    }

    val last = getLast(current)
    last.next = current

    return current!!
}

fun getLast(node: Node): Node {
    var current = node
    while (current.next != null) {
        current = current.next
    }
    return current
}