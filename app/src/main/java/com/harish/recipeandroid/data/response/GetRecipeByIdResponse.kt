package com.harish.recipeandroid.data.response

import com.harish.recipeandroid.data.model.Recipe

data class GetRecipeByIdResponse(
    val data: Recipe,
    val status: String
)