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

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        presenterScope.launch {
            viewState.showLoading()
            initHeader()
            initRecyclerViewAdapter()
            viewState.hideLoading()
        }
    }

    private suspend fun initRecyclerViewAdapter() {
        val r = getData()
        dataAdapter.attachData(r)
        viewState.initRecyclerViewAdapter(adapter = dataAdapter)
    }

    private suspend fun initHeader() {
        val r = getGlobal()
        viewState.initHeaderView(r)
    }

    private val dataAdapter: DataAdapter = DataAdapter()

    private suspend fun getData() = mainInteractor.getSummaryData()?.countries ?: listOf()

    private suspend fun getGlobal() = mainInteractor.getSummaryData()?.global


}