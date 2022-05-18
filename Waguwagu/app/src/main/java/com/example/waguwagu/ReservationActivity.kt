package com.example.waguwagu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.waguwagu.ui.reserve.ReservetableFragment

class ReservationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)

        val name = intent.getStringExtra("restname")
        val time = intent.getStringExtra("reservetime")
        val restname = findViewById<TextView>(R.id.rest_name)
        restname.text = name

        val query = arrayOf<String>(name!!, time!!)

        val reservetableFragment = ReservetableFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fl_container, reservetableFragment).commit()
        setDataAtFragment(reservetableFragment, query)
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

    fun setDataAtFragment(fragment: Fragment, strings: Array<String>?){
        val bundle = Bundle()
        bundle.putStringArray("query", strings)

        fragment.arguments = bundle
    }
}