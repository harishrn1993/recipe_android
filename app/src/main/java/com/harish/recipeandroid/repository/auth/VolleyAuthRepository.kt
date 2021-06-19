package com.harish.recipeandroid.repository.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.AuthFailureError
import com.android.volley.toolbox.StringRequest
import com.harish.recipeandroid.api.VolleySingleton
import org.json.JSONObject


class VolleyAuthRepository : IAuthRepository {

    val URL: String = "http://192.168.56.1:5001/api/v1/users/"

    override suspend fun signIn(
        email: String,
        password: String
    ): MutableLiveData<Pair<String?, String?>> {
        val mutableLiveData: MutableLiveData<Pair<String?, String?>> = MutableLiveData()

        val stringReq = object : StringRequest(Method.POST, URL + "login", { response ->
            val strResp = response.toString()
            Log.d("VolleyAuthRepository", strResp)
            mutableLiveData.value = Pair(strResp, null)
        }, {
            Log.d("VolleyAuthRepository", it?.localizedMessage + "")
            mutableLiveData.value = Pair(null, it.localizedMessage)
        }) {
            override fun getBodyContentType(): String {
                return "application/json"
            }

            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json; charset=utf-8"
                return headers
            }

            override fun getBody(): ByteArray {
                val params: HashMap<Any?, Any?> = HashMap()
                params["email"] = email
                params["password"] = password
                return JSONObject(params).toString().toByteArray()
            }
        }
        VolleySingleton.getInstance()?.addToRequestQueue(stringReq)
        return mutableLiveData
    }

    override suspend fun signUp(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ): MutableLiveData<Pair<String?, String?>> {
        val mutableLiveData: MutableLiveData<Pair<String?, String?>> = MutableLiveData()

        val stringReq: StringRequest = object : StringRequest(Method.POST, URL + "signup", {
            val strResp = it.toString()
            Log.d("VolleyAuthRepository", strResp)
            mutableLiveData.value = Pair(strResp, null)
        }, {
            Log.d("VolleyAuthRepository", it?.localizedMessage + "")
            mutableLiveData.value = Pair(null, it.localizedMessage)
        }) {
            override fun getBodyContentType(): String {
                return "application/json"
            }

            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json; charset=utf-8"
                return headers
            }

            override fun getBody(): ByteArray {
                val params: HashMap<Any?, Any?> = HashMap()
                params["email"] = email
                params["password"] = password
                params["username"] = username
                params["confirmPassword"] = confirmPassword
                return JSONObject(params).toString().toByteArray()
            }
        }
        VolleySingleton.getInstance()?.addToRequestQueue(stringReq)
        return mutableLiveData
    }

    override suspend fun forgotPassword(email: String): MutableLiveData<Pair<String?, String?>> {
        val mutableLiveData: MutableLiveData<Pair<String?, String?>> = MutableLiveData()

        val stringReq: StringRequest =
            object : StringRequest(Method.POST, URL + "forgotPassword", {
                val strResp = it.toString()
                Log.d("VolleyAuthRepository", strResp)
                mutableLiveData.value = Pair(strResp, null)
            }, {
                Log.d("VolleyAuthRepository", it?.localizedMessage + "")
                mutableLiveData.value = Pair(null, it.localizedMessage)
            }) {
                override fun getBodyContentType(): String {
                    return "application/json"
                }

                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Content-Type"] = "application/json; charset=utf-8"
                    return headers
                }

                override fun getBody(): ByteArray {
                    val params: HashMap<Any?, Any?> = HashMap()
                    params["email"] = email
                    return JSONObject(params).toString().toByteArray()
                }
            }
        VolleySingleton.getInstance()?.addToRequestQueue(stringReq)
        return mutableLiveData
    }

    override suspend fun resendVerification(userId: String): MutableLiveData<Pair<String?, String?>> {
        val mutableLiveData: MutableLiveData<Pair<String?, String?>> = MutableLiveData()


        return mutableLiveData
    }
}