package com.harish.recipeandroid.data.response

import com.harish.recipeandroid.data.model.Recipe

data class GetAllRecipeResponse(
    val data: List<Recipe>,
    val results: Int,
    val status: String
)