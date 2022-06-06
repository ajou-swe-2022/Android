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
              val context=binding.categoryName.context
              when (binding.categoryName.text.toString()) {
                   "일식"  ->  (context as MainActivity).setCategory("category:"+"JAPANESE")
                  "피자" ->(context as MainActivity).setCategory("category:"+"PIZZA")
                  "중식" -> (context as MainActivity).setCategory("category:"+"CHINESE")
                  "카페·디저트"->(context as MainActivity).setCategory("category:"+"CAFE")
                  "양식" ->(context as MainActivity).setCategory("category:"+"WESTERN")
                  "분식" ->(context as MainActivity).setCategory("category:"+"SNACK")
                  "치킨"->(context as MainActivity).setCategory("category:"+"CHICKEN")
                  "백반·죽·국수" ->(context as MainActivity).setCategory("category:"+"NOODLE")
                  "야식" ->(context as MainActivity).setCategory("category:"+"NIGHT")
                  "아시안" ->(context as MainActivity).setCategory("category:"+"ASIAN")
                  "찜·탕·찌개"->(context as MainActivity).setCategory("category:"+"SOUP")
                  "고기·구이"->(context as MainActivity).setCategory("category:"+"MEAT")
                  "족발·보쌈"->(context as MainActivity).setCategory("category:"+"LEG")
                  "패스트푸드" ->(context as MainActivity).setCategory("category:"+"FAST")
              }

          }
          }
        fun setData(string: String){
            binding.categoryName.text = string
            val context=binding.categoryName.context
            var imgstring="";
            when (string) {
                "일식"  ->  binding.categoryName.background=context.getDrawable(R.drawable.japanese);
                "피자" ->  binding.categoryName.background=context.getDrawable(R.drawable.pizza);
                "중식" ->  binding.categoryName.background=context.getDrawable(R.drawable.chinese);
                "카페·디저트"-> binding.categoryName.background=context.getDrawable(R.drawable.cafe);
                "양식" -> binding.categoryName.background=context.getDrawable(R.drawable.western);
                "분식" -> binding.categoryName.background=context.getDrawable(R.drawable.snack);
                "치킨"-> binding.categoryName.background=context.getDrawable(R.drawable.chicken);
                "백반·죽·국수" -> binding.categoryName.background=context.getDrawable(R.drawable.noodle);
                "야식" -> binding.categoryName.background=context.getDrawable(R.drawable.night);
                "아시안" -> binding.categoryName.background=context.getDrawable(R.drawable.asian);
                "찜·탕·찌개"-> binding.categoryName.background=context.getDrawable(R.drawable.soup)
                "고기·구이"-> binding.categoryName.background=context.getDrawable(R.drawable.meat);
                "족발·보쌈"-> binding.categoryName.background=context.getDrawable(R.drawable.leg);
                "패스트푸드" -> binding.categoryName.background=context.getDrawable(R.drawable.fast);
            }


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

