package com.harish.recipeandroid.data.model

data class Recommendation(
    val breakfast: List<Recipe>,
    val dinner: List<Recipe>,
    val lunch: List<Recipe>,
    val snacks: List<Recipe>
)