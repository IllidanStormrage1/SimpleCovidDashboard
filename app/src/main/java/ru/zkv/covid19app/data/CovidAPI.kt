package ru.zkv.covid19app.data

import retrofit2.http.GET
import ru.zkv.covid19app.data.response.BaseCovidAPIResponse

interface CovidAPI {

    @GET("summary")
    suspend fun summaryData(): BaseCovidAPIResponse
}