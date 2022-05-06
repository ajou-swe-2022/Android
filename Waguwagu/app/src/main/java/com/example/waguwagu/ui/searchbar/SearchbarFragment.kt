package com.example.waguwagu.ui.searchbar

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.waguwagu.MainActivity
import com.example.waguwagu.SearchAdapter
import com.example.waguwagu.model.data.SearchData

class SearchbarFragment : Fragment() {

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val datas = mutableListOf<SearchData>()

        datas.apply {
            add(SearchData("맥도날드", "햄버거"))
            add(SearchData("롯데리아", "햄버거"))
            add(SearchData("본죽", "한식"))
        }

        val myAdapter = SearchAdapter(mainActivity, datas)

    }

}