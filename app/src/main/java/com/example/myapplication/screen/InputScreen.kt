package com.example.myapplication.Screen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.Component.bitmapToUri
import com.example.myapplication.Component.uriToBitmap
import com.example.myapplication.R
import com.example.myapplication.ui.theme.Gray200
import com.example.myapplication.ui.theme.Gray400
import com.example.myapplication.ui.theme.Gray50
import com.example.myapplication.ui.theme.Gray500
import com.example.myapplication.ui.theme.Gray900
import com.example.myapplication.ui.theme.NeonBlue
import com.example.myapplication.ui.theme.NeonRed
import java.net.URLEncoder


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputActivity(navController: NavController) {
    var isToggled by remember { mutableStateOf(false) }

    val toggleImage by remember(isToggled) {
        mutableStateOf(if (isToggled) R.drawable.toggle_on_svgrepo_com else R.drawable.toggle_off_svgrepo_com)
    }

    Scaffold(
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
                    .background(Gray50)
                    .fillMaxWidth()
//                    .height(100.dp)
//                    .padding(start = 20.dp, end = 20.dp)
            ) {
                BottomBar(

                    navigationIcon = {
                        val currentRoute = navController.currentDestination?.route

                        IconButton(
                            onClick = { navController.navigate("calender") },
//                            modifier = Modifier
//                                .padding(start = 14.dp, top = 8.dp, end = 14.dp, bottom = 8.dp)
                        ) {
                            Column {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.calender),
                                    contentDescription = "내 기록 아이콘 (캘린더)",
                                    modifier = Modifier
                                        .size(40.dp),
                                    tint = if (currentRoute == "calender") NeonBlue else Gray200
                                )
                                Text(
                                    text = "내기록",
                                    color = if (currentRoute == "calender") NeonBlue else Gray500,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    },
                    actionIcon1 = {
                        val currentRoute = navController.currentDestination?.route

                        IconButton(
                            onClick = { navController.navigate("input") },
//                            modifier = Modifier
//                                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                        ) {
                            Column {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.info),
                                    contentDescription = "영양정보 아이콘",
                                    modifier = Modifier.size(40.dp),
                                    tint = if (currentRoute == "input") NeonBlue else Gray200
                                )
                                Text(
                                    text = "영양정보",
                                    color = if (currentRoute == "input") NeonBlue else Gray500,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    },
                    actionIcon2 = {
                        val currentRoute = navController.currentDestination?.route

                        IconButton(
                            onClick = { /* doSomething() */ },
//                            modifier = Modifier
//                                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                        ) {
                            Column {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.consult),
                                    contentDescription = "상담 아이콘",
                                    modifier = Modifier.size(40.dp),
//                                    tint = if (currentRoute == "") NeonRed else Gray200
                                    tint = Gray200
                                )
                                Text(
                                    text = "상담",
//                                    color = if (currentRoute == "calender") NeonRed else Gray500,
                                    color = Gray500,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    },
                    actionIcon3 = {
                        val currentRoute = navController.currentDestination?.route

                        IconButton(
                            onClick = { navController.navigate("state") },
//                            modifier = Modifier
//                                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                        ) {
                            Column {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.state),
                                    contentDescription = "내 상태 아이콘",
                                    modifier = Modifier.size(40.dp),
                                    tint = if (currentRoute == "state") NeonBlue else Gray200
                                )
                                Text(
                                    text = "내상태",
                                    color = if (currentRoute == "state") NeonBlue else Gray500,
                                    fontSize = 16.sp,
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
                InputScreen(navController)
            }
        }
    )
}

@Composable
fun InputScreen(navController: NavController) {
    // 갤러리 이미지 uri 객체
    var selectUri by remember {
        mutableStateOf<Uri?>(null)
    }
    // 기본 사진 앱 비트맵 객체
    var takenPhoto by remember {
        mutableStateOf<Bitmap?>(null)
    }
    val context = LocalContext.current

    // 갤러리 이미지 런쳐
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                selectUri = uri
                takenPhoto = null
            }
        }
    )
    // 비트맵 변환 변수
    val bitmap: Bitmap? = selectUri?.let { uriToBitmap(it, context) } ?: takenPhoto

    // 카메라 이미지 런쳐
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { photo ->
            if (photo != null) {
                takenPhoto = photo
                selectUri = bitmapToUri(context, takenPhoto!!)
            }
        }
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        // 입력 페이지에 나타날 이미지 공간
        // 사진이 업로드 되었을 시
        if (bitmap != null) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .size(320.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )
        } else {
            // 사진이 업로드 되지 않았을 때
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.image),
                contentDescription = null,
                modifier = Modifier
                    .size(320.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }

        // 이미지와 버튼 여백
        Spacer(modifier = Modifier.height(5.dp))

        // 사진찍기, 사진 불러오기 버튼
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.width(320.dp)
        ) {
            CameraAndGallery(cameraLauncher, launcher)
        }

        Spacer(modifier = Modifier.height(15.dp))

        // 영양정보 분석하기 버튼
        GetiButton(
            onclick = {
                if (selectUri != null) {
                    val encodedUri = URLEncoder.encode(selectUri.toString(), "UTF-8")
                    navController.navigate("loading/${encodedUri}")
                }
            }, text = "영양정보 분석하기"
        )

        // 영양정보 분석하기와 광고배너의 간격
        Spacer(modifier = Modifier.height(15.dp))

        // 광고 배너
        GetiButton(
            onclick = {
                navController.navigate("recs")
            }, text = "당뇨환자 건강기능식품 추천"
        )
    }
}

