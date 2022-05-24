package com.example.waguwagu

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.waguwagu.model.data.TableDatas

interface tableinterface {

    @GET("restaurants/{id}/tables")
    fun getTables(
        @Path("id") resID: String
    ) : Call<TableDatas>
}