package com.harish.recipeandroid.datastore

import android.content.Context
import android.content.SharedPreferences


class PreferenceDataStore private constructor(context: Context, name: String) {
    private lateinit var sharedPref: SharedPreferences

    companion object {
        private var instance: PreferenceDataStore? = null
        fun init(context: Context, name: String) {
            if (instance == null) {
                instance = PreferenceDataStore(context, name)
            }
        }

        @Synchronized
        fun getInstance(): PreferenceDataStore? {
            if (instance == null) throw RuntimeException("Initialize PreferenceDataStore")
            return instance
        }
    }

    init {
        createDataStore(context, name)
    }

    private fun createDataStore(context: Context, name: String) {
        sharedPref = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    fun readInt(key: String): Int {
        return sharedPref.getInt(key, 0)
    }

    fun readString(key: String): String {
        return sharedPref.getString(key, "") ?: ""
    }

    fun readBool(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }

    fun writeInt(key: String?, value: Int) {
        with(sharedPref.edit()) {
            putInt(key, value)
            apply()
        }
    }

    fun writeString(key: String?, value: String) {
        with(sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }


    fun writeBool(key: String?, value: Boolean) {
        with(sharedPref.edit()) {
            putBoolean(key, value)
            apply()
        }
    }
}
