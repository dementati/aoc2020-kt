package com.dementati.aoc2020.day22

data class State(val deck1: List<Long>, val deck2: List<Long>)

fun solveStar1(groups: List<List<String>>): Long {
    val (deck1, deck2) = parseDecks(groups)

    while (deck1.isNotEmpty() && deck2.isNotEmpty()) {
        val card1 = deck1.removeAt(0)
        val card2 = deck2.removeAt(0)

        if (card1 > card2) {
            deck1.add(card1)
            deck1.add(card2)
        } else {
            deck2.add(card2)
            deck2.add(card1)
        }
    }

    return if (deck1.isNotEmpty()) value(deck1) else value(deck2)
}

fun solveStar2(groups: List<List<String>>): Long {
    val (deck1, deck2) = parseDecks(groups)
    val (_, winningDeck) = play(deck1, deck2)
    return value(winningDeck)
}

fun play(deck1: MutableList<Long>, deck2: MutableList<Long>): Pair<Boolean, List<Long>> {
    val states: MutableSet<State> = mutableSetOf()

    while (deck1.isNotEmpty() && deck2.isNotEmpty()) {
        val state = State(deck1.toMutableList(), deck2.toMutableList())

        if (states.contains(state)) {
            return true to deck1
        }

        states.add(state)

        val card1 = deck1.removeAt(0)
        val card2 = deck2.removeAt(0)

        val playerOneWins = if (deck1.size >= card1 && deck2.size >= card2) {
            play(
                deck1.subList(0, card1.toInt()).toMutableList(),
                deck2.subList(0, card2.toInt()).toMutableList()
            ).first
        } else {
            card1 > card2
        }

        if (playerOneWins) {
            deck1.add(card1)
            deck1.add(card2)
        } else {
            deck2.add(card2)
            deck2.add(card1)
        }
    }

    if (deck1.isNotEmpty()) {
        return true to deck1
    } else {
        return false to deck2
    }
}

fun parseDecks(groups: List<List<String>>): Pair<MutableList<Long>, MutableList<Long>> {
    assert(groups.size == 2)

    val deck1 = groups[0].drop(1).map { it.toLong() }.toMutableList()
    val deck2 = groups[1].drop(1).map { it.toLong() }.toMutableList()

    return deck1 to deck2
}

fun value(deck: List<Long>): Long {
    return deck.mapIndexed { i, v -> (deck.size - i) * v }.sum()
}