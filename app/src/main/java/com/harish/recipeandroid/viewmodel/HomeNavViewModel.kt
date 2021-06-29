package com.harish.recipeandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.harish.recipeandroid.R
import com.harish.recipeandroid.data.model.Recipe
import com.harish.recipeandroid.data.response.GetRecommendationResponse
import com.harish.recipeandroid.repository.recipes.IRecipeRepository
import kotlinx.coroutines.launch
import org.json.JSONObject

class HomeNavViewModel : ViewModel() {
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val showError: MutableLiveData<String> = MutableLiveData()
    val navigateTo: MutableLiveData<Int> = MutableLiveData()

    val breakfast: MutableLiveData<List<Recipe>> = MutableLiveData()
    val lunch: MutableLiveData<List<Recipe>> = MutableLiveData()
    val snacks: MutableLiveData<List<Recipe>> = MutableLiveData()
    val dinner: MutableLiveData<List<Recipe>> = MutableLiveData()

    private val repository: IRecipeRepository = IRecipeRepository.instance

    fun getAllRecipe(params: JSONObject?) {
        isLoading.value = true
        viewModelScope.launch {
            repository.getRecommendationResponse().observeForever {
                isLoading.value = false
                if (it.first == null) {
//                    showError.value = "Please make sure what you entered is correct, otherwise its our problem"
                    showError.value = it.second
                } else if (it.second == null) {
                    parseRecipeResponse(it.first!!)
                    navigateTo.value = R.id.action_loginFragment_to_homeFragment
                }
            }
        }
    }

    private fun parseRecipeResponse(first: String) {
        val getRecommendationResponse: GetRecommendationResponse =
            Gson().fromJson(first, GetRecommendationResponse::class.java)

        breakfast.value = getRecommendationResponse.data.breakfast
        lunch.value = getRecommendationResponse.data.lunch
        dinner.value = getRecommendationResponse.data.dinner
        snacks.value = getRecommendationResponse.data.snacks
    }

}