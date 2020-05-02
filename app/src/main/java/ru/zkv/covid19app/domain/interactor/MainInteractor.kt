package ru.zkv.covid19app.domain.interactor

import kotlinx.coroutines.*
import ru.zkv.covid19app.data.CovidAPI
import javax.inject.Inject

class MainInteractor @Inject constructor(private val apiModule: CovidAPI) {

    private val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    suspend fun getGlobalData() =
        withContext(coroutineScope.coroutineContext) {
            try {
                apiModule.globalData().body()
            } catch (e: java.lang.Exception) {
                null
            }
        }

    suspend fun getCountriesData() =
        withContext(coroutineScope.coroutineContext) {
            try {
                apiModule.countriesData().body()
            } catch (e: java.lang.Exception) {
                null
            }
        }

    fun onDestroy() {
        coroutineScope.cancel()
    }
}