package com.example.myapplication.Screen

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.ui.theme.Gray200
import com.example.myapplication.ui.theme.Gray400
import com.example.myapplication.ui.theme.Gray50
import com.example.myapplication.ui.theme.Gray500
import com.example.myapplication.ui.theme.GraySkull
import com.example.myapplication.ui.theme.NeonBlue
import com.example.myapplication.ui.theme.NeonRed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URLDecoder
import java.net.URLEncoder


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoadingScreen(navController: NavController, encodedUri: String) {
    val encodedUri1 = URLEncoder.encode(encodedUri, "UTF-8")
    val selectedUri = URLDecoder.decode(encodedUri, "UTF-8")
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

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
                    .background(Gray50),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 모델 예측
                LoadingPrediction(context, selectedUri, coroutineScope, navController, encodedUri1)
                // 이미지 화면이 핸드폰마다 사이즈가 달라서 정삭각형 유지를 위해 덩어리를 나눴음.
                Spacer(modifier = Modifier.height(24.dp))
                // OutputScreen에서 Image 부분의 스켈레톤 ui
                LoadingImage()
                Spacer(modifier = Modifier.height(20.dp))
                // OutputScreen에서 영양정보 Text 부분의 스켈레톤 ui
                LoadingText()
            }
        }
    )
}

// 각 content의 기본 틀 함수
@Composable
fun SkullSurface(modifier: Modifier, roundPixel: Int) {
    Surface(
        shape = RoundedCornerShape(roundPixel.dp),
        color = GraySkull,
        modifier = modifier
    ) {}
}

@Composable
fun LoadingImage() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SkullSurface(
            modifier = Modifier
                .size(320.dp),
            roundPixel = 8
        )
    }
}


@Composable
fun LoadingText() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        SkullSurface(
            modifier = Modifier
                .width(80.dp)
                .height(24.dp),
            roundPixel = 12
        )
        Spacer(modifier = Modifier.height(8.dp))
        SkullSurface(
            modifier = Modifier
                .width(165.dp)
                .height(37.dp),
            roundPixel = 12
        )
        Spacer(modifier = Modifier.height(20.dp))
        SkullSurface(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            roundPixel = 12
        )
        Spacer(modifier = Modifier.height(12.dp))
        SkullSurface(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            roundPixel = 12
        )
        Spacer(modifier = Modifier.height(12.dp))
        SkullSurface(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            roundPixel = 12
        )
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
private fun LoadingPrediction(
    context: Context,
    selectedUri: String?,
    coroutineScope: CoroutineScope,
    navController: NavController,
    encodedUri1: String?
) {
    suspend fun uploadImage(imageUri: Uri): String? = withContext(Dispatchers.IO) {
        val url = "http://192.168.1.59:5000/prediction"
        val client = OkHttpClient()

        val inputStream = context.contentResolver.openInputStream(imageUri)
        val file = createFileFromInputStream(inputStream)

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "img",
                "img.jpg",
                RequestBody.create("img/*".toMediaTypeOrNull(), file)
            )
            .build()

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        var prediction: String? = null

        try {
            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                val jsonObject = JSONObject(responseBody)
                prediction = jsonObject.getString("prediction")
                Log.d("성공함", "이미지가 올라갔다? Respones : ${responseBody ?: "no data"}")
            } else {
                Log.e("망함", "망함")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return@withContext prediction
    }

    if (selectedUri != null) {
        coroutineScope.launch {
            val predictValue = uploadImage(Uri.parse(selectedUri))
            if (predictValue != null) {
                navController.navigate(
                    "output/${predictValue}/${encodedUri1}"
                )
            }
        }
    }
}

fun createFileFromInputStream(inputStream: InputStream?): File {
    val file = File.createTempFile("temp", null)
    inputStream?.use { input ->
        FileOutputStream(file).use { output ->
            input.copyTo(output)
        }
    }
    return file
}