package com.example.waguwagu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.waguwagu.model.data.MenuData
import com.example.waguwagu.model.data.MenusData
import com.example.waguwagu.ui.reserve.ReservemenuFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ReservenotiActivity : AppCompatActivity() {

    val BASE_URL_API = "https://diunbu3dmy.ap-northeast-1.awsapprunner.com:443/api/v1/"
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api = retrofit.create(menuinterface::class.java)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservenoti)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)

        val table_id = intent.getStringExtra("table_name")
        val table_name = findViewById<TextView>(R.id.table_name)
        table_name.text = table_id
        val id = intent.getIntExtra("table_id", 0)

        val resID = intent.getStringExtra("resID")
        val call = api.getMenu(resID!!)

        val reservemenuFragment = ReservemenuFragment()
        var menus : List<MenuData>? = null
        call.enqueue(object : Callback<MenusData> {
            override fun onResponse(call: Call<MenusData>, response: Response<MenusData>) {
                menus = response.body()?.items
                reservemenuFragment.menurecycle(menus!!)
                reservemenuFragment.getResID(menus!![0].resID)
                reservemenuFragment.table_id = id!!
                Log.d("wy","Succeed : $menus")
            }

            override fun onFailure(call: Call<MenusData>, t: Throwable) {
                Log.e("error", "error :${t.message}")
            }

        })
        supportFragmentManager.beginTransaction().replace(R.id.fl_container, reservemenuFragment).commit()
        setDataAtFragment(reservemenuFragment, intent.getIntExtra("minimum_price", 0))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setDataAtFragment(fragment: Fragment, price: Int){
        val bundle = Bundle()
        bundle.putInt("query", price)
        fragment.arguments = bundle
    }

}