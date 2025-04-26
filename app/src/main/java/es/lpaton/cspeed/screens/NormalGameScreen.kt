package es.lpaton.cspeed.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import es.lpaton.cspeed.R

@Preview()
@Composable
fun MyScreenPreview() {
    val navController = rememberNavController()
    NormalGameScreen(navController)
}

@Composable
fun NormalGameScreen(navController: NavController) {
    val gameManager = remember { GameLogic.GameManager() }
    var gameState by remember { mutableStateOf(gameManager.getCurrentState()) }
    var userInput by remember { mutableStateOf("") }
    var showFeedback by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(userInput) }

    LaunchedEffect(Unit) {
        gameManager.resetGame()
        gameState = gameManager.getCurrentState()

        while (!gameState.isGameOver) {
            delay(1000)
            gameManager.updateTime()
            gameState = gameManager.getCurrentState()
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(R.color.primary))
        .padding(12.dp)){

        Column(modifier = Modifier
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Top){
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Score: ${gameState.score}",
                    fontSize = 20.sp
                )
                Text(
                    text = "Time: ${gameState.timeLeft}",
                    fontSize = 20.sp
                )
            }

            Row(modifier = Modifier.fillMaxWidth()
                .padding(34.dp),
                horizontalArrangement = Arrangement.Center){
                gameState.currentOperation?.let { operation ->
                    Text(
                        text = "${operation.num1} ${operation.operator} ${operation.num2} = ?",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Box(modifier = Modifier
            .align(Alignment.Center)
            .padding(16.dp)){
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {

                Row(modifier = Modifier.fillMaxWidth()
                    .padding(6.dp)){
                    TextField(
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp)
                            .padding(end = 8.dp),
                        shape = RoundedCornerShape(2.dp),
                        value = userInput,
                        onValueChange = { newValue ->
                            userInput = newValue
                        },
                    )

                    Button(
                        onClick = {
                            if (userInput.isNotEmpty()) {
                                isCorrect = gameManager.checkAnswer(userInput.toInt())
                                showFeedback = true
                                userInput = ""

                                if (isCorrect) {
                                    gameState = gameManager.getCurrentState()
                                }
                            }
                        },
                        modifier = Modifier
                            .height(45.dp),
                        shape = RoundedCornerShape(2.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.primary_dark),
                            contentColor = Color.Black
                        ),
                    ) {
                        Text("ENTER", color = Color.White)
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))

                for (i in 0..2) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        for (j in 1..3) {
                            val number = i * 3 + j
                            NumberButton(number = number,
                            ) {
                                userInput += number.toString()
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    NumberButton(number = 0) {
                        userInput += "0"
                    }

                    Button(
                        onClick = {
                            if (userInput.isNotEmpty() && !userInput.contains("-")) {
                                userInput = "-$userInput"
                            }else{
                                userInput = userInput.replace("-", "")
                            }
                        },
                        modifier = Modifier
                            .width(90.dp),
                        shape = RoundedCornerShape(2.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.primary_dark),
                            contentColor = Color.Black
                        ),
                    ) {
                        Text("+/-", color = Color.White)
                    }

                    Button(
                        onClick = {
                            if (userInput.isNotEmpty()) {
                                userInput = userInput.dropLast(1)
                            }
                        },
                        modifier = Modifier
                            .width(90.dp),
                        shape = RoundedCornerShape(2.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.primary_dark),
                            contentColor = Color.Black
                        ),
                    ) {
                        Text("⌫", color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth()
                    .padding(12.dp),
                    horizontalArrangement = Arrangement.Center){
                    if (showFeedback) {
                        Text(
                            text = if (isCorrect) stringResource(R.string.correct) else stringResource(R.string.incorrect),
                            color = if (isCorrect) colorResource(R.color.correct) else colorResource(R.color.incorrect),
                            fontSize = 24.sp
                        )
                        LaunchedEffect(Unit) {
                            delay(1000)
                            showFeedback = false
                        }
                    }
                }
            }
        }

        Button(
            onClick = { navController.navigateUp() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(222.dp)
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.accent),
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(2.dp),
        ) {
            Text(stringResource(R.string.back))
        }
    }
}

@Composable
fun NumberButton(number: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(90.dp),
        shape = RoundedCornerShape(2.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.primary_dark),
            contentColor = Color.Black
        ),
    ) {
        Text(
            text = number.toString(),
            fontSize = 24.sp,
            color = Color.White
        )
    }
}