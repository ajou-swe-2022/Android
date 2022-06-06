package com.example.waguwagu

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.waguwagu.databinding.OrderListItemBinding
import com.example.waguwagu.model.data.SearchData

class OrderlistAdapter (val data:List<SearchData>, val date_reserved : List<String>) : RecyclerView.Adapter<OrderlistAdapter.SearchViewHolder>() {

    inner class SearchViewHolder(private val binding : OrderListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(searchData: SearchData, date: String){
            binding.restName.text = searchData.restname
            binding.restTag.text = searchData.resttag
            binding.restAdmit.text = searchData.restadmit.toString()
            binding.restSeatNum.text = searchData.restseatnum.toString()
            binding.reserveTime.text = searchData.reservetime.toString()
            when(searchData.restname) {
                "맥도날드"-> binding.restImg.setImageResource(R.drawable.menu_example)
            }
            Glide.with(binding.restAdmit.context)
                .load(searchData.imgUrl)
                .placeholder(R.drawable.ic_mymenu) // 이미지 로딩 시작하기 전 표시할 이미지
                .error(R.drawable.ic_mymenu) // 로딩 에러 발생 시 표시할 이미지
                .fallback(R.drawable.ic_mymenu) // 로드할 url 이 비어있을(null 등) 경우 표시할 이미지
                .into(binding.restImg) // 이미지를 넣을 뷰
            binding.date.text = date
        }
    }

    //상속받으면 자동 생성
    //ViewHolder에 쓰일 Layout을 inflate하는 함수
    //ViewGroup의 context를 사용하여 특정 화면에서 구현할 수 있도록 함
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = OrderListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    //상속받으면 자동 생성
    override fun getItemCount(): Int = data.size

    //상속받으면 자동 생성
    //ViewHolder에서 데이터 묶는 함수가 실행되는 곳
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(data[position], date_reserved[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView?.context, ReservationActivity::class.java)
            intent.putExtra("restname", data[position].restname)
            intent.putExtra("reservetime", data[position].reservetime.toString())
            intent.putExtra("restid", data[position].id)
            intent.putExtra("desc", data[position].descript)
            intent.putExtra("table_num", data[position].restadmit)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }
}