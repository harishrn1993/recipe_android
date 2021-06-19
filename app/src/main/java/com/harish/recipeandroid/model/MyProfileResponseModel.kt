package com.harish.recipeandroid.model

data class MyProfileModel(
    val body: Body,
    val status: String
)

data class Body(
    val user: User
)