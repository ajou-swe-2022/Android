package com.example.waguwagu

import android.app.SearchManager
import android.content.Context
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.waguwagu.ui.home.*
import com.example.waguwagu.ui.mymenu.*
import com.example.waguwagu.ui.orderlist.*
import com.example.waguwagu.ui.searchbar.*
import com.example.waguwagu.ui.searchmap.*


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    val searchbarFragment = SearchbarFragment()
    private lateinit var mySearchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))



        // 하단 탭이 눌렸을 때 화면을 전환하기 위해선 이벤트 처리하기 위해 BottomNavigationView 객체 생성
        var bnv_main = findViewById(R.id.bnv_main) as BottomNavigationView

        // OnNavigationItemSelectedListener를 통해 탭 아이템 선택 시 이벤트를 처리
        // navi_menu.xml 에서 설정했던 각 아이템들의 id를 통해 알맞은 프래그먼트로 변경하게 한다.
        bnv_main.run { setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    // 다른 프래그먼트 화면으로 이동하는 기능
                    val homeFragment = HomeFragment()
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
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_bar, menu)
/*
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        this.mySearchView = menu?.findItem(R.id.action_search)?.actionView as SearchView

        this.mySearchView.apply{
            this.queryHint = "배고파!!"
            this.setOnQueryTextListener(this@MainActivity)
        }

*/
            return true

        }


    override fun onQueryTextSubmit(p0: String?): Boolean {
        //setDataAtFragment(searchbarFragment, p0)
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }


    fun setDataAtFragment(fragment: Fragment, string: String?){
        val bundle = Bundle()
        bundle.putString("query", string)

        fragment.arguments = bundle
    }
}