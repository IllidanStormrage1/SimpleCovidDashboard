package ru.zkv.covid19app.presentation.presenter

import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
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

    private fun initRecyclerViewAdapter(data: BaseCovidAPIResponse) {
        dataAdapter.attachData(data.countries)
        viewState.setRecyclerViewAdapter(adapter = dataAdapter)
    }

    private fun initHeader(data: BaseCovidAPIResponse) {
        viewState.initHeaderView(data.global)
    }

    private suspend fun getData(): BaseCovidAPIResponse? = mainInteractor.getSummaryData()

    override fun onDestroy() {
        super.onDestroy()
        mainInteractor.onDestroy()
    }
}