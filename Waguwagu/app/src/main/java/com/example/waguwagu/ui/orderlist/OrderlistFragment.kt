package com.example.waguwagu.ui.orderlist

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waguwagu.MainActivity
import com.example.waguwagu.OrderlistAdapter
import com.example.waguwagu.R
import com.example.waguwagu.databinding.FragmentOrderlistBinding
import com.example.waguwagu.databinding.FragmentSearchmapBinding
import com.example.waguwagu.model.data.SearchData


class OrderlistFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentOrderlistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOrderlistBinding.inflate(inflater, container, false)
        (activity as MainActivity).RestDataAll(this@OrderlistFragment,2)

        return binding.root
    }
    fun recyledata(datas:List<SearchData>?) {
        val data= mutableListOf<SearchData>()
        data.apply{
            add(datas?.get(0)!!)
            add(datas[1]!!)
        }
        val dates = mutableListOf<String>()

        dates.apply {
            add("2022/01/12")

            add("2022/02/23")
            /*
            add("2022/02/28")
            add("2022/03/03")
            add("2022/05/09")
             */
        }
        binding.orderRecyclerview.adapter = data?.let { OrderlistAdapter(it,dates) };

    }
}