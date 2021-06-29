package com.harish.recipeandroid.data.model

data class User(
    val _id: String,
    val cookedRecipes: List<String>,
    val createdAt: String,
    val email: String,
    val favoriteRecipes: List<Any>,
    val id: String,
    val isVerified: Boolean,
    val userType: String,
    val username: String
)
