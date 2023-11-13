package com.example.myapplication

sealed class NavScreen(val route: String) {
    object Input : NavScreen("input")
    object Output : NavScreen("output")
}
