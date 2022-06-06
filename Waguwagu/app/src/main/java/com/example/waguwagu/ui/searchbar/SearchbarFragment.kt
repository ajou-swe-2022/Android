package com.example.waguwagu.ui.searchbar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.waguwagu.MainActivity
import com.example.waguwagu.OrderlistAdapter
import com.example.waguwagu.SearchAdapter
import com.example.waguwagu.databinding.FragmentSearchbarBinding
import com.example.waguwagu.model.data.SearchData


class SearchbarFragment : Fragment() {

    lateinit var binding : FragmentSearchbarBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = FragmentSearchbarBinding.inflate(inflater, container, false)

        var searchkey = arguments?.getString("query")


        if(!searchkey.isNullOrBlank()) {
            //Log.d("wy","Succeed2 : $searchkey")
                if(searchkey.contains("category:")) (activity as MainActivity).RestDataTag(searchkey.substring(9),this@SearchbarFragment,3)

                else (activity as MainActivity).RestDataName(searchkey,this@SearchbarFragment,3)
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    fun recyledata(datas:List<SearchData>?) {


        if (datas==null) {
            binding.resultText.setVisibility(View.VISIBLE)
            binding.resultText.text = "일치하는 음식점이 없습니다."
        } else {
            binding.resultText.setVisibility(View.GONE)
            binding.searchRecyclerview.adapter = datas?.let { SearchAdapter(it) }

        }

    }

}