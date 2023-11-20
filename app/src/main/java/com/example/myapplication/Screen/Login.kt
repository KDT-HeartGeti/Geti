package com.example.myapplication.Screen

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable
fun Login(navController: NavController, anything: () -> Unit) {
    Button(onClick = anything) {

    }
}