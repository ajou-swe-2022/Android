package com.example.waguwagu.model.data

import com.google.gson.annotations.SerializedName

data class reservecheckData(
   val reservations:List<reservation>

)

data class reservation(

    val createdTimeAt: Int,
    val id: Int,
    val orders:List<order>,
    val restaurantID: String,
    val status: String,
    val totalPrice: Int,
    val userID: Int

)

data class order(
    val menuID : Int,
    val menuName: String,
    val price: Int,
    val quantity : Int
)