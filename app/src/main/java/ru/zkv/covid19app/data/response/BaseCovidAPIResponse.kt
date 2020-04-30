package ru.zkv.covid19app.data.response

import com.google.gson.annotations.SerializedName

data class BaseCovidAPIResponse(
    @SerializedName("Global")
    val global: Global,
    @SerializedName("Countries")
    val countries: List<Countries>,
    @SerializedName("Date")
    val date: String
)
