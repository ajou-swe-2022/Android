package com.example.waguwagu.model.data

import com.google.gson.annotations.SerializedName

data class TableDatas(val tables: List<TableData>)

data class TableData(
    val table_img : String?,

    @SerializedName("id")
    val table_id: Int,

    @SerializedName("restaurantID")
    val resID: String,

    @SerializedName("name")
    val table_name: String,

    @SerializedName("maxCustomerCount")
    val seat_num: Int,

    @SerializedName("minOrderAmount")
    val minimum_price: Int,

    @SerializedName("enabled")
    val table_access: Boolean
    )
