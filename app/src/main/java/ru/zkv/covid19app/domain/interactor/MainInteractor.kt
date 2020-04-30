package ru.zkv.covid19app.domain.interactor

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import ru.zkv.covid19app.data.CovidAPi
import ru.zkv.covid19app.data.response.BaseCovidAPIResponse
import javax.inject.Inject

class MainInteractor @Inject constructor(private val apiModule: CovidAPi) {

    private val interactorScope = CoroutineScope(Dispatchers.IO + Job())
    suspend fun getSummaryData(): BaseCovidAPIResponse? =
        withContext(interactorScope.coroutineContext) {
            apiModule.summaryData().body()
        }
}