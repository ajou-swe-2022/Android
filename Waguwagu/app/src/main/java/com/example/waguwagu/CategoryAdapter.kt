package com.example.waguwagu

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.waguwagu.databinding.CategoryButtonBinding
import com.example.waguwagu.databinding.OrderListItemBinding
import androidx.fragment.app.FragmentManager

class CategoryAdapter(val data:List<String>) : RecyclerView.Adapter<CategoryHolder>() {
    private val mainActivity = MainActivity.getInstance()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val binding = CategoryButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(binding)
    }
    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        return holder.setData(data[position])
    }
    override fun getItemCount(): Int = data.size


}
class CategoryHolder(private val binding : CategoryButtonBinding) : RecyclerView.ViewHolder(binding.root){
    private val mainActivity = MainActivity.getInstance()
    var mMember:String? = null

    init {
        binding.categoryName.setOnClickListener {
            mainActivity?.text()
            //mainActivity?.setCategory(binding.categoryName.text.toString())
        }
    }
    fun setData(string: String){
        binding.categoryName.text = string
    }
}

