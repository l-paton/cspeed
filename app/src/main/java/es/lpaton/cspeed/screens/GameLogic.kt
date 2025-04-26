package es.lpaton.cspeed.screens

import kotlin.random.Random

class GameLogic {

    data class GameState(
        val score: Int = 0,
        val timeLeft: Int = 60,
        val currentOperation: Operation? = null,
        val isGameOver: Boolean = false
    )

    data class Operation(
        val num1: Int,
        val num2: Int,
        val operator: String,
        val result: Int
    )

    class GameManager {
        private var gameState = GameState()
        private val operators = listOf("+", "-", "×", "÷")

        fun generateOperation(): Operation {
            val operator = operators.random()
            var num1 = Random.nextInt(1, 100)
            var num2 = Random.nextInt(1, 100)

            val result = when (operator) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "×" -> num1 * num2
                "÷" -> {
                    num1 = num1 * num2
                    num1 / num2
                }
                else -> 0
            }

            return Operation(num1, num2, operator, result)
        }

        fun checkAnswer(userAnswer: Int): Boolean {
            val currentOperation = gameState.currentOperation ?: return false
            val isCorrect = userAnswer == currentOperation.result

            if (isCorrect) {
                gameState = gameState.copy(
                    score = gameState.score + 1,
                    currentOperation = generateOperation()
                )
            }

            return isCorrect
        }

        fun updateTime() {
            gameState = gameState.copy(
                timeLeft = gameState.timeLeft - 1,
                isGameOver = gameState.timeLeft <= 1
            )
        }

        fun getCurrentState(): GameState = gameState

        fun resetGame() {
            gameState = GameState(
                currentOperation = generateOperation()
            )
        }
    }

    class InverseGameManager {
        private var gameState = GameState()
        private val operators = listOf("+", "-", "×", "÷")

        fun generateOptions(): List<Operation> {
            val correctOperation = generateOperation()
            val options = mutableListOf(correctOperation)

            while (options.size < 4) {
                val option = generateOperation()
                if (option.result == correctOperation.result &&
                    !options.any { it.num1 == option.num1 && it.num2 == option.num2 && it.operator == option.operator }) {
                    options.add(option)
                }
            }

            return options.shuffled()
        }

        private fun generateOperation(): Operation {
            val operator = operators.random()
            val num1 = Random.nextInt(1, 10)
            val num2 = Random.nextInt(1, 10)

            val result = when (operator) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "×" -> num1 * num2
                "÷" -> {
                    val product = num1 * num2
                    product / num1
                }
                else -> 0
            }

            return Operation(num1, num2, operator, result)
        }

        fun checkAnswer(selectedOperation: Operation): Boolean {
            val isCorrect = selectedOperation.result == gameState.currentOperation?.result

            if (isCorrect) {
                gameState = gameState.copy(
                    score = gameState.score + 1,
                    currentOperation = generateOperation()
                )
            }

            return isCorrect
        }

        fun updateTime() {
            gameState = gameState.copy(
                timeLeft = gameState.timeLeft - 1,
                isGameOver = gameState.timeLeft <= 1
            )
        }

        fun getCurrentState(): GameState = gameState

        fun resetGame() {
            gameState = GameState(
                currentOperation = generateOperation()
            )
        }
    }
}