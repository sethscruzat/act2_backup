package com.example.loginpage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelStore
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginpage.screens.content.ContentScreen
import com.example.loginpage.screens.display.DisplayScreen
import com.example.loginpage.screens.login.LoginScreen
import com.example.loginpage.screens.login.loginViewModel
import com.example.loginpage.navigation.navRoutes
import com.example.loginpage.screens.content.contentViewModel
import com.example.loginpage.screens.display.displayViewModel
import com.example.loginpage.ui.theme.LoginPageTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginPageTheme {
                val loginViewModel = loginViewModel()
                val displayViewModel = displayViewModel()
                val contentViewModel = contentViewModel()

                val modifier: Modifier = Modifier
                val iViewModelStore = ViewModelStore()
                val navController = rememberNavController().apply {setViewModelStore(iViewModelStore)  }
                NavHost(navController = navController, startDestination = navRoutes.Login.screenroute) {
                    composable(navRoutes.Login.screenroute) {
                        LoginScreen(modifier, navController, loginViewModel)
                    }
                    composable(navRoutes.Display.screenroute) {
                        DisplayScreen(modifier, navController, displayViewModel)
                    }
                    composable("${navRoutes.Content.screenroute}/{id}") { navBackStackEntry ->
                        val id = navBackStackEntry.arguments?.getString("id")
                        if (id != null) {
                            ContentScreen(modifier, navController, contentViewModel, id = id.toInt())
                        }
                    }
                }
            }
        }
    }
}