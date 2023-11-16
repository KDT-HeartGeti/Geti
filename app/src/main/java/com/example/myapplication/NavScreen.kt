package com.example.myapplication

sealed class NavScreen(val route: String) {
    object Input : NavScreen("input")
    data class Output (
        val predictValue:String, val selectUri: String) : NavScreen("output/${predictValue}/${selectUri}")

    object Loading : NavScreen("loading/{selectUri}")

}
