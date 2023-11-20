package com.example.myapplication.Screen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.Component.uriToBitmap
import com.example.myapplication.R
import com.example.myapplication.database.getFoodNutrientByName
import com.example.myapplication.ui.theme.Gray200
import com.example.myapplication.ui.theme.Gray400
import com.example.myapplication.ui.theme.Gray50
import com.example.myapplication.ui.theme.Gray500
import com.example.myapplication.ui.theme.Gray600
import com.example.myapplication.ui.theme.Gray900
import com.example.myapplication.ui.theme.NeonBlue
import com.example.myapplication.ui.theme.NeonRed
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutputActivity(navController: NavController, predictValue: String, selectedUri: String) {
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
                OutputScreen(navController, predictValue, selectedUri)
            }
        }
    )
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun OutputScreen(navController: NavController, predictValue: String, selectedUri: String) {
    val foodNutrient = getFoodNutrientByName(predictValue)
    val context = LocalContext.current
    val bitmap: Bitmap? = Uri.parse(selectedUri)?.let { uriToBitmap(it, context) }
    val resources = context.resources
    val defaultImageBitmap =
        BitmapFactory.decodeResource(resources, R.drawable.no_image).asImageBitmap()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                // painter = painterResource(id = R.drawable.pizza),
                bitmap = bitmap?.asImageBitmap() ?: defaultImageBitmap, contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(320.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Card(
                colors = CardDefaults.cardColors(Gray50),
                modifier = Modifier
                    .padding(0.dp)
                    .width(320.dp)
            ) {
                Column {
                    TitleText(text = "메뉴명")
                    Spacer(modifier = Modifier.height(8.dp))
                    foodNutrient?.let {
                        ContentText(text = it.menu)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Card(
                colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(320.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    TitleText(text = "열량")
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        foodNutrient?.let {
                            ContentText(text = "${it.kcalPer100g}kcal")
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        SubText(text = "(100g 기준)")
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row {
                        foodNutrient?.let {
                            ContentText(text = "${it.kcalPerUnit}kcal")
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        foodNutrient?.let {
                            SubText(text = "(${it.foodUnit} ${it.foodUnitGram}g 기준)")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
        item {
            Card(
                colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(320.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    TitleText(text = "당질")
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        foodNutrient?.let {
                            ContentText(text = "${it.sugarPer100g}g")
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        SubText(text = "(100g 기준)")
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row {
                        foodNutrient?.let {
                            ContentText(text = "${it.sugarPerUnit}g")
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        foodNutrient?.let {
                            SubText(text = "(${it.foodUnit} ${it.foodUnitGram}g 기준)")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
        item {
            Card(
                colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(320.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    TitleText(text = "위험도")
                    Spacer(modifier = Modifier.height(12.dp))
                    foodNutrient?.let {
                        DangerBox(dangerNum = it.danger)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "숫자가 클수록 위험합니다.",
                        color = Gray900,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
@Composable
fun TitleText(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        color = Gray600,
        textAlign = TextAlign.Start,
        modifier = Modifier.wrapContentWidth(align = Alignment.Start)
    )
}

@Composable
fun SubText(text: String) {
    Text(
        text = text,
        color = Gray600,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Start,
        modifier = Modifier.wrapContentWidth(align = Alignment.Start)
    )
}

@Composable
fun ContentText(text: String) {
    Text(
        text = text,
        color = Gray600,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
        modifier = Modifier.wrapContentWidth(align = Alignment.Start)
    )
}

@Composable
fun GetiCard(content: @Composable () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(20.dp)
            .width(320.dp)
    ) {
        content
    }
}

@Composable
fun FoodInfo(contentText: Double, subText: String) {
    Row {
        ContentText(text = contentText.toString())
        Spacer(modifier = Modifier.width(6.dp))
        SubText(text = subText)
    }
}

@Composable
fun NutrientInfo(
    titleText: String,
    quantityText100: Double,
    quantityTextUnit: Double,
    unitText: String
) {
    GetiCard {
        Column {
            TitleText(text = titleText)
            Spacer(modifier = Modifier.height(10.dp))
            FoodInfo(contentText = quantityText100, subText = "(100g 기준)")
            Spacer(modifier = Modifier.height(4.dp))
            FoodInfo(contentText = quantityTextUnit, subText = "(${unitText} 기준)")

        }
    }
}

@Composable
fun DangerBox(dangerNum: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 1..5) {
            Text(
                text = i.toString(),
                color = if (i == dangerNum) Color.White else Gray900,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = if (dangerNum == i) {
                            when (dangerNum) {
                                1 -> Color(0xFF9AE74B)
                                2 -> Color(0xFF9AE74B)
                                3 -> Color(0xFFF2E96B)
                                4 -> NeonRed
                                5 -> NeonRed
                                else -> Color.Cyan
                            }
                        } else {
                            Color.Transparent
                        },
                        shape = CircleShape
                    )
                    .padding(top = 5.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
    }
}