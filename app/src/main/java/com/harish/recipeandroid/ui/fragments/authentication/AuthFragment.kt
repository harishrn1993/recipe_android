package com.harish.recipeandroid.ui.fragments.authentication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.harish.recipeandroid.R
import com.harish.recipeandroid.databinding.AuthLayoutBinding
import com.harish.recipeandroid.ui.BaseFragment
import com.harish.recipeandroid.viewmodel.AuthViewModel

class AuthFragment : BaseFragment(R.layout.auth_layout) {

    private lateinit var binding: AuthLayoutBinding
    private var state: STATE = STATE.REGISTER
    private lateinit var viewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AuthLayoutBinding.bind(view)
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        initUi()
        registerToLiveData()
    }

    private fun registerToLiveData() {
        viewModel.isLoading.observe(viewLifecycleOwner, {
            if (it) {
                showLoadingDialog()
            } else {
                hideLoadingDialog()
            }
        })

        viewModel.showError.observe(viewLifecycleOwner, {
            if (it != null) {
                showErrDialog(it)
            } else {
                hideErrDialog()
            }
        })

        viewModel.navigateTo.observe(viewLifecycleOwner, {
            if (it != null) {
                view?.findNavController()?.navigate(it)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        updateEditTextValsFromViewModel()
    }

    private fun updateEditTextValsFromViewModel() {
        binding.emailEditText.setText(viewModel.email)
        binding.usernameEditText.setText(viewModel.username)
    }

    override fun onPause() {
        super.onPause()
        updateViewModelWithEditTextVals()
    }

    private fun updateViewModelWithEditTextVals() {
        viewModel.email = binding.emailEditText.text.toString()
        viewModel.username = binding.usernameEditText.text.toString()
    }

    private fun initUi() {
        binding.registerButton.setOnClickListener {
//            animateToRegistrationState()
            viewModel.viewState.value = STATE.REGISTER
        }

        binding.loginButton.setOnClickListener {
//            animateToLoginState()
            viewModel.viewState.value = STATE.LOGIN
        }

        binding.forgotPassword.setOnClickListener {
//            animateToForgotPasswordState()
            viewModel.viewState.value = STATE.FORGOT_PASSWORD
        }

        viewModel.viewState.observe(viewLifecycleOwner, {
            when (it) {
                STATE.REGISTER -> animateToRegistrationState()
                STATE.LOGIN -> animateToLoginState()
                STATE.FORGOT_PASSWORD -> animateToForgotPasswordState()
                null -> {
                }
            }
        })
        binding.skipButton.setOnClickListener {
            viewModel.skipClicked()
        }

        binding.submitButton.setOnClickListener {
            Log.d("State", state.toString())
            when (viewModel.viewState.value) {
                STATE.REGISTER -> {
                    viewModel.signUp(
                        binding.emailEditText.text.toString(),
                        binding.usernameEditText.text.toString(),
                        binding.passwordEditText.text.toString(),
                        binding.confirmPasswordEditText.text.toString()
                    )
                }
                STATE.LOGIN -> {
                    viewModel.signIn(
                        binding.emailEditText.text.toString(),
                        binding.passwordEditText.text.toString()
                    )
                }
                STATE.FORGOT_PASSWORD -> {
                    viewModel.forgotPassword(binding.emailEditText.text.toString())
                }
            }
        }
    }

    private fun animateToForgotPasswordState() {
//        state = STATE.FORGOT_PASSWORD
        binding.emailTextInputLayout.visibility = View.VISIBLE
        binding.usernameTextInputLayout.visibility = View.GONE
        binding.passwordTextInputLayout.visibility = View.GONE
        binding.confirmPasswordTextInputLayout.visibility = View.GONE

        binding.toggleButtonGroup.isSelectionRequired = false
        binding.toggleButtonGroup.clearChecked()
        binding.submitButton.text = getString(R.string.forgot_password)

    }

    private fun animateToLoginState() {

        binding.toggleButtonGroup.isSelectionRequired = true
//        state = STATE.LOGIN
        binding.emailTextInputLayout.visibility = View.VISIBLE
        binding.usernameTextInputLayout.visibility = View.GONE
        binding.passwordTextInputLayout.visibility = View.VISIBLE
        binding.confirmPasswordTextInputLayout.visibility = View.GONE

        binding.submitButton.text = getString(R.string.login)
    }

    private fun animateToRegistrationState() {
//        state = STATE.REGISTER
        binding.toggleButtonGroup.isSelectionRequired = true

        binding.emailTextInputLayout.visibility = View.VISIBLE
        binding.usernameTextInputLayout.visibility = View.VISIBLE
        binding.passwordTextInputLayout.visibility = View.VISIBLE
        binding.confirmPasswordTextInputLayout.visibility = View.VISIBLE

        binding.submitButton.text = getString(R.string.register)
    }

    enum class STATE { REGISTER, LOGIN, FORGOT_PASSWORD }
}