package com.example.waguwagu.model.data

import android.view.Menu

data class RestData(
    val address: String,
    val addressDetail: String,
    val arriveTimeoutMinutes: Int,
    val category:  String,
    val description: String,
    val email: String,
    val id: String,
    val latitude: Double,
    val longitude: Double,
    val menu: List<MenuData> ,
   val name: String,
    val tables:List<TableData> ,
   val tel: String

)
