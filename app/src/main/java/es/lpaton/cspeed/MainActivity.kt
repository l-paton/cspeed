package es.lpaton.cspeed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import es.lpaton.cspeed.screens.NormalGameScreen;
import es.lpaton.cspeed.screens.InverseGameScreen;
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import es.lpaton.cspeed.snippets.TextSnippets;
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CSpeedApp()
        }
    }
}

@Preview()
@Composable
fun MyScreenPreview() {
    CSpeedApp()
}

@Composable
fun CSpeedApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController)
        }
        composable("normal") {
            NormalGameScreen(navController)
        }
        composable("inverse") {
            InverseGameScreen(navController)
        }
    }
}

@Composable
fun MainScreen(navController: NavController, modifier: Modifier = Modifier) {
    val radioOptions = listOf(
        stringResource(R.string.normal_mode),
        stringResource(R.string.inverse_mode)
    )
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.background)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp),
                fontFamily = TextSnippets.Fonts.bungeeFontFamily,
                color = colorResource(R.color.primary)
            )

            Image(
                painter = painterResource(id = R.drawable.speed_icon),
                contentDescription = "Descripción de la imagen",
                modifier = Modifier
                    .width(125.dp)
                    .height(125.dp)
            )

            Spacer(modifier = Modifier.height(38.dp))

            Column(
                modifier.selectableGroup(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                radioOptions.forEach { text ->
                    Row(
                        Modifier
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = { onOptionSelected(text) },
                                role = Role.RadioButton
                            )
                            .padding(vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = null,
                            colors = RadioButtonDefaults.colors(
                                selectedColor = colorResource(R.color.green),
                                unselectedColor = Color.White,
                            )
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp),
                            color = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(38.dp))

            val normalMode = stringResource(R.string.normal_mode)

            Button(
                onClick = {
                    if(selectedOption == normalMode){
                        navController.navigate("normal");
                    }else{
                        navController.navigate("inverse");
                    }
                },
                modifier = Modifier
                    .width(222.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.primary),
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(2.dp),
            ) {
                Text(stringResource(R.string.start))
            }
        }
    }

}