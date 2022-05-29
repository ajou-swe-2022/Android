package com.example.waguwagu

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.waguwagu.model.data.RestaurantsData
import com.example.waguwagu.model.data.UserData
import com.example.waguwagu.model.data.reservecheckData
import com.example.waguwagu.ui.home.*
import com.example.waguwagu.ui.mymenu.*
import com.example.waguwagu.ui.orderlist.*
import com.example.waguwagu.ui.reserve.ReservemenuFragment
import com.example.waguwagu.ui.searchbar.*
import com.example.waguwagu.ui.searchmap.*
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.concurrent.timer


class MainActivity : AppCompatActivity()  {


    val BASE_URL_API = "https://diunbu3dmy.ap-northeast-1.awsapprunner.com:443/api/v1/"
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api = retrofit.create(resinterface::class.java)
    companion object {
        var context_main: Context?=null;
    }
    val rescheckapi=retrofit.create(reservecheckInterface::class.java)

    var checktime : Timer?=null
    var checkans : Timer?=null
    val searchbarFragment = SearchbarFragment()
    val searchMapFragment=SearchmapFragment()

    val homeFragment = HomeFragment()
    var userdata: UserData?=null
    var  searchview:SearchView?=null
    override fun onCreate(savedInstanceState: Bundle?) {






        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchview = findViewById(R.id.search_view)
        var mToolbar = findViewById(R.id.toolBar) as Toolbar
        mToolbar.setVisibility(View.GONE)

        //임시 유저 데이터
        userdata=UserData("뿡빵이","BungBang0123","wakwak0101","010-1010-1010","wakwak0101@gmail.com")


        // 하단 탭이 눌렸을 때 화면을 전환하기 위해선 이벤트 처리하기 위해 BottomNavigationView 객체 생성
        var bnv_main = findViewById(R.id.bnv_main) as BottomNavigationView

        // OnNavigationItemSelectedListener를 통해 탭 아이템 선택 시 이벤트를 처리
        // navi_menu.xml 에서 설정했던 각 아이템들의 id를 통해 알맞은 프래그먼트로 변경하게 한다.
        bnv_main.run { setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    // 다른 프래그먼트 화면으로 이동하는 기능
                    supportFragmentManager.beginTransaction().replace(R.id.fl_container, homeFragment).commit()
                }
                R.id.searchmap -> {
                    val searchmapFragment = SearchmapFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fl_container, searchmapFragment).commit()
                }
                R.id.searchbar -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fl_container, searchbarFragment).commit()
                }
                R.id.orderlist -> {
                    val orderlistFragment = OrderlistFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fl_container, orderlistFragment).commit()
                }
                R.id.mymenu -> {
                    val mymenuFragment = MymenuFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fl_container, mymenuFragment).commit()
                }
            }
            true
        }

            selectedItemId = R.id.home

            searchview?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    if(R.id.searchmap==selectedItemId) {
                        setDataAtFragment(searchMapFragment, query)
                        supportFragmentManager.beginTransaction().replace(R.id.fl_container, searchMapFragment).commit()


                    }
                    else {
                        setDataAtFragment(searchbarFragment, query)

                        if (selectedItemId != R.id.searchbar) {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.fl_container, searchbarFragment).commit()
                            selectedItemId = R.id.searchbar

                        } else {
                            supportFragmentManager.beginTransaction().detach(searchbarFragment)
                                .commitNowAllowingStateLoss()
                            supportFragmentManager.beginTransaction().attach(searchbarFragment)
                                .commitAllowingStateLoss()
                        }
                    }

                    searchview?.clearFocus()
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })

        }
        context_main=this;


    }

    fun setDataAtFragment(fragment: Fragment, string: String?){
        val bundle = Bundle()
        bundle.putString("query", string)

        fragment.arguments = bundle
    }
    fun setCategory(string: String?){
        searchview?.setQuery(string,true)
        //Log.d("chegking","why not fun")
        //Toast.makeText(this@MainActivity, "토스트 메세지 띄우기 입니다.", Toast.LENGTH_SHORT).show()


    }

    fun RestDataAll(fragment: Fragment,id:Int)  {
        SendAPI(api.getRes("ALL",.1,.1,""),fragment,id)

    }
    fun RestDataLoc(latitude: Double,longitude:Double,fragment: Fragment,id:Int)  {
        SendAPI(api.getRes("LOCATION",latitude,longitude,""),fragment,id)

    }
    fun RestDataName(Name:String,fragment: Fragment,id:Int) {
        return SendAPI(api.getRes("NAME",.1,.1,Name),fragment,id)
    }
    fun SendAPI(sendUrl:Call<RestaurantsData>,fragment: Fragment,id:Int ){

        sendUrl.enqueue(object : Callback<RestaurantsData> {
            override fun onResponse(call: Call<RestaurantsData>, response: Response<RestaurantsData>) {

                var senddata=response.body()?.restaurants
                if(id==1) {
                    (fragment as HomeFragment).recycledata(senddata)
                }
                else if(id==2) {
                    (fragment as OrderlistFragment).recyledata(senddata)
                }
                else if(id==3) {
                    (fragment as SearchbarFragment).recyledata(senddata)
                }
                else if(id==4) {
                    (fragment as SearchmapFragment).recyledata(senddata)
                }
                Log.d("wy","Succeed : $senddata")
            }
            override fun onFailure(call: Call<RestaurantsData>, t: Throwable) {
                Log.d("ch","Failed : $t")
            }
        })


    }
    fun showReservation(restId:String,userId:Int) {
        var mToolbar = findViewById(R.id.toolBar) as Toolbar
        var timebar=findViewById(R.id.result_text) as TextView
       mToolbar.setVisibility(View.VISIBLE)

        timebar.text="${restId} 예약 요청 확인 중 잠시만 기다려주세요"

        checkans=timer(period = 10000) {

            sendReservecheck(userId,restId)
        }
    }
    fun sendReservecheck(userId:Int,restId:String) {
        rescheckapi.getReserv(userId).enqueue(object : Callback<reservecheckData> {
            override fun onResponse(call: Call<reservecheckData>, response: Response<reservecheckData>) {

                var senddata=response.body()?.reservations
                if (senddata != null) {
                    for(x in senddata) {
                        if(x.restaurantID==restId&&x.status=="approved") {
                            checkans?.cancel()
                            checktime(restId)
                        }

                    }
                }
                Log.d("wy","Succeed : $senddata")
            }
            override fun onFailure(call: Call<reservecheckData>, t: Throwable) {
                Log.d("ch","Failed : $t")
            }
        })

    }
    fun checktime(restId: String) {
        var x=31
        var timebar=findViewById(R.id.result_text) as TextView
        checktime=timer(period = 60000) {
            x--// timer() 호출
            runOnUiThread {
                if(x==0) {
                    timebar.text="예약 방문 시간이 만료되었습니다"

                    checktime?.cancel()
                }
                else timebar.text="${restId} 예약 방문 시간 ${x}분 남았습니다"

            }
        }

    }





}