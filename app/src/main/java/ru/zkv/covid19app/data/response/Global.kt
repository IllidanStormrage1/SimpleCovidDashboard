package ru.zkv.covid19app.data.response

import com.google.gson.annotations.SerializedName

data class Global(
    @SerializedName("NewConfirmed")
    val newConfirmed: Int,
    @SerializedName("TotalConfirmed")
    val TotalConfirmed: Int,
    @SerializedName("NewDeaths")
    val newDeaths: Int,
    @SerializedName("TotalDeaths")
    val totalDeaths: Int,
    @SerializedName("NewRecovered")
    val newRecovered: Int,
    @SerializedName("TotalRecovered")
    val totalRecovered: Int
) : ItemModel {
    override val type: ItemType = ItemType.PINNED_ITEM
}