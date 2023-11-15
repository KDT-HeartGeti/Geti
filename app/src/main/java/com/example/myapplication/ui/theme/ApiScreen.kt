//package com.example.myapplication.ui.theme
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import com.example.myapplication.network.ApiService
//import com.example.myapplication.ui.theme.ui.theme.MyApplicationTheme
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import okhttp3.MediaType.Companion.toMediaTypeOrNull
//import okhttp3.MultipartBody
//import okhttp3.RequestBody
//import java.io.File
//
//class MainActivity : ComponentActivity() {
//    private val BASE_URL = "http://your_flask_server_ip:port" // Flask 서버 주소 입력
//    private var apiService: ApiService
//
//    init {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        apiService = retrofit.create(ApiService::class.java)
//    }
//
//    private fun uploadImageToServer(file: File) {
//        val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
//        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
//
//        val call: Call<ModelResult> = apiService.uploadImage(body)
//
//        call.enqueue(object : Callback<ModelResult> {
//            override fun onResponse(call: Call<ModelResult>, response: Response<ModelResult>) {
//                if (response.isSuccessful) {
//                    val result = response.body() // 서버에서 받은 결과(result)를 처리
//                    // TODO: 결과 처리 로직 작성
//                } else {
//                    // 서버 요청 실패 시 처리
//                }
//            }
//
//            override fun onFailure(call: Call<ModelResult>, t: Throwable) {
//                // 네트워크 오류 처리
//            }
//        })
//    }
//
//    // 이미지를 업로드하고 싶은 버튼 또는 이벤트에서 호출
//    private fun onUploadButtonClick() {
//        val file = File("/path/to/your/image.jpg") // 실제 파일 경로로 수정해주세요
//        uploadImageToServer(file)
//    }
//}
//
