// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    // Add the dependency for the Google services Gradle plugin - Firebase
//    id("com.google.gms.google-services") version "4.4.0" apply false
    // 프로젝트 수준
    // 4.4.0은 client library와 호환되지 않는다는 에러가 발생하여 4.3.13으로 변경
    id("com.google.gms.google-services") version "4.3.13" apply false // 파이어베이스 플러그인
}