package ru.zkv.covid19app.presentation.presenter

import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import ru.zkv.covid19app.data.response.Country
import ru.zkv.covid19app.data.response.Global
import ru.zkv.covid19app.domain.Result
import ru.zkv.covid19app.domain.interactor.MainInteractor
import ru.zkv.covid19app.presentation.adapter.DataAdapter
import ru.zkv.covid19app.presentation.view.MainView
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(private val mainInteractor: MainInteractor) :
    MvpPresenter<MainView>() {

    private val dataAdapter: DataAdapter = DataAdapter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        onRefresh()
    }

    fun onRefresh() {
        presenterScope.launch {
            viewState.showLoading(true)

            when (val apiResponse = mainInteractor.getSummaryData()) {
                is Result.Success -> {
                    initHeader(mainInteractor.prepareHeaderData(data = apiResponse.data))
                    initRecyclerViewAdapter(mainInteractor.prepareCountriesData(data = apiResponse.data))
                }
                is Result.Failure -> {
                    apiResponse.throwable.printStackTrace()
                    viewState.showError(apiResponse.throwable.toString())
                }
            }

            viewState.showLoading(false)
        }
    }

    private fun initRecyclerViewAdapter(data: Collection<Country>) {
        dataAdapter.attachData(data)
        viewState.setRecyclerViewAdapter(adapter = dataAdapter)
    }

    private fun initHeader(data: Global) {
        viewState.initHeaderView(data)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainInteractor.onDestroy()
    }
}