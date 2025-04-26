package es.lpaton.cspeed.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.navigation.NavController
import es.lpaton.cspeed.R

@Composable
fun InverseGameScreen(navController: NavController) {
    val gameManager = remember { GameLogic.InverseGameManager() }
    var gameState by remember { mutableStateOf(gameManager.getCurrentState()) }
    var options by remember { mutableStateOf<List<GameLogic.Operation>>(emptyList()) }
    var showFeedback by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        gameManager.resetGame()
        gameState = gameManager.getCurrentState()
        options = gameManager.generateOptions()

        while (!gameState.isGameOver) {
            delay(1000)
            gameManager.updateTime()
            gameState = gameManager.getCurrentState()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Puntuación: ${gameState.score}",
                fontSize = 20.sp
            )
            Text(
                text = "Tiempo: ${gameState.timeLeft}",
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        gameState.currentOperation?.let { operation ->
            Text(
                text = "¿Qué operación da como resultado ${operation.result}?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            options.forEach { option ->
                OperationButton(
                    operation = option,
                    onClick = {
                        isCorrect = gameManager.checkAnswer(option)
                        showFeedback = true

                        if (isCorrect) {
                            gameState = gameManager.getCurrentState()
                            options = gameManager.generateOptions()
                        }
                    }
                )
            }
        }

        if (showFeedback) {
            Text(
                text = if (isCorrect) stringResource(R.string.correct) else stringResource(R.string.incorrect),
                color = if (isCorrect) colorResource(R.color.primary) else colorResource(R.color.incorrect),
                fontSize = 24.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            LaunchedEffect(Unit) {
                delay(1000)
                showFeedback = false
            }
        }

        Button(
            onClick = { navController.navigateUp() },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Volver")
        }
    }
}

@Composable
fun OperationButton(operation: GameLogic.Operation, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Text(
            text = "${operation.num1} ${operation.operator} ${operation.num2}",
            fontSize = 20.sp
        )
    }
}