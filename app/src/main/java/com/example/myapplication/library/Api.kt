package com.example.myapplication.library

import retrofit2.http.GET

interface Api {
    @GET("photos")
//    fun getPhotos(): String
    suspend fun getPhotos(): String

}

//object MarsApi {
//    val retrofitService : Api by lazy {
//        retrofit.create(Api::class.java)
//    }
//}

