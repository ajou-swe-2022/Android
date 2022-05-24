package com.example.waguwagu

import retrofit2.Call
import com.example.waguwagu.model.data.MenusData
import com.example.waguwagu.model.data.Postresult
import com.example.waguwagu.model.data.orders
import retrofit2.http.*

interface menuinterface {

    @GET("restaurant/{id}/menu")
    fun getMenu(
        @Path("id") restaurantID : String
    ) : Call<MenusData>

    @FormUrlEncoded
    @POST("reservations")
    fun postReserve(
        @Field("orders") orders : List<orders>,
        @Field("restaurantID") resID : String,
        @Field("userID") userID : Int
    ) : Call<Postresult>
}