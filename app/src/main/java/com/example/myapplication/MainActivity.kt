package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.Screen.CalendarScreen
import com.example.myapplication.Screen.InputActivity
import com.example.myapplication.Screen.InputScreen
import com.example.myapplication.Screen.OutputScreen
import com.example.myapplication.Screen.RecsScreen
import com.example.myapplication.Screen.LoadingScreen
import com.example.myapplication.Screen.Login
import com.example.myapplication.Screen.MyState
import com.example.myapplication.Screen.MyStateActivity
import com.example.myapplication.Screen.OutputActivity
import com.example.myapplication.Screen.StartLoadingScreen
//import com.example.myapplication.Screen.StartLoadingScreen
import com.example.myapplication.data.NavScreen
import com.example.myapplication.ui.theme.Gray200
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.firestore


class MainActivity : ComponentActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 구글 로그인 인증
        mAuth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)


        setContentView(R.layout.activity_main)

        val webView: WebView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true

        class WebViewClientClass : WebViewClient() {
            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url.startsWith("intent://")) {
                    try {
                        val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                        val existPackage =
                            packageManager.getLaunchIntentForPackage(intent.getPackage()!!)
                        if (existPackage != null) {
                            startActivity(intent)
                        } else {
                            val marketIntent = Intent(Intent.ACTION_VIEW)
                            marketIntent.data =
                                Uri.parse("market://details?id=" + intent.getPackage())
                            startActivity(marketIntent)
                        }
                        return true
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                    view.loadUrl(url)
                }
                return true
            }
        }

        webView.webViewClient = WebViewClientClass()

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Gray200
                ) {
                    val navController = rememberNavController()
                    val user: FirebaseUser? = mAuth.currentUser
                    val startDestination = remember {
                        if (user == null) {
                            NavScreen.Login.route
                        } else {
                            NavScreen.Start.route
                        }
                    }

                    val signInIntent = googleSignInClient.signInIntent
                    val launcher =
                        rememberLauncherForActivityResult(contract = StartActivityForResult()) { result ->
                            val data = result.data
                            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                            val exception = task.exception
                            if (task.isSuccessful) {
                                try {
                                    val account = task.getResult(ApiException::class.java)!!
                                    firebaseAuthWithGoogle(
                                        account.idToken!!,
                                        mAuth,
                                        navController = navController,
                                        activity = this
                                    )
                                } catch (e: Exception) {
                                    Log.d("SignIn", "로그인 실패")
                                }
                            } else {
                                Log.d("SignIn", exception.toString())
                            }
                        }


                    // navigation 변수 선언
//                    val navController = rememberNavController()
                    // 앱 처음 실행시 나올 화면
//                    val startDestination = remember {
//                        NavScreen.Start.route
//                    }

                    // navigation
                    NavHost(navController = navController, startDestination = startDestination) {
                        composable(NavScreen.Login.route) {
                            Login(
                                navController,
                                { launcher.launch(signInIntent) })
                        }
                        composable(NavScreen.Start.route) { StartLoadingScreen(navController) }
                        composable(NavScreen.MyState.route) { MyStateActivity(navController) }
                        composable(NavScreen.Recs.route) { RecsScreen(navController) }
                        composable(NavScreen.Calender.route) { CalendarScreen(navController) }
                        composable(NavScreen.Input.route) { InputActivity(navController) }
                        composable(NavScreen.Loading.route) {
                            LoadingScreen(
                                navController,
                                it.arguments?.getString("encodedUri") ?: ""
                            )
                        }
                        composable(NavScreen.Output.route) {
                            val predictValue = it.arguments?.getString("predictValue") ?: ""
                            val selectedUri = it.arguments?.getString("encodedUri") ?: ""
                            OutputActivity(navController, predictValue, selectedUri)
                        }
                    }
                }
            }
        }
    }
}

private fun firebaseAuthWithGoogle(
    idToken: String,
    mAuth: FirebaseAuth,
    navController: NavController,
    activity: Activity
) {
    val credential = GoogleAuthProvider.getCredential(idToken, null)
    mAuth.signInWithCredential(credential)
        .addOnCompleteListener(activity) { task ->
            if (task.isSuccessful) {
                // SignIn Successful
                val currentUser = mAuth.currentUser
                currentUser?.let {
                    // Check if the user document already exists in Firestore
                    val db = Firebase.firestore
                    val uid = it.uid
                    val userRef = db.collection("users").document(uid)

                    userRef.get()
                        .addOnSuccessListener { document ->
                            if (document.exists()) {
                                // User document already exists, no need to save again
                                Log.d("Firestore", "User document already exists")
                            } else {
                                // User document doesn't exist, save the data
                                val googleSignInAccount =
                                    GoogleSignIn.getLastSignedInAccount(activity)
                                val profileImageUri = googleSignInAccount?.photoUrl

                                val user = hashMapOf(
                                    "email" to it.email,
                                    "displayName" to it.displayName,
                                    "photoUrl" to profileImageUri.toString(), // Add the profile image URL
                                    // Add other fields as needed
                                )
                                userRef.set(user)
                                    .addOnSuccessListener {
                                        Log.d(
                                            "Firestore",
                                            "User document successfully written!"
                                        )
                                    }
                                    .addOnFailureListener { e ->
                                        Log.e("Firestore", "Error writing document", e)
                                    }
                            }

                            navController.popBackStack()
                            navController.navigate(NavScreen.Start.route)
                        }
                        .addOnFailureListener { e ->
                            Log.e("Firestore", "Error checking document existence", e)
                        }
                }
                Toast.makeText(activity, "로그인 성공", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }
}