package com.example.waguwagu

import retrofit2.Call
import com.example.waguwagu.model.data.MenusData
//import com.example.waguwagu.model.data.Postresult
import com.example.waguwagu.model.data.ReserveData
import com.example.waguwagu.model.data.orders
import okhttp3.MultipartBody
import retrofit2.http.*

interface menuinterface {

    @GET("restaurant/{id}/menu")
    fun getMenu(
        @Path("id") restaurantID : String
    ) : Call<MenusData>
/*
    @FormUrlEncoded
    @Headers("accept: application/json", "content-type: application/json")
    @POST("reservations")
    fun postReserve(
        @Field("orders") orders: List<orders>,
        @Field("restaurantID") resID : String,
        @Field("userID") userID : Int
    ) : Call<ReserveData>

 */

    //@FormUrlEncoded
    @POST("reservations")
    fun postReserve(
        @Body body : HashMap<String, Any>
    ) : Call<Void>
}