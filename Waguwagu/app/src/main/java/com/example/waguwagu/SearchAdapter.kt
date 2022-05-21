package com.example.waguwagu

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.waguwagu.databinding.SearchListItemBinding
import com.example.waguwagu.model.data.SearchData

class SearchAdapter (val data:List<SearchData>) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    inner class SearchViewHolder(private val binding : SearchListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(searchData: SearchData){
                binding.restName.text = searchData.restname
                binding.restTag.text = searchData.resttag
                binding.restAdmit.text = searchData.restadmit.toString()
                binding.restSeatNum.text = searchData.restseatnum.toString()
                binding.reserveTime.text = searchData.reservetime.toString()
            }
    }

    //상속받으면 자동 생성
    //ViewHolder에 쓰일 Layout을 inflate하는 함수
    //ViewGroup의 context를 사용하여 특정 화면에서 구현할 수 있도록 함
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = SearchListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    //상속받으면 자동 생성
    override fun getItemCount(): Int = data.size

    //상속받으면 자동 생성
    //ViewHolder에서 데이터 묶는 함수가 실행되는 곳
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(data[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView?.context, ReservationActivity::class.java)
            intent.putExtra("restname", data[position].restname)
           intent.putExtra("reservetime", data[position].reservetime.toString())
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }
}
