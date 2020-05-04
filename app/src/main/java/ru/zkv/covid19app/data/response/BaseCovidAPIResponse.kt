package ru.zkv.covid19app.data.response

import com.google.gson.annotations.SerializedName

class BaseCovidAPIResponse(
    @SerializedName("Global")
    val global: Global,
    @SerializedName("Countries")
    val countries: List<Country>
)