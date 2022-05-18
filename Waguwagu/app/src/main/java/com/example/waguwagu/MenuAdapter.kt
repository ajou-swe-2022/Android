package com.example.waguwagu

import com.example.waguwagu.model.data.MenuData
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.waguwagu.databinding.MenuListItemBinding
import com.example.waguwagu.ui.reserve.ReservemenuFragment

class MenuAdapter(val data: List<MenuData>, var link: ReservemenuFragment.getMenuSelected) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>(){
    lateinit var binding : MenuListItemBinding

    inner class MenuViewHolder(private val binding : MenuListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(menuData : MenuData){
            binding.menuImg.setImageResource(R.drawable.menu_example)
            binding.menuName.text = menuData.menuname
            binding.menuPrice.text = menuData.menuprice.toString() + "원"
            binding.selectedNum.text = "0"
        }

        fun getNumViewPosition() : TextView {
            return binding.selectedNum
        }
        fun getPriceViewPosition() : TextView {
            return binding.menuPrice
        }
    }

    //상속받으면 자동 생성
    //ViewHolder에 쓰일 Layout을 inflate하는 함수
    //ViewGroup의 context를 사용하여 특정 화면에서 구현할 수 있도록 함
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.MenuViewHolder {
        binding = MenuListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    //상속받으면 자동 생성
    override fun getItemCount(): Int = data.size

    //상속받으면 자동 생성
    //ViewHolder에서 데이터 묶는 함수가 실행되는 곳
    override fun onBindViewHolder(holder: MenuAdapter.MenuViewHolder, position: Int) {
        holder.bind(data[position])

        binding.minusMenu.setOnClickListener {
            var num = holder.getNumViewPosition().text.toString().toInt()
            var temp = holder.getPriceViewPosition().text.length
            var price = holder.getPriceViewPosition().text.subSequence(0, temp-1).toString().toInt()

            if (num > 0) {
                num--
                holder.getNumViewPosition().text = num.toString()
                link.getMenuSelect(price * -1, data[position], num)
            }

        }


        binding.plusMenu.setOnClickListener {
            var num = holder.getNumViewPosition().text.toString().toInt()
            var temp = holder.getPriceViewPosition().text.length
            var price = holder.getPriceViewPosition().text.subSequence(0, temp-1).toString().toInt()

            num++
            holder.getNumViewPosition().text = num.toString()
            link.getMenuSelect(price, data[position], num)
        }
    }
}