package com.example.loginpage.screens.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ContentScreen(modifier: Modifier = Modifier, navController: NavHostController, viewModel: contentViewModel, id: Int){
    viewModel.getUserData(id)
    val name by viewModel.name.collectAsState()
    val email by viewModel.email.collectAsState()
    val favFood by viewModel.favFood.collectAsState()

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    )
    {
        Button(
            modifier = modifier
                .padding(12.dp)
                .align(Alignment.End),
            onClick = {
                navController.navigate("display") {
                    navController.graph.startDestinationRoute?.let { screenroute ->
                        popUpTo(screenroute) {
                            saveState = false
                        }
                    }
                    launchSingleTop = true
                    restoreState = false
                }
            }
        )
        {
            Text("Back")
        }

        Spacer(modifier.weight(0.1f))
        Column(modifier = modifier.weight(2f)){
            Text(text = "Name: $name", modifier = modifier
                .padding(
                    vertical = 6.dp,
                    horizontal = 14.dp
                ),
                fontSize = 17.sp)

            Text(text = "Email: $email", modifier = modifier
                .padding(
                    vertical = 6.dp,
                    horizontal = 14.dp
                ),
                fontSize = 17.sp)

            Text(text = "Favorite Food: $favFood", modifier = modifier
                .padding(
                    vertical = 6.dp,
                    horizontal = 14.dp
                ),
                fontSize = 17.sp)
        }

    }
}