package com.example.myapplication.data

sealed class NavScreen(val route: String) {
    object Start : NavScreen("start")
    object MyState : NavScreen("state")

    object Recs : NavScreen("recs")
    object Calender : NavScreen("calender")
    object Input : NavScreen("input")
    object Loading : NavScreen("loading/{encodedUri}")
    object Output : NavScreen("output/{predictValue}/{encodedUri}")
    object SuccessLogin : NavScreen("successLogin")
    object Login : NavScreen("login")
}
