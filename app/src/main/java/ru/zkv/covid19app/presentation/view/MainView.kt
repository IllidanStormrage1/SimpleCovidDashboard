package ru.zkv.covid19app.presentation.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import ru.zkv.covid19app.data.response.Global
import ru.zkv.covid19app.presentation.adapter.DataAdapter

interface MainView : MvpView {

    @AddToEndSingle
    fun setRecyclerViewAdapter(adapter: DataAdapter)

    @AddToEndSingle
    fun initHeaderView(value: Global)

    @OneExecution
    fun showLoading(isLoading: Boolean)

    @OneExecution
    fun showError(errorText: String)
}