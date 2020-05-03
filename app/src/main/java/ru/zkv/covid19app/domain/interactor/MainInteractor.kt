package ru.zkv.covid19app.domain.interactor

import kotlinx.coroutines.*
import ru.zkv.covid19app.data.CovidAPI
import javax.inject.Inject

class MainInteractor @Inject constructor(private val apiModule: CovidAPI) {

    private val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    suspend fun getCountriesData() =
        withContext(coroutineScope.coroutineContext) {
            try {
                apiModule.summaryData().body()?.countries
                    ?.filter { it.totalConfirmed != 0 && it.totalRecovered != 0 && it.totalDeaths != 0 }
                    ?.sortedBy { it.totalConfirmed }
                    ?.reversed()
            } catch (e: Exception) {
                null
            }
        }

    suspend fun getGlobalData() =
        withContext(coroutineScope.coroutineContext) {
            try {
                apiModule.summaryData().body()?.global
            } catch (e: Exception) {
                null
            }
        }

    fun onDestroy() {
        coroutineScope.cancel()
    }
}