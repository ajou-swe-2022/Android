package com.example.waguwagu


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.waguwagu.databinding.SelectedMenusBinding
import com.example.waguwagu.model.data.MenuData

class SelectmenuAdapter(val datas: List<MenuData>, val numbers: List<String>) : RecyclerView.Adapter<SelectmenuAdapter.MenuViewHolder>() {

    lateinit var binding : SelectedMenusBinding

    inner class MenuViewHolder(private val binding : SelectedMenusBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(selectedData: MenuData?, number: String?){
            if(selectedData != null) {
                binding.menuImg.setImageResource(R.drawable.menu_example)
                binding.menuName.text = selectedData.menuname
                binding.menuPrice.text = selectedData.menuprice.toString() + "원"
                binding.selectedNum.text = number
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        binding = SelectedMenusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }


    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(datas[position], numbers[position])
    }
}