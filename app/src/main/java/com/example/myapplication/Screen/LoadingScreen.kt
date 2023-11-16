package com.example.myapplication.Screen

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.IOException
import java.net.URLDecoder


//
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoadingScreen(navController: NavController, selectUri: String) {
    val coroutineScope = rememberCoroutineScope()
    // 맵핑한 함수에서 해당 데이터 클래스의 value값 가져와서 변수에 할당
    val context = LocalContext.current
    val selectedUri = URLDecoder.decode(selectUri, "UTF-8")
//    var selectedUri by remember { mutableStateOf<Uri?>(null) }

    suspend fun UploadImage(imageUri: Uri): String? = withContext(Dispatchers.IO)  {
        // navDeepLink
        val url = "http://192.168.0.31:5000/prediction"
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
                // Image uploaded successfully
                val responseBody = response.body?.string()

                val jsonObject = JSONObject(responseBody)
                prediction = jsonObject.getString("prediction")


                Log.d("성공함", "이미지가 올라갔다? Respones : ${responseBody?: "no data"}")
            } else {
                Log.e("망함", "망함")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return@withContext prediction
    }

    if(selectedUri != null) {
        coroutineScope.launch {
            val predictValue = UploadImage(Uri.parse(selectedUri))
            if (predictValue != null) {
//                var dataList = mutableListOf<String>()
//                dataList.add(predictValue)
//                dataList.add(selectUri)
                navController.navigate("output/${predictValue}")
            }
        }
    }
    
    Column {
        Text(text = "Loading...😂😂")
    }
}