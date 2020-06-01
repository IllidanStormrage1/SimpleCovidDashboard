package ru.zkv.covid19app.domain.interactor

import kotlinx.coroutines.*
import ru.zkv.covid19app.data.CovidAPI
import ru.zkv.covid19app.data.response.BaseCovidAPIResponse
import ru.zkv.covid19app.domain.Result
import javax.inject.Inject

class MainInteractor @Inject constructor(private val apiModule: CovidAPI) {

    private val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    private suspend fun <T : Any> safeApiCall(call: suspend () -> T?): Result<T> =
        withContext(coroutineScope.coroutineContext) {
            try {
                Result.Success(data = call()!!)
            } catch (throwable: Throwable) {
                Result.Failure(throwable = throwable)
            }
        }

    suspend fun getSummaryData() =
        safeApiCall {
            apiModule.summaryData()
        }

    fun prepareCountriesData(data: BaseCovidAPIResponse) =
        data.countries
            .filter { it.totalRecovered != 0 && it.totalConfirmed != 0 && it.totalRecovered != 0 }
            .sortedBy { it.totalConfirmed }
            .asReversed()

    fun prepareHeaderData(data: BaseCovidAPIResponse) = data.global

    fun onDestroy() {
        coroutineScope.cancel()
    }
}