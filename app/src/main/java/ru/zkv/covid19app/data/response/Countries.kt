package ru.zkv.covid19app.data.response

import com.google.gson.annotations.SerializedName

data class Countries(
    @SerializedName("Country")
    val country: String,
    @SerializedName("CountryCode")
    val countryCode: String,
    @SerializedName("Slug")
    val slug: String,
    @SerializedName("NewConfirmed")
    val newConfirmed: Int,
    @SerializedName("TotalConfirmed")
    val totalConfirmed: Int,
    @SerializedName("NewDeaths")
    val newDeaths: Int,
    @SerializedName("TotalDeaths")
    val totalDeaths: Int,
    @SerializedName("NewRecovered")
    val newRecovered: Int,
    @SerializedName("TotalRecovered")
    val totalRecovered: Int,
    @SerializedName("Date")
    val date: String
) : ItemModel {
    override val type: ItemType = ItemType.LIST_ITEM
}