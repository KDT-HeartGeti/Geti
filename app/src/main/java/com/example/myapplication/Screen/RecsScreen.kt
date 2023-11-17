package com.example.myapplication.Screen

import android.content.Intent
import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController

@Composable
fun RecsScreen(navController: NavController) {
    AndroidView(
        factory = { context ->
            val webView = WebView(context)
            webView.settings.javaScriptEnabled = true

            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    val url = request?.url.toString()
                    if (url.startsWith("")) {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(url)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
                        context.startActivity(intent)
                        return true
                    } else {
                        return super.shouldOverrideUrlLoading(view, request)
                    }
                }
            }



            val htmlContent = """
                <html>
                <head>
                    <style>
                       body { 
                            display: flex; 
                            justify-content: center; 
                            align-items: center; /* 수직 중앙 정렬 추가 */
                            min-height: 100vh; 
                            margin: 0; 
                        }
                        .center-content { 
                            text-align: center;
                            width: 100%; 
                        }
                        .container { /* 모든 iframe을 포함하는 컨테이너 */
                            display: flex;
                            flex-direction: column; /* 아이템들을 수직으로 정렬 */
                            align-items: center; /* 수직 방향의 중앙 정렬 */
                        }
                        .description {
                            margin-top: 10px; /* 설명과 iframe 사이의 간격 */
                            font-family: 'Noto Sans KR', sans-serif;
                            font-weight: bold;
                            font-size: 18px; /* 텍스트 크기 */
                            color: #333; /* 텍스트 색상 */
                            padding: 0 20px; /* 양 옆 공백 추가 */
                            width: calc(100% - 40px); /* padding을 고려한 너비 설정 */
                        }
                        .header-text {
                            font-size: 24px; /* 큰 폰트 사이즈 */
                            text-align: center;
                            margin-bottom: 20px; /* 헤더와 콘텐츠 사이 간격 */
                            font-weight: bold;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <p class="header-text">당뇨환자 건강기능식품 추천</p>
                        <div class="center-content">
                             <iframe src="https://coupa.ng/cexPlS" width="360" height="240" frameborder="0" scrolling="no" referrerpolicy="unsafe-url"></iframe>
                             <p class="description"> 녹우컴파운드 네레정 / 여주의 11배 천연인슐린 함유로 당뇨 환자의 혈당 관리에 도움을 줍니다. </p>
                        </div>
                        <div class="center-content">
                            <iframe src="https://coupa.ng/cex8qC" width="360" height="240" frameborder="0" scrolling="no" referrerpolicy="unsafe-url"></iframe>
                            <p class="description"> 뉴케어 당플랜 곡물맛 / 당뇨 환자가 식사 대신 먹을 수 있는 균형영양식으로 식이조절에 도움을 줍니다. </p>
                        </div>
                        <div class="center-content">
                            <iframe src="https://coupa.ng/ceya21" width="360" height="240" frameborder="0" scrolling="no" referrerpolicy="unsafe-url"></iframe>
                            <p class="description"> 닥터인슐로인 숫치로 여주즙 / 고형분 4% 국내 최대고함량 여주즙으로 당뇨 환자의 혈당 관리에 도움을 줍니다. </p>
                        </div>
                        <div class="center-content">
                            <iframe src="https://coupa.ng/ceya3M" width="360" height="240" frameborder="0" scrolling="no" referrerpolicy="unsafe-url"></iframe>
                            <p class="description"> 하이생 혈당밸런스 / 하루 1포로 혈당 상승 억제시켜서 당뇨 환자의 혈당 관리에 도움을 줍니다. </p>
                        </div>
                    </div>
                </body>
                </html>
            """.trimIndent()

            webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)
            webView
        },
        modifier = Modifier.fillMaxSize()
    )
}
