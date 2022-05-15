package com.example.waguwagu.ui.mymenu

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import com.example.waguwagu.MainActivity
import com.example.waguwagu.R
import com.example.waguwagu.databinding.FragmentMymenuBinding
import com.example.waguwagu.databinding.FragmentSearchmapBinding
import com.example.waguwagu.model.data.UserData


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MymenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MymenuFragment : Fragment() {
    // TODO: Rename and change types of parameters


    lateinit var mContext: Context
    lateinit var binding: FragmentMymenuBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentMymenuBinding.inflate(inflater,container,false)
        var tempuser=(activity as MainActivity).userdata
        binding.edText1.setText(tempuser?.username)
        binding.edText2.setText(tempuser?.userId)
        binding.edText3.setText(tempuser?.userPW)
        binding.edText4.setText(tempuser?.userphone)
        binding.edText5.setText(tempuser?.userEmail)
        binding.accept.setOnClickListener {

            (activity as MainActivity).userdata=UserData(binding.edText1.text.toString(),binding.edText2.text.toString(),binding.edText3.text.toString(),binding.edText4.text.toString(),binding.edText5.text.toString())

        }

        // Inflate the layout for this fragment
        return binding.root
    }


}