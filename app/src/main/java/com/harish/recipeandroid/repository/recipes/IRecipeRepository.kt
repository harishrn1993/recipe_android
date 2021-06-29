package com.harish.recipeandroid.repository.recipes

import androidx.lifecycle.LiveData
import org.json.JSONObject

interface IRecipeRepository {
    companion object {
        val instance: IRecipeRepository = VolleyRecipeRepository()
    }

    suspend fun getAllRecipeResponse(params: JSONObject?): LiveData<Pair<String?, String?>>
    suspend fun getRecommendationResponse(): LiveData<Pair<String?, String?>>
    suspend fun getRecipeById(id: String): LiveData<Pair<String?, String?>>
}