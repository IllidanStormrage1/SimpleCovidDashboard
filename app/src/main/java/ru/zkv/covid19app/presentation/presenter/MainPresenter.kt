package ru.zkv.covid19app.presentation.presenter

import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import ru.zkv.covid19app.data.response.BaseCovidAPIResponse
import ru.zkv.covid19app.domain.interactor.MainInteractor
import ru.zkv.covid19app.presentation.adapter.DataAdapter
import ru.zkv.covid19app.presentation.view.MainView
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(private val mainInteractor: MainInteractor) :
    MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        update()
    }

    fun update() {
        presenterScope.launch {
            viewState.showLoading(true)
            val data = getData()
            if (data != null) {
                initHeader(data)
                initRecyclerViewAdapter(data)
            } else {
                viewState.showError()
            }
            viewState.showLoading(false)
        }
    }

    private val dataAdapter: DataAdapter = DataAdapter()

    private fun initRecyclerViewAdapter(data: BaseCovidAPIResponse) {
        dataAdapter.attachData(data.countries)
        viewState.setRecyclerViewAdapter(adapter = dataAdapter)
    }

    private fun initHeader(data: BaseCovidAPIResponse) {
        viewState.initHeaderView(data.global)
    }

    private suspend fun getData(): BaseCovidAPIResponse? = mainInteractor.getSummaryData()
}