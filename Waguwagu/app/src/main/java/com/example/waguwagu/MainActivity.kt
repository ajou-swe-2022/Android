package com.example.waguwagu

import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                    val searchbarFragment = SearchbarFragment()
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
}