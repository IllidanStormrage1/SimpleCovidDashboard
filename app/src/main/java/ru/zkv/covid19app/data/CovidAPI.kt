package ru.zkv.covid19app.data

import retrofit2.Response
import retrofit2.http.GET
import ru.zkv.covid19app.data.response.Countries
import ru.zkv.covid19app.data.response.Global

interface CovidAPI {

    @GET("summary")
    suspend fun globalData(): Response<Global>

    @GET("summary")
    suspend fun countriesData(): Response<Countries>
}