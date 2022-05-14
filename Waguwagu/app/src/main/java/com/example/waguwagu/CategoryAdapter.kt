package com.example.waguwagu

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.waguwagu.databinding.CategoryButtonBinding
import com.example.waguwagu.databinding.OrderListItemBinding
import androidx.fragment.app.FragmentManager
import com.example.waguwagu.ui.home.*

class CategoryAdapter(val data:List<String>,val fragment: HomeFragment) : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {



    inner class CategoryHolder(private val binding : CategoryButtonBinding) : RecyclerView.ViewHolder(binding.root){

        init {
          binding.categoryName.setOnClickListener {
                       fragment.delivercatgory(binding.categoryName.text.toString())

          }



          }



        fun setData(string: String){
            binding.categoryName.text = string


        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {

        val binding = CategoryButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(binding)
    }
    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        return holder.setData(data[position])


    }
    override fun getItemCount(): Int = data.size


}

