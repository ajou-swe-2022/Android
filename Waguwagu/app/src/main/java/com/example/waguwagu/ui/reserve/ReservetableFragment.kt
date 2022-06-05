package com.example.waguwagu.ui.reserve

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.example.waguwagu.R
import com.example.waguwagu.TableAdapter
import com.example.waguwagu.databinding.FragmentReservetableBinding
import com.example.waguwagu.model.data.TableData


class ReservetableFragment : Fragment() {
    lateinit var binding : FragmentReservetableBinding
    lateinit var tableId : String
    lateinit var resID : String
    lateinit var descript : String
    var table_num : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReservetableBinding.inflate(inflater, container, false)
        val datas = mutableListOf<TableData>()
        var string_name_time = arguments?.getStringArray("query")
/*
        datas.apply {
            add(TableData("@drawable/seat_example.png", "4인", true, "1", 12500))
            add(TableData("@drawable/seat_example.png", "1인", false, "2", 6000))
            add(TableData("@drawable/seat_example.png", "4인", true, "3", 8000))
        }

 */
        binding.descript.text = descript
        binding.seatImg.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.naver.com"))
            startActivity(intent)
        }
        binding.reserveNum.text = table_num.toString();

        if(!string_name_time.isNullOrEmpty()) {
            binding.restNameWrap.text = string_name_time[0]
            binding.reserveTime.text = string_name_time[1] + "분 이내 방문"
        }

        return binding.root
    }

    fun tablerecycle(datas : List<TableData>){
        binding.tableRecyclerview.adapter = TableAdapter(datas, resID)
    }
}