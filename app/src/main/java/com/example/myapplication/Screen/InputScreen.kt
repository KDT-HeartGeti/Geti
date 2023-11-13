package com.example.myapplication.Screen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.Component.uriToBitmap
import com.example.myapplication.NavScreen
import com.example.myapplication.R

//@Preview
//@Composable
//fun previewSurfaceIn() {
//    Surface(
//        Modifier.fillMaxSize()
//    ) {
//        InputScreen()
//    }
//}

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
            selectUri = uri
            takenPhoto = null
        }
    )
    // 비트맵 변환 변수
    val bitmap: Bitmap? = selectUri?.let { uriToBitmap(it, context) } ?: takenPhoto
    // 이미지 == null일 때 이미지
    val resources = context.resources
    val defaultImageBitmap =
        BitmapFactory.decodeResource(resources, R.drawable.no_image).asImageBitmap()
    // 카메라 이미지 런쳐
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { photo ->
            takenPhoto = photo
            selectUri = null
        }
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        // 상단 여백
        Spacer(modifier = Modifier.height(90.dp))
        // 입력 페이지에 나타날 이미지 공간
        Image(
            bitmap = bitmap?.asImageBitmap() ?: defaultImageBitmap, contentDescription = null,
            modifier = Modifier
                .border(width = 3.dp, Color.Gray)
                .size(300.dp),
            contentScale = ContentScale.Crop
        )
        // 이미지와 버튼 여백
        Spacer(modifier = Modifier.height(25.dp))
        // 이미지 업로드용 버튼
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.width(300.dp)
        ) {
            GetiButton(
                onclick = {
                    // 기본 카메라 앱 실행
                    cameraLauncher.launch(null)
                },
                text = "사진찍기"
            )
            GetiButton(
                onclick = {
                    launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                },
                text = "불러오기"
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        GetiButton(
            onclick = {
                if (selectUri != null || takenPhoto != null) {
                    navController.navigate(NavScreen.Output.route)
                }
            },
            text = "예측하기"
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
        Text(text = text, color = Color.Black, textAlign = TextAlign.Center, fontSize = 20.sp)
    }
}