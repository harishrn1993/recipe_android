package com.harish.recipeandroid.ui

import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment {
    constructor(layoutId: Int) : super(layoutId)
    constructor() : super()

    fun showLoadingDialog() {
        //TODO implementation remaining
    }

    fun hideLoadingDialog() {
        //TODO implementation remaining
    }

    fun showErrDialog(err: String) {
        Toast.makeText(requireContext(), err, Toast.LENGTH_SHORT).show()
    }

    fun hideErrDialog() {}
}