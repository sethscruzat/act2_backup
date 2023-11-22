package com.example.loginpage.navigation

sealed class navRoutes(var screenroute:String){

    object Login : navRoutes("login")
    object Display: navRoutes("display")
    object Content: navRoutes("content")
}
