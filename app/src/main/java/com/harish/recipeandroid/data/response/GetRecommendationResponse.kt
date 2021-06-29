package com.harish.recipeandroid.data.response

import com.harish.recipeandroid.data.model.Recommendation

data class GetRecommendationResponse(
    val data: Recommendation,
    val status: String
)