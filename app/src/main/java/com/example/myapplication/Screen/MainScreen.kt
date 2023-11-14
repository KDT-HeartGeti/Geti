package com.example.myapplication.Screen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.Component.handleButtonClick
import com.example.myapplication.Component.uriToBitmap
import com.example.myapplication.R
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.ops.ResizeOp

@Composable
fun MainScreen() {
    var selectUri by remember { // 갤러리 이미지 uri 객체
        mutableStateOf<Uri?>(null)
    }
    var takenPhoto by remember { // 기본 사진 앱 비트맵 객체
        mutableStateOf<Bitmap?>(null)
    }

    val context = LocalContext.current
    val launcher = // 갤러리 이미지 런쳐
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri ->
                selectUri = uri
                takenPhoto = null
            }
        )
    val cameraLauncher = // 카메라 이미지 런쳐
        rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicturePreview(),
            onResult = { photo ->
                takenPhoto = photo
                selectUri = null
            })
    val bitmap: Bitmap? = selectUri?.let { uriToBitmap(it, context) } ?: takenPhoto
    val resources = context.resources
    val defaultImageBitmap =
        BitmapFactory.decodeResource(resources, R.drawable.no_image).asImageBitmap()


    val imageProcessor = ImageProcessor.Builder()
        .add(NormalizeOp(0.0f, 255.0f))  // 이 줄 추가 안해서 입력값 달랐음 (1, 150, 150, 3)
//        .add(TransformToGrayScaleOp()) // 회색조 이미지, 라이브러리 tensorflow lite support 필요
        .add(ResizeOp(224, 224, ResizeOp.ResizeMethod.BILINEAR))
        .build()

    var outputText by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp),
    ) {
        Image(
            bitmap = bitmap?.asImageBitmap() ?: defaultImageBitmap, contentDescription = null,
            modifier = Modifier
                .size(224.dp),
        )
        Row {
            Button(
                onClick = {
                    launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                },
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 0.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 0.dp
                ),
                border = BorderStroke(1.dp, Color.Gray),
                modifier = Modifier.fillMaxWidth(0.5f),
            ) {
                Text(text = "photo")
            }
            Button(
                onClick = {
                    cameraLauncher.launch(null)  // 기본 카메라 앱 실행
                },
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 16.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 16.dp
                ),
                border = BorderStroke(1.dp, Color.Gray),
                modifier = Modifier.fillMaxWidth(1f),
            ) {
                Text(text = "camera")
            }
        }
        Button(
            onClick = {
                outputText = handleButtonClick(
                    context = context,
                    bitmap = bitmap,
                    imageProcessor = imageProcessor
                )
            },
            enabled = (bitmap != null)
        ) {
            Text(text = "Predict")
        }

        Text(
            text = outputText,
            fontSize = 50.sp,
            color = if (outputText == "organic") Color.Red else Color.Blue,
        )
    }
}



