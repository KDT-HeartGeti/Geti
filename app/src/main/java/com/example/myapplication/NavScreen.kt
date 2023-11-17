package com.example.myapplication

sealed class NavScreen(val route: String) {
    object Input : NavScreen("input")
    object Loading : NavScreen("loading/{encodedUri}")
//    data class Output (
//        val predictValue:String, val selectedUri: String) : NavScreen("output/${predictValue}/${selectedUri}")
//    object Output : NavScreen("output/{predictValue}/{selectedUri}")
object Output : NavScreen("output/{predictValue}")
}
