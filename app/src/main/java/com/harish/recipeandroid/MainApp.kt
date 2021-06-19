package com.harish.recipeandroid

import android.app.Application
import com.harish.recipeandroid.api.VolleySingleton
import com.harish.recipeandroid.datastore.PreferenceDataStore

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        VolleySingleton.init(this)
        PreferenceDataStore.init(this, "RecipeApp")
    }
}