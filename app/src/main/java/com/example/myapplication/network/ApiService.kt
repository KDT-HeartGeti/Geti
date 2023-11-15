//package com.example.myapplication.network
//
//import okhttp3.Callback
//import okhttp3.MultipartBody
//import retrofit2.Call
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.GET
//import retrofit2.http.Multipart
//import retrofit2.http.POST
//import retrofit2.http.Part
//
////interface ApiService {
////    @Multipart
////    @POST("/predict") // 서버의 경로에 맞게 수정해주세요
////    fun uploadImage(
////        @Part image: MultipartBody.Part
////    ): Call<ModelResult> // ModelResult는 서버 응답에 맞게 정의되어야 합니다.
////}
//
//val retrofit: Retrofit = Retrofit.Builder()
//    .baseUrl("http://127.0.0.1:5000/hello") // Flask 서버의 주소
//    .addConverterFactory(GsonConverterFactory.create()) // JSON을 코틀린 객체로 변환하기 위한 Gson 컨버터
//    .build()
//
//
//interface ApiService {
//    @GET("/hello")
//    fun getHello(): Call<String> // 반환 형식에 따라 변경 가능 (여기서는 String으로 예시)
//}
//
//fun sendRequest() {
//    val retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl("http://127.0.0.1:5000/hello") // Flask 서버의 주소
//        .addConverterFactory(GsonConverterFactory.create()) // JSON을 코틀린 객체로 변환하기 위한 Gson 컨버터
//        .build()
//
//    val apiService = retrofit.create(ApiService::class.java)
//
//    val call: Call<String> = apiService.getHello()
//    call.enqueue(object : Callback<String>, retrofit2.Callback<String> {
//        override fun onResponse(call: Call<String>, response: Response<String>) {
//            if (response.isSuccessful) {
//                val serverResponse: String? = response.body() // 서버에서 받은 응답 데이터
//                // 여기서 받은 데이터를 UI에 표시하거나 다른 처리를 수행할 수 있습니다.
//                println("서버 응답: $serverResponse")
//            }
//        }
//
//        override fun onFailure(call: Call<String>, t: Throwable) {
//            // 요청 실패 시 처리할 내용을 여기에 작성합니다.
//            println("요청 실패: ${t.message}")
//        }
//    })
//}
