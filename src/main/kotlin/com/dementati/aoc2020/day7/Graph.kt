package com.dementati.aoc2020.day7

import java.lang.IllegalArgumentException

class Graph constructor(lines: List<String>) {
    val vertices = HashSet<String>()
    private val edges = HashMap<Pair<String, String>, Int>()
    private val pathTo = HashMap<String, Set<String>>()
    private val bagCount = HashMap<String, Int>()

    init {
        val bag = """(\d+) (\w+ \w+) bags?""".toRegex()
        val pattern = """(\w+ \w+) bags contain (.+)\.""".toRegex()
        lines.forEach { line ->
            val groups = pattern.matchEntire(line)
                ?.groups
                ?: throw IllegalArgumentException("Error parsing: $line")

            val from = groups[1]?.value ?: throw IllegalArgumentException("Error parsing: $line")
            vertices.add(from)

            val bags = groups[2]?.value ?: throw IllegalArgumentException("Error parsing: $line")
            if (bags != "no other bags") {
                bags.split(", ")
                    .forEach {
                        bag.matchEntire(it)
                            ?.destructured
                            ?.let { (count, to) ->
                                vertices.add(to)
                                edges[from to to] = count.toInt()
                            }
                    }
            }
        }

        vertices.forEach {
            computePathsTo(it)
        }
    }

    private fun computePathsTo(vertex: String): Set<String> {
        if (!pathTo.containsKey(vertex)) {
            val children = getEdges(vertex)
            if (children.isEmpty()) {
                pathTo[vertex] = setOf()
            } else {
                pathTo[vertex] = children
                    .map { computePathsTo(it) }
                    .reduce { a, b -> a.union(b) }
                    .union(children)
            }
        }

        return pathTo[vertex]!!
    }

    fun computeBagCount(vertex: String): Int {
        if (!bagCount.containsKey(vertex)) {
            val children = getEdges(vertex)
            if (children.isEmpty()) {
                bagCount[vertex] = 0
            } else {
                bagCount[vertex] = children
                    .map { edges[vertex to it]!! * (1 + computeBagCount(it)) }
                    .sum()
            }
        }

        return bagCount[vertex]!!
    }

    private fun getEdges(vertex: String): Set<String> {
        return edges.keys.filter { (a, _) -> a == vertex }.map { (_, b) -> b }.toSet()
    }

    fun allWithPathsTo(vertex: String): Set<String> {
        return pathTo.entries
            .filter { (_, v) -> v.contains(vertex) }
            .map { (k, _) -> k }
            .toSet()
    }
}