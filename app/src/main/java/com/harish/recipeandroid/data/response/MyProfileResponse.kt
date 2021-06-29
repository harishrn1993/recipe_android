package com.harish.recipeandroid.data.model.auth

import com.harish.recipeandroid.data.model.User

data class MyProfileModel(
    val body: Body,
    val status: String
)

data class Body(
    val user: User
)