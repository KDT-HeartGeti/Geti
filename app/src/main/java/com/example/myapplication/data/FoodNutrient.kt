package com.example.myapplication.data

data class FoodNutrient(
    val menu: String,
    val danger: Int,
    val foodUnit: String,
    val foodUnitGram: Int,
    val kcalPer100g: Double,
    val kcalPerUnit: Double,
    val sugarPer100g: Double,
    val sugarPerUnit: Double
)
