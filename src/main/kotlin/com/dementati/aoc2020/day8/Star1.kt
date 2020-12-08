package com.dementati.aoc2020.day8

import com.dementati.aoc2020.filereaders.inputAsLines

fun main() {
    val instructions = parseInstructions(inputAsLines(8))
    val processor = Processor(instructions)
    if (!processor.terminates()) {
        println(processor.acc)
    }
}