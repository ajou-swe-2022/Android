package com.example.waguwagu

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.waguwagu.model.data.MenusData

interface menuinterface {

    @GET("items")
    fun getMenu(
        @Query("id") id:Int,
        @Query("name") name:String,
        @Query("price") price:Int,
        @Query("restaurantID") resID:String
    ) : Call<MenusData>
}