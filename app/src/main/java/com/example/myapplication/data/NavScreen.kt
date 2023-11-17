package com.example.myapplication.data

sealed class NavScreen(val route: String) {
    object Input : NavScreen("input")
    object Loading : NavScreen("loading/{encodedUri}")
    object Output : NavScreen("output/{predictValue}/{encodedUri}")
}
