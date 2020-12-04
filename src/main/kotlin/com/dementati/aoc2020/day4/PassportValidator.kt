package com.dementati.aoc2020.day4

import java.lang.IllegalArgumentException

fun countValidPassports(input: String): Int {
    val required = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    return input.trim().split("""(\r\n\r\n)|(\n\n)""".toRegex())
        .filter { line ->
            val fields = HashMap<String, String>()
            line.split("""\s+""".toRegex())
                .forEach {
                    val parts = it.split(":")
                    if (parts.toList().size != 2) {
                        throw IllegalArgumentException("Invalid format on field $it from passport spec $line")
                    }
                    val (k, v) = parts
                    fields[k] = v
                }

            fields.keys.containsAll(required)
        }
        .toList()
        .size
}

fun countValidPassports2(input: String): Int {
    val required = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    return input.trim().split("""(\r\n\r\n)|(\n\n)""".toRegex())
        .filter { line ->
            val fields = HashMap<String, String>()
            line.split("""\s+""".toRegex())
                .forEach {
                    val parts = it.split(":")
                    if (parts.toList().size != 2) {
                        throw IllegalArgumentException("Invalid format on field $it from passport spec $line")
                    }
                    val (k, v) = parts
                    fields[k] = v
                }

            fields.keys.containsAll(required)
                && validateYear(fields["byr"]!!, 1920, 2002)
                && validateYear(fields["iyr"]!!, 2010, 2020)
                && validateYear(fields["eyr"]!!, 2020, 2030)
                && validateHeight(fields["hgt"]!!)
                && validateHairColor(fields["hcl"]!!)
                && validateEyeColor(fields["ecl"]!!)
                && validatePID(fields["pid"]!!)
        }
        .toList()
        .size
}

fun validateYear(input: String, min: Int, max: Int): Boolean {
    return """\d\d\d\d""".toRegex()
        .matches(input) && input.toInt() in (min until max + 1)
}

fun validateHeight(input: String): Boolean {
    val cm = """(\d\d\d)cm""".toRegex()
    val inch = """(\d\d)in""".toRegex()

    cm.matchEntire(input)
        ?.destructured
        ?.let { (value) ->
            if (value.toInt() in (150 until 194)) {
                return true
            }
        }

    inch.matchEntire(input)
        ?.destructured
        ?.let { (value) ->
            return value.toInt() in (59 until 77)
        }

    return false
}

fun validateHairColor(input: String): Boolean {
    return """#[0-9a-f]{6}""".toRegex().matches(input)
}

fun validateEyeColor(input: String): Boolean {
    return """(amb)|(blu)|(brn)|(gry)|(grn)|(hzl)|(oth)""".toRegex().matches(input)
}

fun validatePID(input: String): Boolean {
    return """\d{9}""".toRegex().matches(input)
}
