package com.example.myapplication.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R

//@Preview
//@Composable
//fun previewSurfaceOut() {
//    Surface(
//        Modifier.fillMaxSize()
//    ) {
//        OutputScreen()
//    }
//}

@Composable
fun OutputScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        // 상단 여백
        Spacer(modifier = Modifier.height(40.dp))
        // 해당 음식 이미지
        Image(
            painter = painterResource(id = R.drawable.o_12576),
            contentDescription = "Food Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(300.dp)
        )
        // 이미지와 이름 여백
        Spacer(modifier = Modifier.height(15.dp))
        // 음식 이름 Text
        FoodInfo(redText = "피자", blackText = "입니다")
        // 음식 성분 정도 Text
        NutrientInfo(explainText = "중접시(1조각) : ", quantityText = 218, unitText = " kcal")
        NutrientInfo(explainText = "포함 당질 : ", quantityText = 26, unitText = " g (100g 당)")
        // 위험도 Text 표시
        FoodInfo(redText = "고위험", blackText = " 식품군입니다.")
        DangerBox(dangerNum = 5)
    }
}

@Composable
fun BlackText(text: String) {
    Text(text = text, color = Color.Black, fontSize = 15.sp)
}

@Composable
fun GrayText(text: String) {
    Text(text = text, color = Color.LightGray, fontSize = 15.sp)
}

@Composable
fun RedText(text: String) {
    Text(text = text, color = Color.Red, fontSize = 30.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun FoodInfo(redText: String, blackText: String) {
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.wrapContentHeight()
    ) {
        RedText(text = redText)
        Spacer(modifier = Modifier.width(6.dp))
        BlackText(text = blackText)
    }
    Spacer(modifier = Modifier.height(15.dp))
}

@Composable
fun NutrientInfo(explainText: String, quantityText: Int, unitText: String) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .wrapContentHeight()
    ) {
        GrayText(text = explainText)
        RedText(text = quantityText.toString())
        GrayText(text = unitText)
    }
    Spacer(modifier = Modifier.height(15.dp))
}

@Composable
fun DangerBox(dangerNum: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 5 downTo 1) {
            Text(
                text = i.toString(),
                color = Color.Black,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .size(45.dp)
                    .background(
                        color = if (dangerNum == i) {
                            when (dangerNum) {
                                1 -> Color.Green
                                2 -> Color.Blue
                                3 -> Color.Yellow
                                4 -> Color.Magenta
                                5 -> Color.Red
                                else -> Color.Cyan
                            }
                        } else {
                            Color.White
                        },
                        shape = CircleShape
                    )
                    .padding(top = 5.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
    }
}