package com.example.myapplication.Screen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.Component.bitmapToUri
import com.example.myapplication.Component.uriToBitmap
import com.example.myapplication.R
import java.net.URLEncoder


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputActivity(navController: NavController) {
    var isToggled by remember { mutableStateOf(false) }

    val toggleImage: Painter = if (isToggled) {
        painterResource(R.drawable.toggle_on)
    } else {
        painterResource(R.drawable.toggle_off)
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "홍길동",
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "프로필 사진"
                        )
                    }
                },
                actionIcon = {
                    IconButton(
                        onClick = { isToggled = !isToggled }
                    ) {
                        Icon(
                            painter = toggleImage,
                            contentDescription = "토글 아이콘"
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
                                .size(width = 80.dp, height = 78.dp)
                                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.calender),
                                contentDescription = "내 기록 아이콘 (캘린더)",
                                modifier = Modifier.fillMaxSize()
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
                            onClick = { /* doSomething() */ },
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
            ) {
                InputScreen(navController)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
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

    // 이미지 == null일 때 이미지
    val resources = context.resources
    val defaultImageBitmap =
        BitmapFactory.decodeResource(resources, R.drawable.defalut_image).asImageBitmap()

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

    var menu by remember { mutableStateOf("") }
    var menuName: String? = null
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        // 입력 페이지에 나타날 이미지 공간
        Image(
            bitmap = bitmap?.asImageBitmap() ?: defaultImageBitmap, contentDescription = null,
            modifier = Modifier
                .border(width = 3.dp, Color.Gray)
                .size(320.dp),
            contentScale = ContentScale.Crop
        )

        // 이미지와 버튼 여백
        Spacer(modifier = Modifier.height(5.dp))

        // 사진찍기, 사진 불러오기 버튼
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.width(320.dp)
        ) {
            CameraAndGallery(cameraLauncher, launcher)
        }
        //
        Spacer(modifier = Modifier.height(5.dp))

        // 영양정보 분석하기 버튼
        IconButton(
            onClick = {
                if (selectUri != null) {
                    val encodedUri = URLEncoder.encode(selectUri.toString(), "UTF-8")
                    navController.navigate("loading/${encodedUri}")
                }
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.predict),
                contentDescription = "영양정보 분석하기"
            )
        }

        // 영양정보 분석하기와 광고배너의 간격
        Spacer(modifier = Modifier.height(5.dp))

        // 광고 배너
        IconButton(
            onClick = {
                navController.navigate("recs")
            }) {
            Icon(
                painter = painterResource(id = R.drawable.banner),
                contentDescription = "당뇨환자 건강기능식품 추천"
            )
        }

        Spacer(modifier = Modifier.height(5.dp))
        Row {
            // 메뉴 이름 작성 TextField
            TextField(
                value = menu,
                onValueChange = { menu = it },
                label = { Text(text = "음식이름을 입력하세요.") }
            )
            // OutputScreen으로 넘겨주는 버튼
            Button(
                onClick = {
                    // 메뉴를 고정된 상태로 OutputScreen으로 넘겨주기 위한 변수 할당
                    menuName = menu
                    // 버튼 클릭시 OutputScreen으로 menuName 전달하면서 이동
//                    navController.navigate("output/$menuName")
                }
            ) {
                Text(text = "입력")
            }
        }
    }
}

@Composable
private fun CameraAndGallery(
    cameraLauncher: ManagedActivityResultLauncher<Void?, Bitmap?>,
    launcher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>
) {
    val iconSize = 156.dp

    // 카메라 실행 버튼
    IconButton(
        onClick = {
            // 기본 카메라 앱 실행
            cameraLauncher.launch(null)
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.camera),
            modifier = Modifier
                .size(width = 156.dp, height = 100.dp),
            contentDescription = "사진 찍기"
        )
    }
    IconButton(
        onClick = {
            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.import_image),
            modifier = Modifier
                .size(width = 156.dp, height = 100.dp),
            contentDescription = "사진 불러오기"
        )
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
        Text(
            text = text,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    navigationIcon: @Composable () -> Unit,
    actionIcon: @Composable () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 27.sp)
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
        contentPadding = PaddingValues(horizontal = 0.dp),
        modifier = Modifier.background(MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            navigationIcon()
            actionIcon1()
            actionIcon2()
            actionIcon3()
        }
    }
}