package com.example.myapplication.data

sealed class NavScreen(val route: String) {
    object Start : NavScreen("start")

    object Recs : NavScreen("recs")
    object Input : NavScreen("input")
    object Loading : NavScreen("loading/{encodedUri}")
    object Output : NavScreen("output/{predictValue}/{encodedUri}")
}
