package com.harish.recipeandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.harish.recipeandroid.R
import com.harish.recipeandroid.data.model.auth.AuthResponseModel
import com.harish.recipeandroid.datastore.PreferenceDataStore
import com.harish.recipeandroid.datastore.UiData
import com.harish.recipeandroid.repository.auth.IAuthRepository
import com.harish.recipeandroid.ui.fragments.authentication.AuthFragment.STATE
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val TAG: String = "AuthViewModel"
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val showError: MutableLiveData<String> = MutableLiveData()
    val navigateTo: MutableLiveData<Int> = MutableLiveData()

    var username: String? = ""
    var email: String? = ""
    var viewState: MutableLiveData<STATE> = MutableLiveData(STATE.REGISTER)
    private val authRepo: IAuthRepository = IAuthRepository.instance

    fun signIn(email: String, password: String) {
        isLoading.value = true
        if (!validateEmail(email)) {
            return
        }
        if (!validatePassword(password)) {
            return
        }
        viewModelScope.launch {
            authRepo.signIn(email, password).observeForever {
                isLoading.value = false
                if (it.first == null) {
//                    showError.value = "Please make sure what you entered is correct, otherwise its our problem"
                    showError.value = it.second
                } else if (it.second == null) {
                    parseAuthResponse(it.first!!)
                    navigateTo.value = R.id.action_loginFragment_to_homeFragment
                }
            }
        }
    }

    fun signUp(email: String, username: String, password: String, confirmPassword: String) {
        isLoading.value = true
        if (!validateEmail(email)) {
            return
        }
        if (!validatePassword(password)) {
            return
        }
        if (!validateUserName(username)) {
            return
        }
        if (!comparePasswordsAndValidate(password, confirmPassword)) {
            return
        }
        viewModelScope.launch {
            authRepo.signUp(email, username, password, confirmPassword).observeForever {
                isLoading.value = false
                if (it.first == null) {
//                    showError.value = "Please make sure what you entered is correct, otherwise its our problem"
                    showError.value = it.second
                } else if (it.second == null) {
                    parseAuthResponse(it.first!!)
                    navigateTo.value = R.id.action_loginFragment_to_homeFragment
                }
            }
        }
    }

    private fun parseAuthResponse(jsonString: String) {
        val authResponseModel: AuthResponseModel =
            Gson().fromJson(jsonString, AuthResponseModel::class.java)
        UiData.user = authResponseModel.data.user
        UiData.token = authResponseModel.data.token
        PreferenceDataStore.getInstance()?.writeString("token", authResponseModel.data.token)
    }

    fun forgotPassword(email: String) {
        isLoading.value = true
        if (!validateEmail(email)) {
            return
        }
        viewModelScope.launch {
            authRepo.forgotPassword(email).observeForever {
                isLoading.value = false
            }
        }
    }

    fun skipClicked() {
        UiData.user = null
        navigateTo.value = R.id.action_loginFragment_to_homeFragment
    }

    //Validation methods

    private fun validatePassword(password: String): Boolean {
        if (password.isNotEmpty() && password.length > 7) {
            return true
        }
        showError.value = "Password must more be 8 characters"
        return false
    }

    private fun validateEmail(email: String): Boolean {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true
        }
        showError.value = "Enter valid email"
        return false
    }

    private fun comparePasswordsAndValidate(password: String, confirmPassword: String): Boolean {
        if (validatePassword(password) && validatePassword(confirmPassword)) {
            if (password == confirmPassword) {
                return true
            }
            showError.value = "Passwords does not match"
            return false
        }
        return false
    }

    private fun validateUserName(username: String): Boolean {
        if (username.isNotEmpty() && username.length > 2 && username.length < 10) {
            return true
        }
        showError.value = "Username must be within 3 to 8 characters"
        return false
    }
}

