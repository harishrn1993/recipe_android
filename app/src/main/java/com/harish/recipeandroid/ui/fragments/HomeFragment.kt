package com.harish.recipeandroid.ui.fragments

import android.os.Bundle
import android.view.View
import com.harish.recipeandroid.R
import com.harish.recipeandroid.databinding.HomeLayoutBinding
import com.harish.recipeandroid.ui.BaseFragment

class HomeFragment : BaseFragment(R.layout.home_layout) {
    private lateinit var binding: HomeLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HomeLayoutBinding.bind(view)
//        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
//
//        initUi()
//        registerToLiveData()
    }
}