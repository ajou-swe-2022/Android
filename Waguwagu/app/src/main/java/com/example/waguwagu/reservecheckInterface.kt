package com.example.waguwagu

import com.example.waguwagu.model.data.RestaurantsData
import com.example.waguwagu.model.data.reservecheckData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface reservecheckInterface {
    @GET("users/{id}/reservations")
    fun getReserv(
        @Path("id") UserID : String
    ) : Call<reservecheckData>
}