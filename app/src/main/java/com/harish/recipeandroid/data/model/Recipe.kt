package com.harish.recipeandroid.data.model

data class Recipe(
    val _id: String,
    val author: String,
    val cookTimeInMinutes: Int,
    val createdAt: String,
    val description: String,
    val difficultyLevel: String,
    val id: String,
    val ingredients: List<Ingredient>,
    val mealType: String,
    val servers: Int,
    val steps: List<Step>,
    val title: String
)