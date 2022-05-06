package com.example.waguwagu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.waguwagu.model.data.SearchData

class SearchAdapter (val context: Context, val data:List<SearchData>) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {


    inner class SearchViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val Fullname : TextView = itemView.findViewById(R.id.rest_name)
        private val Quiz : TextView = itemView.findViewById(R.id.rest_tag)

        fun bind(searchData: SearchData){
                Fullname.text = searchData.fullname
                Quiz.text = searchData.quiz
            }
    }

    //상속받으면 자동 생성
    //ViewHolder에 쓰일 Layout을 inflate하는 함수
    //ViewGroup의 context를 사용하여 특정 화면에서 구현할 수 있도록 함
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.search_list_item, parent, false)
        return SearchViewHolder(view)
    }

    //상속받으면 자동 생성
    override fun getItemCount(): Int = data.size

    //상속받으면 자동 생성
    //ViewHolder에서 데이터 묶는 함수가 실행되는 곳
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(data[position])
    }

}
