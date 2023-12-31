package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.Screen.CalendarScreen
import com.example.myapplication.Screen.InputActivity
import com.example.myapplication.Screen.InputScreen
import com.example.myapplication.Screen.OutputScreen
import com.example.myapplication.Screen.RecsScreen
import com.example.myapplication.Screen.LoadingScreen
import com.example.myapplication.Screen.MyState
import com.example.myapplication.Screen.MyStateActivity
import com.example.myapplication.Screen.OutputActivity
import com.example.myapplication.Screen.StartLoadingScreen
//import com.example.myapplication.Screen.StartLoadingScreen
import com.example.myapplication.data.NavScreen
import com.example.myapplication.ui.theme.Gray200
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView: WebView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true

        class WebViewClientClass : WebViewClient() {
            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url.startsWith("intent://")) {
                    try {
                        val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                        val existPackage =
                            packageManager.getLaunchIntentForPackage(intent.getPackage()!!)
                        if (existPackage != null) {
                            startActivity(intent)
                        } else {
                            val marketIntent = Intent(Intent.ACTION_VIEW)
                            marketIntent.data =
                                Uri.parse("market://details?id=" + intent.getPackage())
                            startActivity(marketIntent)
                        }
                        return true
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                    view.loadUrl(url)
                }
                return true
            }
        }

        webView.webViewClient = WebViewClientClass()

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Gray200
                ) {
                    // navigation 변수 선언
                    val navController = rememberNavController()
                    // 앱 처음 실행시 나올 화면
                    val startDestination = remember {
                        NavScreen.Start.route
                    }

                    // navigation
                    NavHost(navController = navController, startDestination = startDestination) {
                        composable(NavScreen.Start.route) { StartLoadingScreen(navController)}
                        composable(NavScreen.MyState.route) { MyStateActivity(navController) }
                        composable(NavScreen.Recs.route) { RecsScreen(navController) }
                        composable(NavScreen.Calender.route) { CalendarScreen(navController) }
                        composable(NavScreen.Input.route) { InputActivity(navController) }
                        composable(NavScreen.Loading.route) {
                            LoadingScreen(
                                navController,
                                it.arguments?.getString("encodedUri") ?: ""
                            )
                        }
                        composable(NavScreen.Output.route) {
                            val predictValue = it.arguments?.getString("predictValue") ?: ""
                            val selectedUri = it.arguments?.getString("encodedUri") ?: ""
                            OutputActivity(navController, predictValue, selectedUri)
                        }
                    }
                }
            }
        }
    }
}
