package com.example.waguwagu.ui.orderlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waguwagu.OrderlistAdapter
import com.example.waguwagu.R
import com.example.waguwagu.databinding.FragmentOrderlistBinding
import com.example.waguwagu.model.data.SearchData


class OrderlistFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var binding = FragmentOrderlistBinding.inflate(inflater, container, false)
        val datas = mutableListOf<SearchData>()
        val dates = mutableListOf<String>()
/*
        datas.apply {
            add(SearchData("McDonalds", "Hamburger", 3, 12, 30))
            add(SearchData("롯데리아", "Hamburger", 3, 12, 30))
            add(SearchData("본죽", "한식", 3, 12, 30))
            add(SearchData("푸라닭", "한식", 3, 12, 30))
            add(SearchData("팔달관매점", "호텔", 3, 12, 30))
        }

        dates.apply {
            add("2022/01/12")
            add("2022/02/23")
            add("2022/02/28")
            add("2022/03/03")
            add("2022/05/09")
        }


        binding.orderRecyclerview.adapter = OrderlistAdapter(datas, dates)
*/
        return binding.root
    }
}