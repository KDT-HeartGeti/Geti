package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.Screen.InputScreen
import com.example.myapplication.Screen.MainScreen
import com.example.myapplication.Screen.OutputScreen
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // navigation 변수 선언
                    val navController = rememberNavController()
                    // 앱 처음 실행시 나올 화면
                    val startDestination = remember {
                        NavScreen.Input.route
                    }

                    // navigation
                    NavHost(navController = navController, startDestination = startDestination){
                        composable(NavScreen.Input.route) { InputScreen(navController) }
                        composable(NavScreen.Output.route) { OutputScreen(navController) }
                    }
                }
            }
        }
    }
}
