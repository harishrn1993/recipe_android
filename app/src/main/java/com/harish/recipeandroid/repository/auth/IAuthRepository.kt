package com.harish.recipeandroid.repository.auth

import androidx.lifecycle.MutableLiveData

interface IAuthRepository {
    suspend fun signIn(email: String, password: String): MutableLiveData<Pair<String?, String?>>
    suspend fun signUp(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ): MutableLiveData<Pair<String?, String?>>

    suspend fun forgotPassword(email: String): MutableLiveData<Pair<String?, String?>>
    suspend fun resendVerification(userId: String): MutableLiveData<Pair<String?, String?>>
}