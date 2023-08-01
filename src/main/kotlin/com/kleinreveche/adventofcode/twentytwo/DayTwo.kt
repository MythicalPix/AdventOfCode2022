package com.kleinreveche.adventofcode.twentytwo

import java.lang.IllegalArgumentException

//--- Day 2: Rock Paper Scissors --- https://adventofcode.com/2022/day/2
fun dayTwo() {
    val moves = readDayTwoData(dayTwoInput)
    val scores = mutableListOf<Int>()
    val secondScores = mutableListOf<Int>()

    moves.forEach { it ->
        it.forEach {
            scores.add(checkScores(it))
            secondScores.add(partTwoScores(it))
        }
    }
    println("Your Score is ${scores.sum()}")
    println("After the new instructions, your new score is ${secondScores.sum()}")
}

data class DayTwoMoves(val opponent: Char, val player: Char)

private fun checkScores(moves: DayTwoMoves): Int {
    val playerChoice = moves.player
    val opponentChoice = moves.opponent

    val playerWinScore = when (playerChoice) {
        'X' -> if (opponentChoice == 'C') 6 else if (opponentChoice == 'A') 3 else 0
        'Y' -> if (opponentChoice == 'A') 6 else if (opponentChoice == 'B') 3 else 0
        'Z' -> if (opponentChoice == 'B') 6 else if (opponentChoice == 'C') 3 else 0
        else -> throw IllegalArgumentException("Wrong Input, Check Hard-Coded Data")
    }
    val playScore = when (playerChoice) {
        'X' -> 1
        'Y' -> 2
        'Z' -> 3
        else -> throw IllegalArgumentException("Wrong Input, Check Hard-Coded Data")
    }

    return playerWinScore + playScore
}

private fun partTwoScores(moves: DayTwoMoves): Int {
    val strategy = moves.player
    val opponentChoice = moves.opponent

    val playerMove = when (strategy) {
        'X' -> if (opponentChoice == 'A') 'Z' else if (opponentChoice == 'B') 'X' else 'Y'
        'Y' -> if (opponentChoice == 'A') 'X' else if (opponentChoice == 'B') 'Y' else 'Z'
        'Z' -> if (opponentChoice == 'A') 'Y' else if (opponentChoice == 'B') 'Z' else 'X'
        else -> throw IllegalArgumentException("Wrong Input, Check Hard-Coded Data")
    }

    return checkScores(DayTwoMoves(moves.opponent, playerMove))
}

private fun readDayTwoData(input: String): MutableList<List<DayTwoMoves>> {
    val moves = mutableListOf<List<DayTwoMoves>>()
    var currentMovesList = mutableListOf<DayTwoMoves>()
    val lines = input.trim().lines()

    lines.forEach { line ->
        if (line.isBlank()) {
            moves.add(currentMovesList.toList())
            currentMovesList = mutableListOf()
        } else {
            val (opponent, own) = line.split(" ")
            val movesList = DayTwoMoves(opponent.trim().toCharArray()[0], own.trim().toCharArray()[0])
            currentMovesList.add(movesList)
        }

    }

    if (currentMovesList.isNotEmpty()) {
        moves.add(currentMovesList.toList())
    }

    return moves
}

