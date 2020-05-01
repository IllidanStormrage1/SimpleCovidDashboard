package ru.zkv.covid19app.domain.interactor

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import ru.zkv.covid19app.data.CovidAPI
import ru.zkv.covid19app.data.response.BaseCovidAPIResponse
import javax.inject.Inject

class MainInteractor @Inject constructor(private val apiModule: CovidAPI) {

    private val interactorScope = CoroutineScope(Dispatchers.IO + Job())
    suspend fun getSummaryData(): BaseCovidAPIResponse? =
        withContext(interactorScope.coroutineContext) {
            try {
                apiModule.summaryData().body()
            } catch (e: Exception) {
                null
            }
        }
}