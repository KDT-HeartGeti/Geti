package com.example.myapplication.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Preview
@Composable
fun previewSurfaceIn() {
    Surface(
        Modifier.fillMaxSize()
    ) {
        InputScreen()
    }
}

@Composable
fun InputScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // 상단 여백
        Spacer(modifier = Modifier.height(90.dp))
        // 입력 페이지에 나타날 이미지 공간
        Image(
            painter = painterResource(
                id = R.drawable.no_image
            ),
            contentDescription = "uploaded picture",
            modifier = Modifier
                .border(width = 3.dp, Color.Gray)
                .size(300.dp)
        )
        // 이미지와 버튼 여백
        Spacer(modifier = Modifier.height(25.dp))
        // 이미지 업로드용 버튼
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.width(300.dp)
        ) {
            GetiButton(onclick = { /*TODO*/ }, text = "사진찍기")
            GetiButton(onclick = { /*TODO*/ }, text = "불러오기")
        }
    }
}

// 버튼 함수
@Composable
fun GetiButton(onclick: () -> Unit, text: String) {
    Button(
        onClick = onclick,
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(Color.Yellow),
        modifier = Modifier
            .height(50.dp)
            .width(130.dp)
    ) {
        Text(text = text, color = Color.Black, textAlign = TextAlign.Center, fontSize = 20.sp)
    }
}