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
              when (binding.categoryName.text.toString()) {
                   "일식"  ->  (fragment.activity as MainActivity).setCategory("category:"+"JAPANESE")
                  "피자" ->(fragment.activity as MainActivity).setCategory("category:"+"PIZZA")
                  "중식" -> (fragment.activity as MainActivity).setCategory("category:"+"CHINESE")
                  "까페·디저트"->(fragment.activity as MainActivity).setCategory("category:"+"CAFE")
                  "양식" ->(fragment.activity as MainActivity).setCategory("category:"+"WESTERN")
                  "분식" ->(fragment.activity as MainActivity).setCategory("category:"+"SNACK")
                  "치킨"->(fragment.activity as MainActivity).setCategory("category:"+"CHICKEN")
                  "백반·죽·국수" ->(fragment.activity as MainActivity).setCategory("category:"+"NOODLE")
                  "야식" ->(fragment.activity as MainActivity).setCategory("category:"+"NIGHT")
                  "아시안" ->(fragment.activity as MainActivity).setCategory("category:"+"ASIAN")
                  "찜·탕·찌개"->(fragment.activity as MainActivity).setCategory("category:"+"SOUP")
                  "고기·구이"->(fragment.activity as MainActivity).setCategory("category:"+"MEAT")
                  "족발·보쌈"->(fragment.activity as MainActivity).setCategory("category:"+"LEG")
                  "패스트푸드" ->(fragment.activity as MainActivity).setCategory("category:"+"FAST")
              }
              //(fragment.activity as MainActivity).RestDataAll();
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

