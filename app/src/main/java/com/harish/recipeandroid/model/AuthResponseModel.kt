package com.harish.recipeandroid.model

data class AuthResponseModel(
    val data: Data,
    val status: String
)

data class Data(
    val token: String,
    val user: User
)