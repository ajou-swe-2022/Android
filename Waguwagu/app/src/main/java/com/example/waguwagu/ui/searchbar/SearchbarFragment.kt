package com.example.waguwagu.ui.searchbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.waguwagu.SearchAdapter
import com.example.waguwagu.databinding.FragmentSearchbarBinding
import com.example.waguwagu.model.data.SearchData


class SearchbarFragment : Fragment() {

    lateinit var binding : FragmentSearchbarBinding
    val searched_data = mutableListOf<SearchData>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val binding = FragmentSearchbarBinding.inflate(inflater, container, false)
        val datas = mutableListOf<SearchData>()
        val searchkey = arguments?.getString("query")

        datas.apply {
            add(SearchData("맥도날드", "햄버거", 3, 12, 30))
            add(SearchData("롯데리아", "햄버거", 3, 12, 30))
            add(SearchData("본죽", "한식", 3, 12, 30))
            add(SearchData("푸라닭", "한식", 3, 12, 30))
            add(SearchData("팔달관매점", "호텔", 3, 12, 30))
        }

        for(data in datas){
            if(searchkey == data.restname){
                searched_data.apply { add(data) }
            }
            else if(searchkey == data.resttag){
                searched_data.apply { add(data) }
            }
        }

        if(!searchkey.isNullOrBlank()) {
            if (!searched_data.isEmpty()) {
                binding.searchRecyclerview.adapter = SearchAdapter(searched_data)
                binding.resultText.setVisibility(View.GONE)
            } else {
                binding.resultText.text = "일치하는 음식점이 없습니다."
            }
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



}