package com.example.myapplication.network

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.File
import java.io.IOException

val client = OkHttpClient()

fun post(url: String, file: File, callback: Callback): Call {
    val requestBody = MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart("file", file.name,
            RequestBody.create("img/*".toMediaTypeOrNull(), file))
        .build()

    val request = Request.Builder()
        .url(url)
        .post(requestBody)
        .build()

    val call = client.newCall(request)
    call.enqueue(callback)
    return call
}

fun main() {
    val file = File("path/to/your/img.jpg")
    post("http://192.168.0.31:5000/prediction", file, object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            }

            // 여기에서 응답을 처리합니다.
            println(response.body?.string())
        }
    })
}