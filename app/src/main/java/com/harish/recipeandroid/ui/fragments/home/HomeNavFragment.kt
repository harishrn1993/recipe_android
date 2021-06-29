package com.harish.recipeandroid.ui.fragments.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harish.recipeandroid.R
import com.harish.recipeandroid.databinding.HomeNavLayoutBinding
import com.harish.recipeandroid.ui.BaseFragment
import com.harish.recipeandroid.ui.adapters.HorizontalAdapter
import com.harish.recipeandroid.viewmodel.HomeNavViewModel

class HomeNavFragment : BaseFragment(R.layout.home_nav_layout) {
    private lateinit var binding: HomeNavLayoutBinding
    private lateinit var viewModel: HomeNavViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HomeNavLayoutBinding.bind(view)
        viewModel = ViewModelProvider(this).get(HomeNavViewModel::class.java)

        initRecyclerViews()
        loadRecyclerViews()
    }

    private fun loadRecyclerViews() {
        viewModel.getAllRecipe(null)
    }

    private fun initRecyclerViews() {
        binding.breakFastRecyclerView.layoutManager =
            LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)

        binding.lunchRecyclerView.layoutManager =
            LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)

        binding.dinnerRecyclerView.layoutManager =
            LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)

        binding.snackRecyclerView.layoutManager =
            LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)


//        val recipe = ArrayList<Recipe>()
//        recipe.add(Recipe("","",1,"","","","",ArrayList(),"",1,ArrayList(),"Recipe1"))
//        recipe.add(Recipe("","",1,"","","","",ArrayList(),"",1,ArrayList(),"Recipe2"))
//        recipe.add(Recipe("","",1,"","","","",ArrayList(),"",1,ArrayList(),"Recipe3"))
//        recipe.add(Recipe("","",1,"","","","",ArrayList(),"",1,ArrayList(),"Recipe4"))
//        recipe.add(Recipe("","",1,"","","","",ArrayList(),"",1,ArrayList(),"Recipe5"))
//        recipe.add(Recipe("","",1,"","","","",ArrayList(),"",1,ArrayList(),"Recipe6"))
//        recipe.add(Recipe("","",1,"","","","",ArrayList(),"",1,ArrayList(),"Recipe7"))
//        recipe.add(Recipe("","",1,"","","","",ArrayList(),"",1,ArrayList(),"Recipe8"))
//        recipe.add(Recipe("","",1,"","","","",ArrayList(),"",1,ArrayList(),"Recipe9"))
//        recipe.add(Recipe("","",1,"","","","",ArrayList(),"",1,ArrayList(),"Recipe10"))
//        recipe.add(Recipe("","",1,"","","","",ArrayList(),"",1,ArrayList(),"Recipe11"))
//        recipe.add(Recipe("","",1,"","","","",ArrayList(),"",1,ArrayList(),"Recipe12"))
//        recipe.add(Recipe("","",1,"","","","",ArrayList(),"",1,ArrayList(),"Recipe13"))

        viewModel.breakfast.observe(viewLifecycleOwner, {
            binding.breakFastRecyclerView.adapter = HorizontalAdapter(it)
        })

        viewModel.lunch.observe(viewLifecycleOwner, {
            binding.breakFastRecyclerView.adapter = HorizontalAdapter(it)
        })

        viewModel.dinner.observe(viewLifecycleOwner, {
            binding.breakFastRecyclerView.adapter = HorizontalAdapter(it)
        })

        viewModel.snacks.observe(viewLifecycleOwner, {
            binding.breakFastRecyclerView.adapter = HorizontalAdapter(it)
        })
//
//
//        binding.dinnerRecyclerView.adapter = HorizontalAdapter(recipe)
//        binding.snackRecyclerView.adapter = HorizontalAdapter(recipe)
    }

}