val dayTwoInput = """
    B Z
    A Z
    B Z
    C Z
    C Z
    B X
    A X
    C X
    A Z
    C Y
    C X
    C Y
    C Y
    A X
    A Z
    A Z
    A X
    B Z
    B X
    A Z
    A X
    C Y
    A X
    B Z
    B Z
    A X
    C Z
    A Z
    A X
    B Z
    A Z
    A Y
    C Y
    A Z
    C Z
    A Z
    C Y
    C Z
    C Z
    A Z
    A X
    A X
    B X
    A Z
    B Z
    A X
    A Z
    A Z
    A X
    A X
    C Y
    A Z
    B X
    C Y
    A X
    B Y
    A Z
    A X
    A Z
    A X
    C Z
    A Z
    A Y
    A X
    C Y
    A X
    B X
    A X
    A Z
    C Y
    A Z
    A X
    C X
    C Z
    C Z
    A Z
    A X
    A Z
    C X
    C Z
    B Z
    A Z
    C Y
    C Z
    B X
    A X
    A Z
    A X
    A X
    C Y
    A Z
    C Z
    B X
    A X
    A X
    A Z
    A Y
    A X
    C X
    A Z
    B X
    C Y
    A X
    A X
    A X
    C X
    B Z
    B Z
    A Z
    A X
    A Z
    C Z
    C X
    C Y
    B X
    C Z
    A Z
    C X
    A Z
    A X
    A X
    A X
    A X
    A Z
    B X
    A X
    B X
    C Z
    A Z
    A Z
    A X
    A X
    A X
    B X
    A Z
    A X
    A Z
    A X
    B Z
    A Z
    A X
    B Z
    A Z
    A Z
    C Y
    B Z
    A Z
    B Z
    A Z
    A X
    C X
    A X
    C Y
    C X
    A X
    A X
    C Y
    A Z
    A X
    B Z
    A Z
    A Z
    B X
    B Z
    A X
    A Z
    A X
    A X
    A Z
    A X
    A Z
    A Z
    C X
    A Z
    A X
    C Y
    A Z
    A Z
    A X
    A X
    A X
    A X
    B Z
    B X
    A Z
    A X
    A Z
    C Y
    B Y
    C Y
    B X
    A Z
    A Z
    A Z
    C Y
    A Z
    A X
    B Z
    C X
    A X
    C Z
    C X
    C Y
    A Z
    A X
    A Z
    C Z
    A Z
    A Z
    A Y
    C Z
    A X
    A X
    B Z
    A Z
    C Z
    A Z
    A X
    A Z
    A Z
    A Z
    A Z
    A Z
    A Z
    A Z
    A Z
    C X
    A X
    A X
    A X
    B Y
    B X
    A X
    A Z
    A Z
    A X
    A Z
    A X
    A X
    A Z
    B X
    A Z
    C Z
    A Z
    C Z
    A Z
    A Z
    C Z
    A X
    C Z
    A X
    C X
    A Z
    A Z
    B Z
    A Y
    B Z
    A X
    B Z
    A Z
    A X
    A Z
    A Z
    A X
    A Z
    B Z
    C X
    A Z
    A X
    B Z
    C Y
    B Z
    C X
    A Z
    A Z
    C X
    B X
    C Z
    A Z
    A X
    A Z
    C X
    A Z
    C Z
    C Z
    B Z
    B Z
    A Z
    C X
    A X
    B Y
    A Y
    A Z
    C X
    B X
    A Z
    A Z
    B Y
    A Z
    A Z
    C Z
    A Z
    A X
    A Z
    B X
    C Z
    B X
    A Z
    B Z
    C Y
    A X
    A X
    A Z
    A Z
    A Z
    C Y
    A X
    A Z
    A Z
    A X
    B Z
    A Z
    C X
    C Z
    A Z
    A X
    B Z
    A X
    C Y
    A X
    A Z
    A Y
    C Z
    A Y
    A Z
    C X
    C Y
    A Z
    C Y
    A Z
    A X
    C Y
    A Z
    A X
    A X
    B Z
    A X
    C X
    A X
    C X
    A X
    A Z
    A X
    A Z
    A Z
    A X
    C X
    C Z
    A Z
    C Z
    C X
    A Z
    C Y
    A X
    A Z
    A Z
    C Y
    A X
    B X
    C Y
    B Z
    C Y
    A X
    A X
    C Z
    A X
    A Z
    A X
    A X
    C X
    A X
    A Z
    B X
    C Z
    A Y
    B Y
    A Z
    A Z
    A Z
    A Z
    B Y
    A Y
    A Z
    B Z
    A Z
    A X
    C Z
    B Y
    C Y
    A X
    A Z
    C Z
    B Z
    A Z
    A Z
    A Y
    C Z
    A Z
    A Z
    C Z
    C Z
    C Z
    A X
    B Y
    C X
    A X
    A Z
    A Z
    B Z
    A Y
    A X
    A Z
    B X
    A X
    A X
    A Z
    A Y
    A Z
    A X
    B X
    A Z
    C X
    A Z
    A X
    C X
    B X
    B Y
    B X
    A Z
    A Z
    A Z
    A X
    B X
    A X
    B Z
    A Y
    B Z
    C Z
    A X
    C Z
    C X
    A Z
    C Y
    C Y
    A Z
    A X
    A X
    A X
    B X
    A Z
    A Y
    C Y
    B X
    A X
    A X
    A X
    C Y
    A Z
    A Y
    A X
    C Z
    A Z
    A Z
    A Z
    C Z
    A X
    A Z
    C Z
    B X
    C Y
    A Z
    B Z
    B Z
    C Z
    C Z
    A X
    A Z
    A Z
    B X
    B X
    A Z
    A Z
    A Y
    C Z
    A Z
    A X
    C Z
    A Z
    C Z
    C Z
    A Z
    A Z
    B Z
    A X
    B Y
    A Z
    A X
    C Z
    A X
    B X
    A Z
    C Y
    A Z
    C X
    C Y
    A X
    C X
    C X
    A Z
    C Z
    C Y
    A Z
    A Z
    A X
    A X
    C Z
    A Y
    A Z
    A X
    B Z
    A X
    A Z
    A Z
    A X
    B Z
    A X
    A Z
    C X
    A Z
    A Z
    C X
    A Z
    A X
    A Z
    A X
    B Z
    A Z
    A Z
    C Y
    C Y
    C Z
    C Z
    C Y
    A X
    A X
    A X
    A Z
    A Z
    C Y
    A Z
    C Z
    A Z
    C Z
    B Z
    B X
    A Y
    C Y
    A X
    A Z
    A X
    A X
    A Z
    C Z
    C Y
    C Y
    A Y
    A X
    A Z
    A X
    C Z
    A X
    A Z
    A Z
    C Y
    A X
    A Z
    A Z
    A X
    B Y
    A Z
    A Z
    A Z
    A Z
    C Y
    A Z
    A X
    A X
    A Z
    A Z
    C Y
    A Z
    A Z
    B Z
    A Z
    A X
    A X
    A Y
    C Z
    C Z
    A Z
    B Z
    A Z
    A Z
    A X
    A X
    C Z
    A Z
    A X
    A X
    A Z
    A Z
    B Y
    A Y
    C Y
    A X
    A Z
    C Z
    C Y
    A Z
    C Y
    A X
    A X
    A X
    A Z
    C Y
    C Y
    A Z
    B X
    C Y
    A Z
    A X
    A Z
    A Y
    C Y
    C Z
    B Z
    A Z
    C Y
    A X
    C Z
    A X
    C Y
    C Z
    A Z
    C Y
    C Y
    A Y
    B X
    A Z
    C Z
    B Z
    B Y
    A Z
    C Y
    A X
    A Z
    A Z
    C Y
    A Z
    C Z
    A Z
    A Z
    A X
    A Z
    A Z
    C Z
    A X
    A Z
    C Z
    C Y
    A X
    A X
    C Z
    C Y
    A Z
    C Y
    A Z
    A X
    A X
    A Z
    A Z
    A X
    A Z
    C Y
    C Z
    A Z
    B X
    C Z
    A Z
    A Z
    A X
    B X
    A Z
    A Y
    A X
    C Z
    B X
    A Z
    C Y
    C Z
    C Z
    C Z
    A Z
    A Z
    A X
    A Z
    C Z
    C Y
    A X
    A Z
    A Z
    A Y
    A X
    A Z
    A Z
    C X
    B Z
    A X
    A Z
    A X
    C Z
    A X
    C X
    A X
    C Y
    C Y
    A X
    A X
    A Z
    B Z
    A Z
    A Y
    B Z
    A X
    C X
    A X
    C X
    A X
    C X
    A Z
    A X
    A X
    A X
    C Y
    A X
    A X
    C X
    C Z
    A X
    B Y
    A X
    B Z
    A Z
    A X
    C Z
    A Z
    A X
    A Z
    A X
    C X
    C Z
    A X
    C Z
    C Y
    C Y
    A X
    A X
    A X
    C Z
    C Z
    A Z
    C Z
    A Z
    B Z
    A Y
    B Z
    A X
    A Z
    A Z
    A Z
    C Z
    A Z
    A X
    A X
    B Z
    A Z
    C Y
    C Z
    B Z
    C Y
    C Z
    A X
    A X
    C X
    B X
    C Z
    A Z
    A Z
    C Z
    A X
    A Z
    A Z
    B X
    B Z
    A Z
    A Z
    B Z
    A Z
    A Z
    B Z
    B Z
    C Z
    A Z
    C Z
    A Z
    A Z
    C Z
    A Z
    A Z
    B Z
    A Z
    A X
    A X
    A X
    A Z
    C Z
    A X
    A Z
    A X
    A Z
    B Z
    B Z
    C Z
    A Z
    A Y
    C X
    A X
    A Z
    A X
    A X
    C X
    C Z
    B Y
    A X
    A X
    C Y
    C Y
    A X
    A Z
    A Z
    B Z
    B Z
    A Z
    C Z
    A Z
    C Z
    C X
    A Z
    A X
    A Y
    A X
    A Z
    C Z
    A X
    A Z
    A Z
    A Z
    A X
    C X
    B Z
    A Z
    A Y
    C X
    A Z
    A Z
    A Z
    A X
    A Y
    A X
    C Y
    A Z
    C Y
    A Z
    A Z
    C X
    A Z
    A Y
    C Z
    A X
    A X
    A Z
    A Y
    A Z
    A X
    C Z
    A Z
    A Z
    B Z
    A Z
    A Z
    C X
    B Z
    C Y
    A X
    A Y
    B X
    A Z
    C Z
    C X
    C Z
    A X
    A X
    A Z
    A Z
    A X
    A Z
    B Z
    B Z
    A Z
    B Y
    B Z
    A X
    A X
    C Z
    A Z
    C Y
    B Z
    C Z
    C Z
    A Z
    A X
    B Z
    A X
    A X
    A Z
    A Z
    A X
    B Z
    A Z
    C Z
    A Z
    A Z
    B X
    C Z
    B Z
    C Y
    A Z
    A X
    A X
    B X
    A X
    B Z
    C Y
    C Y
    B Z
    C Y
    C Y
    A X
    A Z
    A Z
    C Y
    B Z
    A X
    A Z
    A X
    B X
    A X
    C Y
    A X
    A X
    B Z
    A X
    B X
    A Z
    C Y
    B Z
    A X
    A Z
    A X
    A X
    B X
    A X
    A X
    A X
    A Z
    A Z
    C Y
    A Z
    B Z
    C Z
    C Y
    C Z
    A X
    A Z
    A X
    C Z
    C Z
    C Z
    C Y
    A X
    B X
    B X
    C X
    C Z
    C X
    C Z
    B Z
    A Y
    A X
    C X
    B X
    A X
    A X
    A Z
    A X
    A Z
    A X
    A X
    A Z
    A Z
    C Z
    A X
    C Y
    C Y
    C Z
    A Z
    A X
    A Z
    A X
    A Z
    A Y
    A Z
    A X
    A X
    A Z
    A X
    A Y
    A Y
    C X
    A Y
    C Y
    C X
    A Z
    C Z
    C Y
    C Z
    A Z
    C X
    A X
    C Z
    A Z
    C Y
    B Z
    A Z
    A Z
    A Z
    C Y
    A X
    A X
    A Z
    A Z
    A Z
    B Z
    C Z
    A X
    C Y
    A X
    A Z
    A X
    C X
    C Z
    A Z
    C Y
    A Y
    A Y
    A Z
    A X
    A Z
    A Y
    A X
    C Z
    A Z
    A Z
    A X
    A X
    B Z
    B Y
    B Y
    C X
    A Z
    A Z
    C Z
    A X
    A Z
    C Y
    B X
    C Y
    A Z
    A Z
    A X
    A Z
    A X
    A X
    C Z
    A Z
    A Z
    A X
    A X
    C Y
    A X
    A X
    C Y
    A Y
    C X
    A X
    C Y
    A Y
    A Z
    B Y
    A X
    C Z
    B X
    A X
    C Z
    A Z
    B Z
    A Z
    A Z
    C X
    A Z
    B Z
    C X
    A Z
    C Y
    A Z
    C Z
    A Z
    C X
    A X
    A Z
    A Y
    B X
    A X
    B X
    A Z
    A Z
    C Y
    A Z
    C Z
    A Z
    A Z
    A Z
    C X
    A Z
    B X
    A X
    A Z
    C Z
    A Z
    A X
    C Y
    A Z
    C Y
    A Z
    A Z
    C Z
    A X
    A Z
    A Z
    B X
    A Z
    A X
    C Z
    B Y
    A Z
    A X
    C Z
    A X
    A Z
    A Z
    A Z
    C Z
    A Z
    A Z
    C X
    A X
    A X
    C X
    A X
    A X
    B Z
    A Z
    A X
    B X
    A X
    A Z
    C Y
    A Z
    B X
    C Y
    A X
    C Z
    A Z
    B Z
    A X
    A Z
    A Z
    C Z
    A X
    C Z
    A X
    C X
    A Y
    A X
    A Z
    C X
    A X
    A X
    A X
    A X
    A Z
    A X
    B Z
    C Z
    C Y
    B Z
    B Z
    B X
    A Z
    A X
    B Z
    A Z
    A X
    C Z
    A Z
    C Y
    B Z
    C Y
    A X
    C X
    A X
    A Z
    C Y
    A Z
    A X
    A Y
    C Y
    C Y
    A Z
    A Z
    C Y
    A Z
    A Z
    A X
    C Y
    B Y
    A X
    A X
    A X
    C Y
    B X
    A Z
    A Y
    A Z
    A Z
    A X
    C Z
    A Z
    A Z
    C X
    A Z
    B Z
    B X
    A X
    C Z
    A X
    C Y
    A Z
    C Z
    C Y
    C X
    C Y
    A Z
    A X
    A Z
    A Z
    A Z
    B Z
    A Z
    C Z
    A Z
    C Z
    B Z
    C Z
    C Z
    A Z
    A Z
    B X
    A Z
    A X
    A Z
    A X
    A Y
    A Z
    B Z
    C X
    A Z
    A Z
    B X
    A X
    C X
    C Z
    A X
    A Z
    A X
    C Y
    C Z
    A Z
    C Y
    A X
    A Z
    A Z
    B Z
    C Y
    A Z
    A Z
    C Z
    A X
    C X
    A Z
    A Z
    A Y
    C Z
    C Z
    A Z
    A X
    B Z
    A X
    A X
    A X
    A X
    A Z
    A Z
    A X
    A X
    C Z
    C Y
    C Y
    C Y
    A Z
    C X
    B Z
    C Y
    C Z
    A X
    A X
    A Z
    C Z
    C Z
    A Y
    C Y
    B X
    C Y
    A X
    A X
    A Y
    A X
    A Z
    A Z
    A X
    A Z
    A Z
    A Z
    A X
    A Z
    B Z
    A X
    A X
    A X
    C Z
    C X
    A Z
    C Y
    C Z
    A X
    A Z
    A Z
    A X
    A Z
    A X
    B Z
    A Z
    A Z
    A Z
    C Y
    C Z
    B Z
    A Z
    B Z
    A X
    A X
    A X
    C Y
    A Z
    A Z
    A Z
    C X
    A X
    A X
    A Z
    A Z
    A Z
    A Z
    B Z
    A X
    A Z
    A X
    C Z
    A Z
    A Z
    A Z
    B Z
    A Y
    A Z
    C Z
    B Z
    C Y
    A Z
    A X
    C Y
    A X
    A X
    A Z
    A Z
    A X
    B Z
    B X
    C Z
    C Z
    A Y
    A Z
    A X
    A Z
    A Z
    A Z
    A Z
    A Z
    B Z
    A Z
    B X
    A Z
    A Z
    C Z
    B Z
    A Z
    C X
    B Z
    C X
    B Z
    A Z
    A Z
    A Z
    A Z
    A X
    B Z
    A X
    B Z
    C Y
    A Z
    A Z
    C X
    A X
    A Z
    A Y
    A Z
    C Y
    C X
    C Z
    A X
    A Z
    C Z
    A Z
    A X
    A Z
    B Z
    A X
    A Z
    B Z
    C Y
    A Z
    C Y
    A Z
    C Y
    B X
    C Y
    A Z
    B Z
    A X
    B Z
    B Z
    C X
    A Z
    C Z
    A Z
    C Z
    A Z
    C Z
    B Z
    C Y
    C Z
    A Z
    C Y
    A Z
    C X
    B Y
    B Y
    C X
    C Y
    A Z
    C Z
    A Z
    C X
    A Z
    A Z
    C Y
    A Z
    A Z
    C X
    A Y
    A X
    A X
    B Z
    A Z
    C Z
    A Z
    B Z
    C Y
    C Z
    A Z
    C Y
    B X
    C Z
    A X
    C X
    C Y
    C Y
    C Y
    A Z
    A Z
    B Y
    C Y
    A Z
    A X
    A Z
    A X
    A Z
    C X
    C Z
    A X
    C Z
    B X
    A X
    C Z
    A Z
    C X
    A Y
    A Z
    A X
    A Z
    C Z
    B Z
    B Z
    A Z
    C Y
    A Z
    C Z
    C Y
    C Z
    A Z
    A Z
    B X
    C X
    C Z
    A X
    A Z
    A Y
    C Y
    A X
    A Z
    C Y
    A Z
    B X
    A Z
    A Z
    A Z
    A Z
    C X
    A X
    A Z
    A Y
    A X
    B Y
    A X
    A X
    A X
    A X
    B Z
    A X
    C Y
    C X
    A X
    A Z
    A X
    A Z
    A Z
    A Z
    A Z
    A X
    A Z
    C X
    C Z
    C Z
    A X
    B Y
    A X
    C Y
    A X
    C Z
    A X
    A Z
    A Z
    A Z
    A Z
    C X
    A X
    C Y
    A Z
    A Z
    C Y
    A Z
    A X
    A Z
    A X
    A Z
    B Y
    C Y
    B Y
    C Y
    A Y
    A Z
    C X
    B X
    A X
    A Y
    C Z
    A X
    B Z
    A X
    A X
    A Y
    A X
    A Z
    A Z
    C Z
    C X
    A Z
    C Y
    A Z
    A Z
    C X
    A Y
    A Z
    A X
    A Z
    A Z
    C X
    A Z
    A Z
    A Z
    C Y
    A X
    A X
    A X
    C Z
    A Z
    C Y
    A X
    A X
    C Y
    C X
    C Y
    A Y
    C X
    A Z
    A Z
    A Y
    C X
    A Z
    A Z
    A Z
    C Y
    C X
    A Z
    B Z
    A Z
    A X
    C Y
    A X
    C Y
    C Y
    A X
    C Y
    A X
    C Y
    B Z
    A Z
    A X
    A Z
    A X
    A X
    A Z
    A X
    A X
    C Z
    A Z
    B Y
    C X
    B X
    B X
    A Z
    A Y
    B Y
    A Z
    A X
    A X
    C Y
    C X
    B Z
    A Z
    C Y
    C X
    C Y
    A Y
    A Z
    C X
    A X
    A X
    A Z
    A Z
    B Z
    C X
    A Z
    B Z
    A X
    B Y
    C X
    A X
    A Y
    C X
    A X
    C Y
    A Z
    A Z
    A X
    A X
    C Z
    C Z
    B Z
    A X
    B Z
    B Y
    A Z
    A Z
    C Y
    A Z
    B Y
    A Z
    A X
    C Z
    C Z
    A X
    A X
    A X
    A Z
    A Z
    C Y
    A X
    A X
    A Z
    A X
    C X
    A Z
    A X
    B Z
    B X
    A X
    A X
    A Z
    B Z
    A X
    A Z
    C Y
    C Y
    B Z
    C Z
    A X
    B X
    B X
    A Z
    A X
    A Z
    C Y
    A X
    A X
    B Y
    C Y
    A X
    A X
    C Y
    A Z
    A Z
    C Z
    A Z
    B Z
    A Y
    A Z
    C Y
    B Y
    A X
    C X
    A X
    C Z
    A Z
    A X
    A Z
    A Z
    A X
    B Z
    C Z
    A Z
    A X
    C X
    A X
    C Y
    B Z
    B X
    C Z
    C X
    A X
    A X
    A Z
    A X
    C Y
    B Z
    A Z
    C Z
    A Z
    A Z
    A X
    B X
    A X
    A Z
    A X
    A X
    A Z
    A Z
    A Y
    A Z
    C Z
    B Z
    A X
    A X
    A X
    A Z
    B Z
    A Z
    A Z
    C Z
    C Y
    C Y
    A Z
    A X
    A Z
    C Z
    A X
    A X
    A Z
    A Z
    C X
    B Y
    A X
    B Z
    B Z
    A Z
    A Z
    C Z
    C Z
    A X
    A X
    A Z
    C Z
    A Z
    C Z
    B X
    A X
    A Z
    A Z
    C Z
    A X
    A Z
    B Y
    B X
    A X
    C Y
    C X
    C Y
    B X
    A X
    C Y
    A Z
    B Y
    A Y
    A X
    C Y
    A Z
    A Z
    A Z
    A Z
    A Z
    C Z
    A Z
    A Z
    A Z
    C X
    B X
    C Z
    A X
    B X
    A X
    C Y
    A X
    C X
    A X
    A Z
    A Z
    B X
    A X
    A Z
    A X
    C Z
    A Z
    A Z
    A X
    C Z
    A Z
    C X
    A Z
    A Z
    A Z
    A Z
    B Z
    A X
    A X
    C Y
    A Z
    A Z
    B Z
    A X
    A Z
    A Y
    A Z
    C X
    B Z
    A Z
    A X
    B Y
    A Z
    A X
    C X
    C X
    A X
    A Z
    A X
    A Z
    B Z
    B Y
    A Y
    A Z
    C Y
    B Z
    A Z
    C X
    C Y
    C Z
    C X
    A X
    A Z
    A X
    A X
    A Z
    A X
    A X
    A X
    A Z
    C Z
    C Y
    A Z
    A X
    C X
    B Z
    C Z
    C Y
    C Y
    A Z
    A X
    A Z
    A Z
    A Z
    A X
    A Z
    A Z
    A X
    C Z
    A X
    A X
    A Z
    C X
    B Y
    A X
    C Z
    A X
    C X
    A Z
    A X
    A Y
    C Z
    A X
    A Z
    C X
    B X
    A Z
    A Z
    A X
    A X
    A Z
    C Y
    C Z
    A X
    A Z
    C Z
    C X
    A X
    A Z
    A X
    A X
    B X
    C Z
    B Z
    A Z
    A X
    A X
    B Y
    A Z
    A X
    A X
    A Z
    A Z
    A Z
    A X
    C Y
    A Z
    C Y
    A Z
    A Z
    A X
    C Z
    A Z
    B X
    A X
    C Z
    A X
    A Y
    B X
    C X
    A X
    A X
    A Z
    A X
    A X
    A X
    C Y
    A Y
    A Z
    B Z
    B Z
    B Z
    A Z
    A Z
    A Z
    A X
    B X
    C Y
    A X
    A Z
    C Z
    B Y
    A X
    A Z
    A Z
    A X
    A X
    B Z
    A X
    C Y
    A Z
    A X
    A X
    A Z
    A Z
    A Z
    A Z
    A Z
    A X
    A Z
    A X
    A X
    C Z
    A X
    C Z
    A Z
    C Y
    A X
    A Z
    C Z
    A Z
    A Z
    A X
    A X
    A Z
    A X
    A Z
    B Z
    A X
    A X
    C X
    A Z
    A X
    A Z
    C Y
    C Y
    C Y
    C Y
    C Z
    A Z
    B X
    C Z
    A X
    A Z
    C Y
    A X
    A Z
    B X
    A Z
    C X
    C Z
    C X
    C X
    A Z
    A Z
    B X
    A Z
    A Z
    A Z
    B Z
    A Z
    C Z
    A X
    A X
    A Z
    A X
    A Z
    C Z
    C Z
    A Z
    C Z
    C X
    A Z
    A X
    B Z
    A X
    B Y
    C X
    A X
    A Y
    A Z
    A Z
    A X
    A X
    A Z
    A X
    A Z
    A X
    A Z
    A X
    A Y
    A X
    C Z
    A Z
    A Z
    A X
    A X
    A Z
    A X
    C Y
    C Z
    A Z
    A Y
    A Z
    A X
    A Z
    C X
    B X
    A Z
    C Z
    B Y
    A Z
    A Z
    C Y
    A X
    A Z
    A Z
    C Z
    C Y
    A Z
    B Y
    A X
    C Y
    A X
    A X
    A Z
    C Z
    A Z
    A Z
    A X
    C Z
    A X
    C Y
    C Y
    A X
    B X
    C X
    A Y
    A X
    A Z
    B X
    A X
    B Z
    A Z
    A X
    C X
    A X
    B X
    A X
    A Z
    A X
    C Y
    A Z
    C Z
    C Y
    A X
    B X
    C Y
    C X
    A X
    A Z
    A X
    A Z
    A X
    A X
    A Z
    A Z
    A X
    A Z
    A Y
    A X
    A X
    A Y
    A Z
    A Z
    C Z
    A X
    A Z
    A X
    A X
    B X
    A X
    B Y
    C Y
    A Z
    B Z
    A Z
    B Y
    A Z
    C Y
    A Z
    A X
    A X
    A Z
    C X
    C Z
    B X
    A X
    A Z
    A X
    A Z
    A Z
    B X
    A Z
    A X
    C Z
    B Y
    C Y
    C X
    A X
    A Z
    A X
    C Y
    A Z
    C Z
    A X
    A X
    A X
    A Z
    A Z
    A Z
    B X
    C X
    A Y
    B Z
    A X
    A Z
    A Z
    C X
    C Y
    A X
    A Z
    C Y
    A X
    A Z
    A X
    A Z
    A X
    A X
    B Z
    A X
    C X
    A X
    A X
    A X
    A Z
    A Z
    A X
    A Z
    C Y
    C Z
    B Z
    B X
    A Z
    A X
    C X
    C Y
    A X
    B Z
    B Z
    A Z
    B Z
    C Y
    A X
    A X
    A X
    C Y
    A Z
    A X
    A Z
    A Z
    A Z
    A X
    A Z
    A X
    A Z
    C Y
    A X
    C X
    C Y
    C Y
    C X
    A X
    C Y
    A Z
    C Y
    B Z
    B Z
    C Z
    A X
    B X
    C X
    A Z
    A Y
    A Z
    B Z
    A Z
    B Z
    A X
    A X
    A Z
    A Z
    A Y
    A Z
    A X
    A Y
    A Z
    A Z
    A Z
    A Z
    B X
    A Z
    A Z
    A Z
    A Z
    C Y
    B Y
    C X
    A Z
    B Z
    B X
    A X
    C Z
    A Z
    C Z
    A Z
    A X
    A X
    A X
    A X
    A X
    A Z
    C Y
    A X
    C X
    A Y
    C X
    C X
    A X
    A Z
    A X
    C Z
    A X
    C Y
    B Z
    C Y
    C Z
    B X
    A Z
    C Y
    A Z
    C Y
    A X
    A X
    A X
    A Z
    A Z
    A Z
    A Z
    C Z
    C Y
    A X
    C Y
    B Y
    A X
    A X
    A Z
    C Y
    C Z
    A X
    A X
    A Z
    A Z
    A X
    A X
    A Z
""".trimIndent()