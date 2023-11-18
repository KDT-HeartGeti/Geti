package com.example.myapplication.Screen

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
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

    suspend fun uploadImage(imageUri: Uri): String? = withContext(Dispatchers.IO)  {
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
            val predictValue = uploadImage(Uri.parse(selectedUri))
            if (predictValue != null) {
                navController.navigate(
                    "output/${predictValue}/${encodedUri1}"
                )
            }
        }
    }
    
    Column {
        Text(text = "Loading...😂😂")
    }
}

// 데이터 리스트로 전달할 때
//                var dataList = mutableListOf(predictValue, selectedUri)
//                dataList.add(predictValue)
//                dataList.add(selectedUri)