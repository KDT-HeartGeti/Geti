package com.example.myapplication

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myapplication.Screen.InputScreen
import com.example.myapplication.library.Post
import com.example.myapplication.library.RetrofitBuilder
import com.example.myapplication.ui.theme.MyApplicationTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InputScreen()
                }
            }
        }
    }
}

//
//@Composable
//fun MainScreen() {
//    RetrofitBuilder.api.getData(
//        RetrofitBuilder.api.getData("1").enqueue(object : Callback<List<Post>> {
//            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
//                if (response.isSuccessful) {
//                    Log.d(TAG, "getData onResponse()")
//                    response.body()?.get(3)?.let { Log.d(TAG, it.title) }
//                }
//            }
//
//            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
//                Log.d(TAG, t.localizedMessage)
//            }
//        }).toString()
//    )
//
//
//    val input = HashMap<String, Any>()
//    input["userId"] = 1
//    input["title"] = "title"
//    input["body"] = "body"
//
//    RetrofitBuilder.api.getPostList(input).enqueue(object : Callback<Post> {
//        override fun onResponse(call: Call<Post>, response: Response<Post>) {
//            if (response.isSuccessful) {
//                Log.d(TAG, "getPostList onResponse()")
//                Log.d(TAG, "${response.body()?.title}")
//            }
//        }
//
//        override fun onFailure(call: Call<Post>, t: Throwable) {
//            Log.d(TAG, t.localizedMessage)
//        }
//    })
//}