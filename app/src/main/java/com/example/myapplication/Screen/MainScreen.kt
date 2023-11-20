package com.example.myapplication.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.Gray600
import com.example.myapplication.ui.theme.NeonRed

@Composable
fun MainScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(320.dp)
                    .height(80.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    TitleText(text = "오늘 나의 열량")
                    Spacer(modifier = Modifier.height(8.dp))
                    ContentText(text = "900 kcal")
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(320.dp)
                    .height(80.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    TitleText(text = "오늘 나의 당질")
                    Spacer(modifier = Modifier.height(8.dp))
                    ContentText(text = "85 g")
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(320.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "이번주 요약",
                        color = Gray600,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.wrapContentWidth(align = Alignment.Start)
                    )
                    TitleText(text = "이런 식으로 한 주 또는 한 달이 분석되어서 사용자에게 정보가 제공될 예정입니다. 차트, 표, 그래프 등의 시각 자료도 적극 반영할 계획입니다.")
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(320.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    TitleText(text = "지난 주 나의 평균 열량")
                    Spacer(modifier = Modifier.height(8.dp))
                    ContentText(text = "2,000 kcal")
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                colors = CardDefaults.cardColors(NeonRed),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(320.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "지난 주 나의 총 열량",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.wrapContentWidth(align = Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "2,4000 kcal",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.wrapContentWidth(align = Alignment.Start)
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(320.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    TitleText(text = "지난 주 나의 평균 당질")
                    Spacer(modifier = Modifier.height(8.dp))
                    ContentText(text = "15 g")
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                colors = CardDefaults.cardColors(NeonRed),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(320.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "지난 주 나의 총 당질",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.wrapContentWidth(align = Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "1000 g",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.wrapContentWidth(align = Alignment.Start)
                    )
                }
            }
        }
    }
}