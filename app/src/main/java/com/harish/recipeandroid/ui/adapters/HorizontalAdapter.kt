package com.harish.recipeandroid.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.harish.recipeandroid.R
import com.harish.recipeandroid.data.model.Recipe

class HorizontalAdapter(private val recipeList: List<Recipe>) :
    RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {

    override fun getItemId(position: Int): Long {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_small_recipe, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(recipeList[position])
//        holder.imageView.rootView.setOnClickListener()
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.item_recipe_imageview)
        var nameTextView: TextView = itemView.findViewById(R.id.item_recipe_name_textview)

        fun bindItem(recipe: Recipe) {
            nameTextView.text = recipe.title
        }
    }

}