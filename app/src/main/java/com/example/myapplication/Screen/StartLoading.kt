package com.example.myapplication.Screen

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.example.myapplication.R
import kotlinx.coroutines.delay

// 앱 초기 진입 화면
@Composable
fun StartLoadingScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.healthy_lifestyle_bro),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentScale = ContentScale.FillWidth
        )
    }

    LaunchedEffect(key1 = true) {
        delay(2000L)
        // After a delay, navigate to another screen
        navController.navigate("state")
    }
}



