package com.dementati.aoc2020.day8

import java.lang.IllegalArgumentException

enum class Op {
    NOP,
    JMP,
    ACC
}

data class Instruction(var op: Op, val arg: Int) {
    fun clone() : Instruction {
        return Instruction(op, arg)
    }
}

fun parseInstructions(lines: List<String>): List<Instruction> {
    val pattern = """(\w+) ([+-]\d+)""".toRegex()
    return lines.map {
        pattern.matchEntire(it)
            ?.destructured
            ?.let { (opStr, arg) ->
                val op = when (opStr) {
                    "nop" -> Op.NOP
                    "jmp" -> Op.JMP
                    "acc" -> Op.ACC
                    else -> throw IllegalArgumentException("Unrecognized op: $opStr")
                }

                Instruction(op, arg.toInt())
            } ?: throw IllegalArgumentException("Couldn't parse line: $it")
    }
}

class Processor constructor(val instructions: List<Instruction>) {
    var acc = 0
    var i = 0

    fun execute(current: Instruction) {
        when (current.op) {
            Op.NOP -> {
                i += 1
            }
            Op.JMP -> i += current.arg
            Op.ACC -> {
                acc += current.arg
                i += 1
            }
        }
    }

    fun terminates(): Boolean {
        val visited = HashSet<Int>()
        while (true) {
            val current = instructions[i]

            if (visited.contains(i)) {
                return false
            } else {
                visited.add(i)
            }

            execute(current)

            if (i == instructions.size) {
                return true
            }
        }
    }
}

fun findTerminatingProgram(lines: List<String>): Int {
    val original = parseInstructions(lines)
    var i = 0
    while (true) {
        val current = original[i]
        val new = original.map { it.clone() }.toList()
        if (current.op == Op.NOP) {
            new[i].op = Op.JMP
        } else if (current.op == Op.JMP) {
            new[i].op = Op.NOP
        } else {
            i += 1
            continue
        }

        val processor = Processor(new)
        if (processor.terminates()) {
            return processor.acc
        }

        i += 1
    }
}