@Composable
private fun CameraAndGallery(
    cameraLauncher: ManagedActivityResultLauncher<Void?, Bitmap?>,
    launcher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.width(320.dp)
    ) {
        // 카메라 실행 버튼
        CameraGalleryButton(
            onclick = {
                // 기본 카메라 앱 실행
                cameraLauncher.launch(null)
            },
            text = "사진 찍기",
            iconPath = R.drawable.camera_svgrepo_com
        )
        Spacer(modifier = Modifier.width(8.dp))
        // 사진 선택 도구 실행 버튼
        CameraGalleryButton(
            onclick = {
                launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            },
            text = "사진 불러오기",
            iconPath = R.drawable.image_on_svgrepo_com
        )
    }
}

// 분석하기와 추천 버튼 함수
@Composable
fun GetiButton(onclick: () -> Unit, text: String) {
    Button(
        onClick = onclick,
        colors = ButtonDefaults.buttonColors(NeonBlue),
        modifier = Modifier
            .width(320.dp)
            .height(60.dp)
            .padding(start = 0.dp, end = 0.dp),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp, start = 20.dp, end = 20.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
        )
    }
}

// 사진 불러오기, 사진 선택 버튼 함수
@Composable
fun CameraGalleryButton(onclick: () -> Unit, text: String, iconPath: Int) {
    IconButton(
        onClick = onclick,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFCACDD1), RoundedCornerShape(8.dp))
            .width(156.dp)
            .height(100.dp)
            .padding(0.dp),
        colors = IconButtonDefaults.iconButtonColors(Color.White)
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = iconPath),
                modifier = Modifier
                    .size(width = 28.dp, height = 28.dp),
                contentDescription = text
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Gray900,
                modifier = Modifier.wrapContentSize(Alignment.Center)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    navigationIcon: @Composable () -> Unit,
    actionIcon: @Composable () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 22.sp),
                textAlign = TextAlign.Start
            )
        },
        navigationIcon = navigationIcon,
        actions = {
            actionIcon()
        }
    )
}

@Composable
fun BottomBar(
    navigationIcon: @Composable () -> Unit,
    actionIcon1: @Composable () -> Unit,
    actionIcon2: @Composable () -> Unit,
    actionIcon3: @Composable () -> Unit
) {
    BottomAppBar(
//        contentPadding = PaddingValues(start = 20.dp, end = 20.dp), // 10
        modifier = Modifier.background(Gray50).fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Gray50),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            navigationIcon()
            actionIcon1()
            actionIcon2()
            actionIcon3()
        }
    }
}