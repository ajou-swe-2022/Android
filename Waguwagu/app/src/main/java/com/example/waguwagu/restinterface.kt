package com.example.waguwagu;

import com.example.waguwagu.model.data.RestaurantsData
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.example.waguwagu.model.data.SearchData;
import retrofit2.http.Header

interface resinterface {


    @GET("restaurants")
    fun getRes(
        @Query("type") type:String,
        @Query("latitude") latitude:Double,
        @Query("longitude") longitude:Double,
        @Query("name") name:String
    ) :Call<RestaurantsData>





}
