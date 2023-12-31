package com.example.myapplication.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import com.example.myapplication.ui.theme.Gray200
import com.example.myapplication.ui.theme.Gray400
import com.example.myapplication.ui.theme.Gray50
import com.example.myapplication.ui.theme.Gray500
import com.example.myapplication.ui.theme.Gray600
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
        Modifier.background(Gray50),
        topBar = {
            Box(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .height(64.dp)
//                    .clip(
//                        RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
//                    )
            ) {
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
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .background(NeonRed)
                    .fillMaxWidth()
                    .height(70.dp)
//                    .padding(start = 20.dp, end = 20.dp)
            ) {
                BottomBar(
                    navigationIcon = {
                        val currentRoute = navController.currentDestination?.route

                        IconButton(
                            onClick = { navController.navigate("calender") },
                            modifier = Modifier
                                .size(70.dp)
//                                .padding(start = 14.dp, top = 8.dp, end = 14.dp, bottom = 8.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.calender),
                                    contentDescription = "내 기록 아이콘 (캘린더)",
                                    modifier = Modifier
                                        .size(30.dp),
                                    tint = if (currentRoute == "calender") NeonBlue else Gray200
                                )
                                Text(
                                    text = "내기록",
                                    color = if (currentRoute == "calender") NeonBlue else Gray500,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    },
                    actionIcon1 = {
                        val currentRoute = navController.currentDestination?.route

                        IconButton(
                            onClick = { navController.navigate("input") },
                            modifier = Modifier
                                .size(70.dp)
//                                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.info),
                                    contentDescription = "영양정보 아이콘",
                                    modifier = Modifier.size(30.dp),
                                    tint = if (currentRoute == "input") NeonBlue else Gray200
                                )
                                Text(
                                    text = "영양정보",
                                    color = if (currentRoute == "input") NeonBlue else Gray500,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    },
                    actionIcon2 = {
                        val currentRoute = navController.currentDestination?.route

                        IconButton(
                            onClick = { /* doSomething() */ },
                            modifier = Modifier
                                .size(70.dp)
//                                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.consult),
                                    contentDescription = "상담 아이콘",
                                    modifier = Modifier.size(30.dp),
//                                    tint = if (currentRoute == "") NeonRed else Gray200
                                    tint = Gray200
                                )
                                Text(
                                    text = "상담",
//                                    color = if (currentRoute == "calender") NeonRed else Gray500,
                                    color = Gray500,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    },
                    actionIcon3 = {
                        val currentRoute = navController.currentDestination?.route

                        IconButton(
                            onClick = { navController.navigate("state") },
                            modifier = Modifier
                                .size(70.dp)
//                                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.state),
                                    contentDescription = "내 상태 아이콘",
                                    modifier = Modifier.size(30.dp),
                                    tint = if (currentRoute == "state") NeonBlue else Gray200
                                )
                                Text(
                                    text = "내상태",
                                    color = if (currentRoute == "state") NeonBlue else Gray500,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
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
                MyState(navController)
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
                    TitleText(text = "오늘 나의 열량")
                    Spacer(modifier = Modifier.height(8.dp))
                    ContentText(text = "1300 kcal")
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
                    TitleText(text = "오늘 나의 당질")
                    Spacer(modifier = Modifier.height(8.dp))
                    ContentText(text = "90 g")
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
                        text = "한 주 요약",
                        color = Gray600,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.wrapContentWidth(align = Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TitleText(text = "한 주 또는 한 달을 기준으로 분석하여 사용자에게 사용자의 건강에 대한 정보를 제공할 예정입니다. 차트, 표, 그래프 등의 시각화 자료를 적극 반영하려고 합니다.")
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
                    TitleText(text = "지난 주 나의 평균 열량")
                    Spacer(modifier = Modifier.height(8.dp))
                    ContentText(text = "1300 kcal")
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
                    TitleText(text = "지난 주 나의 평균 당질")
                    Spacer(modifier = Modifier.height(8.dp))
                    ContentText(text = "55 g")
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
                        text = "1050 g",
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
