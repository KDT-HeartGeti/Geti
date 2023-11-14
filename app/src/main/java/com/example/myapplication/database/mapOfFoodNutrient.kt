package com.example.myapplication.database

import com.example.myapplication.data.FoodNutrient

//val pizza = FoodNutrient("피자", 5, "1조각", 150, 256.0, 384.0, 3.2, 4.8)
//val mulhoea = FoodNutrient("물회", 4, "1인분", 700, 76.0, 532.0, 2.9, 20.3)
//val younkhoea = FoodNutrient("육회", 3, "1그릇", 90, 141.0, 126.9, 5.3, 4.8)
//val chickensoup = FoodNutrient("삼계탕", 4, "1인분", 500, 96.0, 480.0, 0.4, 2.0)
//val punkinsoup = FoodNutrient("호박죽", 3, "1그릇", 400, 61.0, 183.0, 3.6, 10.8)
//val bossam = FoodNutrient("보쌈", 2, "1인분", 300, 290.0, 870.0, 0.5, 1.5)
//val thoekboekgi = FoodNutrient("떡볶이", 5, "1인분", 300, 147.0, 441.0, 21.2, 63.6)
//val zeayoukboekem = FoodNutrient("제육볶음", 4, "1인분", 300, 194.0, 582.0, 1.0, 3.0)
//val zazangmeon = FoodNutrient("자장면", 5, "1인분", 600, 104.0, 624.0, 7.2, 43.2)
//val zzambbong = FoodNutrient("짬뽕", 5, "1인분", 600, 63.0, 378.0, 7.8, 46.8)
//val menuMap = mapOf(
//    "피자" to pizza,
//    "물회" to mulhoea,
//    "육회" to younkhoea,
//    "삼계탕" to chickensoup,
//    "호박죽" to punkinsoup,
//    "보쌈" to bossam,
//    "떡볶이" to thoekboekgi,
//    "제육볶음" to zeayoukboekem,
//    "자장면" to zazangmeon,
//    "짬뽕" to zzambbong
//)

fun getFoodNutrientByName(menuName: String): FoodNutrient? {
    return when (menuName) {
        "피자" -> FoodNutrient("피자", 5, "1조각", 150, 256.0, 384.0, 3.2, 4.8)
        "물회" -> FoodNutrient("물회", 4, "1인분", 700, 76.0, 532.0, 2.9, 20.3)
        "육회" -> FoodNutrient("육회", 3, "1그릇", 90, 141.0, 126.9, 5.3, 4.8)
        "삼계탕" -> FoodNutrient("삼계탕", 4, "1인분", 500, 96.0, 480.0, 0.4, 2.0)
        "호박죽" -> FoodNutrient("호박죽", 3, "1그릇", 400, 61.0, 183.0, 3.6, 10.8)
        "보쌈" -> FoodNutrient("보쌈", 2, "1인분", 300, 290.0, 870.0, 0.5, 1.5)
        "떡볶이" -> FoodNutrient("떡볶이", 5, "1인분", 300, 147.0, 441.0, 21.2, 63.6)
        "제육볶음" -> FoodNutrient("제육볶음", 4, "1인분", 300, 194.0, 582.0, 1.0, 3.0)
        "자장면" -> FoodNutrient("자장면", 5, "1인분", 600, 104.0, 624.0, 7.2, 43.2)
        "짬뽕" -> FoodNutrient("짬뽕", 5, "1인분", 600, 63.0, 378.0, 7.8, 46.8)
        else -> null
    }
}