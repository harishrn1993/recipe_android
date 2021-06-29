package com.harish.recipeandroid.repository.recipes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.toolbox.StringRequest
import com.harish.recipeandroid.api.VolleySingleton
import org.json.JSONObject

class VolleyRecipeRepository : IRecipeRepository {
    val URL: String = "http://192.168.56.1:5001/api/v1/recipes/"
    override suspend fun getAllRecipeResponse(params: JSONObject?): LiveData<Pair<String?, String?>> {
        val mutableLiveData: MutableLiveData<Pair<String?, String?>> = MutableLiveData()
        val stringReq = object : StringRequest(Method.GET, URL + "getAll", { response ->
            val strResp = response.toString()
            Log.d("VolleyRecipeRepository", strResp)
            mutableLiveData.value = Pair(strResp, null)
        }, {
            Log.e("VolleyRecipeRepository", it?.localizedMessage + "")
            mutableLiveData.value = Pair(null, it.localizedMessage)
        }) {
            override fun getBodyContentType(): String {
                return "application/json"
            }
        }
        VolleySingleton.getInstance()?.addToRequestQueue(stringReq)
        return mutableLiveData
    }

    override suspend fun getRecommendationResponse(): LiveData<Pair<String?, String?>> {
        val mutableLiveData: MutableLiveData<Pair<String?, String?>> = MutableLiveData()
        val stringReq = object : StringRequest(Method.GET, URL + "getRecommeded", { response ->
            val strResp = response.toString()
            Log.d("VolleyRecipeRepository", strResp)
            mutableLiveData.value = Pair(strResp, null)
        }, {
            Log.e("VolleyRecipeRepository", it?.localizedMessage + "")
            mutableLiveData.value = Pair(null, it.localizedMessage)
        }) {
            override fun getBodyContentType(): String {
                return "application/json"
            }
        }
        VolleySingleton.getInstance()?.addToRequestQueue(stringReq)
        return mutableLiveData
    }

    override suspend fun getRecipeById(id: String): LiveData<Pair<String?, String?>> {
        val mutableLiveData: MutableLiveData<Pair<String?, String?>> = MutableLiveData()

        return mutableLiveData
    }
}