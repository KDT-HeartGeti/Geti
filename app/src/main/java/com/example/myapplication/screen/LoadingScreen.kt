package com.example.myapplication.Screen

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.ui.theme.GraySkull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.IOException
import java.net.URLDecoder
import java.net.URLEncoder


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoadingScreen(navController: NavController, encodedUri: String) {
    val encodedUri1 = URLEncoder.encode(encodedUri, "UTF-8")
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val selectedUri = URLDecoder.decode(encodedUri, "UTF-8")

    suspend fun uploadImage(imageUri: Uri): String? = withContext(Dispatchers.IO) {
        val url = "http://192.168.35.227:5000/prediction"
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 이미지 화면이 핸드폰마다 사이즈가 달라서 정삭각형 유지를 위해 덩어리를 나눴음.
        Spacer(modifier = Modifier.height(24.dp))
        // OutputScreen에서 Image 부분의 스켈레톤 ui
        LoadingImage()
        Spacer(modifier = Modifier.height(20.dp))
        // OutputScreen에서 영양정보 Text 부분의 스켈레톤 ui
        LoadingText()
    }
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

//
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

// 데이터 리스트로 전달할 때
//                var dataList = mutableListOf(predictValue, selectedUri)
//                dataList.add(predictValue)
//                dataList.add(selectedUri)

//@Preview(showBackground = true)
@Composable
fun PreviewLoading() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        LoadingImage()
        Spacer(modifier = Modifier.height(20.dp))
        LoadingText()
    }
}