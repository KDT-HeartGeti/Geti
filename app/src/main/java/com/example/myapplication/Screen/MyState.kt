package com.example.myapplication.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.ui.theme.Gray400
import com.example.myapplication.ui.theme.Gray50
import com.example.myapplication.ui.theme.NeonBlue
import com.example.myapplication.ui.theme.NeonRed

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyStateActivity(navController: NavController) {
    var isToggled by remember { mutableStateOf(false) }

    val toggleImage by remember(isToggled) {
        mutableStateOf(if (isToggled) R.drawable.toggle_on_svgrepo_com else R.drawable.toggle_off_svgrepo_com)
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "김상은",
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.heartbeat_logo),
                            contentDescription = "프로필 사진"
                        )
                    }
                },
                actionIcon = {
                    IconButton(
                        onClick = { isToggled = !isToggled }
                    ) {
                        Icon(
                            ImageVector.vectorResource(id = toggleImage),
                            contentDescription = "토글 아이콘",
                            tint = if (isToggled) Gray400 else NeonBlue
                        )
                    }
                }
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                BottomBar(
                    navigationIcon = {
                        IconButton(
                            onClick = { navController.navigate("calender") },
                            modifier = Modifier
                                .size(width = 100.dp, height = 100.dp)
                                .padding(start = 14.dp, top = 8.dp, end = 14.dp, bottom = 8.dp)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.calender),
                                contentDescription = "내 기록 아이콘 (캘린더)",
                                modifier = Modifier
                                    .size(width = 30.dp, height = 30.dp)

                            )
                        }
                    },
                    actionIcon1 = {
                        IconButton(
                            onClick = { navController.navigate("input") },
                            modifier = Modifier
                                .size(width = 80.dp, height = 78.dp)
                                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.info),
                                contentDescription = "영양정보 아이콘",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    },
                    actionIcon2 = {
                        IconButton(
                            onClick = { /* doSomething() */ },
                            modifier = Modifier
                                .size(width = 80.dp, height = 78.dp)
                                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.consult),
                                contentDescription = "상담 아이콘",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    },
                    actionIcon3 = {
                        IconButton(
                            onClick = { navController.navigate("state")},
                            modifier = Modifier
                                .size(width = 80.dp, height = 78.dp)
                                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.state),
                                contentDescription = "내 상태 아이콘",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                )
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Gray50)
            ) {
                InputScreen(navController)
            }
        }
    )
}

@Composable
fun MyState(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(320.dp)
                    .height(80.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
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
                    .height(80.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "지난 주 나의 평균 열량",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = NeonRed,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.wrapContentWidth(align = Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "2,000 kcal",
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