package com.example.waguwagu

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.waguwagu.databinding.ReserveTableBinding
import com.example.waguwagu.model.data.TableData
import com.example.waguwagu.ui.reserve.ReservetableFragment

class TableAdapter (val data:List<TableData>) : RecyclerView.Adapter<TableAdapter.TableViewHolder>() {


    lateinit var binding : ReserveTableBinding
    inner class TableViewHolder(private val binding : ReserveTableBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("ResourceAsColor")
        fun bind(tableData: TableData){
                binding.seatNum.text = tableData.table_num

                if(tableData.table_access){
                    binding.button.text = "예약"
                    binding.button.setTextColor(Color.BLACK)
                    binding.button.setBackgroundResource(R.drawable.component_round)
                }
                else{
                    binding.button.text = "예약불가"
                    binding.button.setTextColor(Color.GRAY)
                    binding.button.setBackgroundResource(R.drawable.component_round_false)
                }

                binding.imageView.setImageResource(R.drawable.seat_example)
            }
    }

    //상속받으면 자동 생성
    //ViewHolder에 쓰일 Layout을 inflate하는 함수
    //ViewGroup의 context를 사용하여 특정 화면에서 구현할 수 있도록 함
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        binding = ReserveTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TableViewHolder(binding)
    }

    //상속받으면 자동 생성
    override fun getItemCount(): Int = data.size

    //상속받으면 자동 생성
    //ViewHolder에서 데이터 묶는 함수가 실행되는 곳
    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.bind(data[position])

        binding.button.setOnClickListener {
            if(data[position].table_access) {
                val intent = Intent(holder.itemView?.context, ReservenotiActivity::class.java)
                intent.putExtra("table_id", data[position].table_id)
                ContextCompat.startActivity(holder.itemView.context, intent, null)
            }
        }
    }
}
