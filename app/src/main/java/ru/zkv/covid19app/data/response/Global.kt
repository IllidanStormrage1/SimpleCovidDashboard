package ru.zkv.covid19app.data.response

import com.google.gson.annotations.SerializedName

class Global(
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
    val totalRecovered: Int
)