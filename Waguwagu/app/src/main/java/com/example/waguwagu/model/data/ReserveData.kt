package com.example.waguwagu.model.data

import com.google.gson.annotations.SerializedName

data class ReserveData(
    @SerializedName("orders")
    val orders: List<orders>,

    @SerializedName("restaurantID")
    val restaurantID : String,

    @SerializedName("userID")
    val userID : Int
)

data class orders(
    @SerializedName("menuID")
    val menuID : Int,

    @SerializedName("quantity")
    val quantity : Int
)