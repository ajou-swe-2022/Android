package com.example.waguwagu.model.data

data class ReserveData(
    val orders: List<orders>,
    val restaurantID : String,
    val userID : Int
)

data class orders(
    val menuID : Int,
    val quentity : Int
)

data class Postresult(val result : String)