package com.example.loginpage.screens.display

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
fun DisplayScreen(modifier: Modifier = Modifier, navController: NavHostController, viewModel: displayViewModel){
    viewModel.getUserList()
    val objList by viewModel.objList.collectAsState()

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Button(
            modifier = modifier
                .padding(12.dp)
                .align(Alignment.End),
            onClick = {
                navController.navigate("login") {
                    navController.graph.startDestinationRoute?.let { screenroute ->
                        popUpTo(screenroute) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        {
            Text("Logout")
        }
        Spacer(modifier.weight(0.1f))
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .weight(2f),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            contentPadding = PaddingValues(start = 18.dp, end = 18.dp)
        ){
            items(objList) { name ->
                GenerateUserList(navController = navController, name = name.fullName, id = name.id)
                Divider(modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        start = 0.dp,
                        top = 12.dp,
                        end = 0.dp,
                        bottom = 2.dp
                    ),
                    thickness = 1.dp)
            }
        }
    }
}

@Composable
fun GenerateUserList(modifier: Modifier = Modifier, navController: NavHostController, name : String, id: String) {
    Row(modifier = modifier
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(modifier = modifier
            .weight(1f)
            .padding(horizontal = 9.dp),text = name, fontSize = 18.sp)
        IconButton(onClick = {
            navController.navigate("content/{id}".replace(oldValue = "{id}", newValue = id)) {
                navController.graph.startDestinationRoute?.let { screenroute ->
                    popUpTo(screenroute) {
                        saveState = false
                    }
                }
                launchSingleTop = true
                restoreState = false
            }
        }) {
            Icon(Icons.Outlined.ArrowForwardIos, contentDescription = "Open", modifier = modifier.size(16.dp))
        }
    }

}