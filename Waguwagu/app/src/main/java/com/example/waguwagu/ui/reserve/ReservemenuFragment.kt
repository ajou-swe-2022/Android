package com.example.waguwagu.ui.reserve

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.waguwagu.*
import com.example.waguwagu.databinding.FragmentReservemenuBinding
import com.example.waguwagu.model.data.MenuData
import com.example.waguwagu.model.data.Postresult
import com.example.waguwagu.model.data.ReserveData
import com.example.waguwagu.model.data.orders
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ReservemenuFragment : Fragment(), View.OnClickListener {

    val BASE_URL_API = "https://diunbu3dmy.ap-northeast-1.awsapprunner.com:443/api/v1/"
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api = retrofit.create(menuinterface::class.java)

    lateinit var binding: FragmentReservemenuBinding
    var num : Int = 0
    lateinit var selected_price : TextView
    lateinit var minimum_price_text : TextView
    var minimum_price : Int? = 0
    val selected_menus = mutableListOf<MenuData>()
    val numbers = mutableListOf<String>()
    val datas = mutableListOf<MenuData>()
    var dialog : View? = null
    lateinit var alertDialog : AlertDialog
    lateinit var inflater: LayoutInflater
    val link = getMenuSelected()
    val send_number = mutableListOf<Int>()
    var resID : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReservemenuBinding.inflate(inflater, container, false)
        this.inflater = inflater

        selected_price = binding.selectedPrice
        minimum_price_text = binding.necessary
        minimum_price = arguments?.getInt("query")



        binding.reserveSelect.setOnClickListener(this)
        binding.reserveNoti.setOnClickListener(this)

        if(minimum_price!! >= 1000){
            if((minimum_price!! % 1000) == 0){
                minimum_price_text.text = (minimum_price!! / 1000).toString() + ",000원"
            }
            else{
                minimum_price_text.text = (minimum_price!! / 1000).toString() + "," + (minimum_price!! % 1000) + "원"
            }
        }
        else{
            minimum_price_text.text = minimum_price.toString() + "원"
        }

        selected_price.setTextColor(Color.RED)
        selected_price.text = "0원"

        datas.apply {
            add(MenuData(1, "빅맥", 3500, "401-234"))
            add(MenuData(2, "불고기버거", 2000, "401-234"))
            add(MenuData(3, "슈비버거", 4000, "401-234"))
            add(MenuData(4, "상하이스파이스버거", 4500, "401-234"))
        }
        //binding.menuRecyclerview.adapter = MenuAdapter(datas, link)
        return binding.root
    }

    inner class getMenuSelected {
        fun getMenuSelect(price: Int, data: MenuData, number: Int) {
            num += price
            var index = -1

            if(selected_menus.isNotEmpty()) {
                if (selected_menus.contains(data)) {
                    index = selected_menus.indexOf(data)
                }
            }


            if(price < 0 && index != -1){ // minus menu
                if(number == 0){ // 줄어서 0개가 되면 리스트에서 삭제
                    selected_menus.removeAt(index)
                    numbers.removeAt(index)
                    send_number.removeAt(index)
                }
                else // 0개가 아니라면 그대로 갯수만 수정
                    numbers[index] = number.toString() + "개"
                    send_number[index] = number
            }
            else{ // plus menu
                if(index != -1){ // 리스트에 존재한다면 갯수만 수정
                    numbers[index] = number.toString() + "개"
                    send_number[index] = number
                }
                else{ // 존재하지 않는다면 리스트에 추가
                    selected_menus.add(data)
                    numbers.add("1개")
                    send_number.add(1)
                }
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.reserve_select -> {
                if (num < minimum_price!!) {
                    selected_price.setTextColor(Color.RED)
                } else {
                    selected_price.setTextColor(Color.GREEN)
                }

                if(num >= 1000) {
                    if(num % 1000 == 0) {
                        selected_price.text = (num / 1000).toString() + ",000원"
                    }
                    else{
                        selected_price.text = (num / 1000).toString() + "," + (num % 1000).toString() + "원"
                    }
                }
                else{
                    selected_price.text = num.toString() + "원"
                }
                binding.selectedMenus.adapter = SelectmenuAdapter(selected_menus, numbers)
            }

            R.id.reserve_noti -> {
                if(num < minimum_price!!){
                    Toast.makeText(getActivity(), "최소 금액을 만족하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
                else{
                    dialog = inflater.inflate(R.layout.popup_reserve, null)
                    showSettingPopup()
                }
            }
        }
    }

    fun showSettingPopup() {
        alertDialog = AlertDialog.Builder(activity).create()

        val btnOk = dialog!!.findViewById<android.widget.Button>(R.id.btn_ok)
        btnOk.setOnClickListener {
            val reserve_menus = mutableListOf<orders>()
            for(index in 0 until selected_menus.size){
                reserve_menus.add(orders(selected_menus[index].menuid, send_number[index]))
            }

            api.postReserve(reserve_menus, resID!!, 1).enqueue(object : Callback<Postresult>{
                override fun onFailure(call: Call<Postresult>, t: Throwable) {
                    Log.e("error", "error : $t")
                }

                override fun onResponse(call: Call<Postresult>, response: Response<Postresult>) {
                    Log.d("test", "Success : ${response.body().toString()}")
                    Toast.makeText(getActivity(), "예약이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                    alertDialog.dismiss()
                    requireActivity().finish()
                    (MainActivity.context_main as MainActivity).showReservation()
                }
            })
        }

        val btnNo = dialog!!.findViewById<android.widget.Button>(R.id.btn_no)
        btnNo.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.setView(dialog)
        alertDialog.setCanceledOnTouchOutside(true)
        alertDialog.show()
    }

    fun menurecycle(data: List<MenuData>){
        binding.menuRecyclerview.adapter = MenuAdapter(data, link)
    }

    fun getResID(resID : String){
        this.resID = resID
    }
}