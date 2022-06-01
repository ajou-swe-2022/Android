package com.example.waguwagu.model.data

import com.google.gson.annotations.SerializedName

data class RestaurantsData(
    val restaurants:List<SearchData>

    )
data class SearchData(
    val address:String,
    val addressDetail:String,
    @SerializedName("category")
    val resttag:String,
    @SerializedName("description")
    val descript:String,

    @SerializedName("countEnabledTable")
    val restadmit:Int,
    @SerializedName("countTotalTable")
    val restseatnum:Int,
    val email:String,
    val id:String,
    @SerializedName("arriveTimeoutMinutes")
    val reservetime: Int,
    val latitude:Double,
    val longitude:Double,
    @SerializedName("name")
    val restname:String,
    val tel:String,

    )

/*
data class SignUpCheckErrorResponse(
    val code: Int,
    val message: String,
    val method: String,
    val url: String
) : SignUpCheckResponse

// SignUpCheckResponse.kt
interface SignUpCheckResponse {
}
*/
