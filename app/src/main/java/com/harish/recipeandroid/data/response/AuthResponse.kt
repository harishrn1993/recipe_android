package com.harish.recipeandroid.data.model.auth

import com.harish.recipeandroid.data.model.User

data class AuthResponseModel(
    val data: Data,
    val status: String
)

data class Data(
    val token: String,
    val user: User
)