package com.example.waguwagu.model.data

import com.google.gson.annotations.SerializedName
data class MenusData(val menus:List<MenuData>)

data class MenuData(
    @SerializedName("id")
    val menuid : Int,

    @SerializedName("name")
    val menuname : String,

    @SerializedName("price")
    val menuprice : Int,

    @SerializedName("restaurantID")
    val resID : Int
    )

/*
data class MenuData(
    val menuid : String,
    val menuname : String,
    val menuprice : Int)


data class RestaurantsData(
    val restaurants:List<SearchData>

)
data class SearchData(
    val address:String,
    val addressDetail:String,
    @SerializedName("category")
    val resttag:String,

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

 */