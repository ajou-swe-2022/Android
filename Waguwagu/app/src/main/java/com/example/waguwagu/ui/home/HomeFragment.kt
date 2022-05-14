package com.example.waguwagu.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waguwagu.CategoryAdapter
import com.example.waguwagu.MainActivity
import com.example.waguwagu.R
import com.example.waguwagu.SearchAdapter
import com.example.waguwagu.databinding.FragmentHomeBinding
import com.example.waguwagu.model.data.SearchData

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment()  {
    lateinit var HContext: Context


    lateinit var binding:FragmentHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        val datas1 = mutableListOf<String>()
        val datas2 = mutableListOf<String>()
        val datas3 = mutableListOf<String>()
        val datas4 = mutableListOf<String>()
        val datas = mutableListOf<SearchData>()
        datas1.apply {
            add("일식")
            add("피자")
            add("중식")
            add("까페·디저트")

        }
        datas2.apply {
            add("양식")
            add("분식")
            add("치킨")
            add("백반·죽·국수")

        }
        datas3.apply {
            add("야식")
            add("아시안")
            add("찜·탕·찌개")
            add("고기·구이")

        }
        datas4.apply {
            add("족발·보쌈")
            add("패스트푸드")
        }
        datas.apply {
            add(SearchData("McDonalds", "Hamburger", 3, 12, 30))
            add(SearchData("롯데리아", "Hamburger", 3, 12, 30))
            add(SearchData("본죽", "한식", 3, 12, 30))
            add(SearchData("푸라닭", "한식", 3, 12, 30))
            add(SearchData("팔달관매점", "호텔", 3, 12, 30))
        }
        binding.categoryRecyclerview1.adapter=CategoryAdapter(datas1,this@HomeFragment)
        binding.categoryRecyclerview2.adapter=CategoryAdapter(datas2,this@HomeFragment)
        binding.categoryRecyclerview3.adapter=CategoryAdapter(datas3,this@HomeFragment)
        binding.categoryRecyclerview4.adapter=CategoryAdapter(datas4,this@HomeFragment)
        binding.searchRecyclerview.adapter=SearchAdapter(datas)
        return binding.root
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun delivercatgory(string:String) {
        return (activity as MainActivity).setCategory(string);
    }


}