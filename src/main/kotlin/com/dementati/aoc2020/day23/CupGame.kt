package com.dementati.aoc2020.day23

data class Node(val value: Long, var next: Node?, val cache: Map<Long, Node>)

fun parseRing(input: String, star2: Boolean = false): Node {
    var current: Node? = null

    val cache = mutableMapOf<Long, Node>()

    if (star2) {
        (1000_000L downTo 10L).forEach {
            current = Node(it, current, cache)
            cache[current!!.value] = current!!
        }
    }

    input.toList().reversed().forEach {
        current = Node(it.toString().toLong(), current, cache)
        cache[current!!.value] = current!!
    }

    val last = find(current!!) { it.next == null }
    last!!.next = current

    return current!!
}

fun turn(ring: Node): Node {
    val list = removeNodes(ring, 3)
    val destination = selectDestination(ring, list)
    insertNodes(destination, list)
    return ring.next!!
}

fun removeNodes(ring: Node, n: Long): Node {
    val start = ring.next
    var end = ring
    (1..n).forEach {
        end = end.next!!
    }
    val temp = end.next
    end.next = null
    ring.next = temp
    return start!!
}

fun insertNodes(ring: Node, list: Node) {
    val temp = ring.next
    ring.next = list
    val last = find(list) { it.next == null }
    last!!.next = temp
}

fun selectDestination(ring: Node, list: Node): Node {
    var label = ring.value - 1

    if (label < 1) {
        label = 9
    }

    while (find(list) { it.value == label } != null) {
        label--

        if (label < 1) {
            label = 9
        }
    }

    return ring.cache[label]!!
}

fun find(list: Node, predicate: (Node) -> Boolean): Node? {
    var current: Node? = list
    while (current != null && !predicate(current)) {
        current = current.next
    }

    return current
}

fun solveStar1(input: String): String {
    var ring = parseRing(input)
    (1..100).forEach {
        ring = turn(ring)
    }
    val one = ring.cache[1L]!!
    val result = mutableListOf<Long>()
    var current = one.next!!
    while (current != one) {
        result.add(current.value)
        current = current.next!!
    }

    return result.joinToString("")
}

fun solveStar2(input: String): Long {
    var ring = parseRing(input, true)

    (1..10_000_000L).forEach {
        ring = turn(ring)
    }
    val one = ring.cache[1]!!
    val n1 = one.next!!.value
    val n2 = one.next!!.next!!.value
    return n1 * n2
}

fun asString(ring: Node): String {
    val first = ring
    var current: Node? = first
    val result = mutableListOf<Long>()
    do {
        result.add(current!!.value)
        current = current.next
    } while (current != null && current != first)

    return result.joinToString(" ")
}