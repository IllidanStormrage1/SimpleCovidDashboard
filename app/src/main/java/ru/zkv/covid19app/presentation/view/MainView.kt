package ru.zkv.covid19app.presentation.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip
import ru.zkv.covid19app.data.response.Global
import ru.zkv.covid19app.presentation.adapter.DataAdapter

interface MainView : MvpView {

    @AddToEndSingle
    fun initRecyclerViewAdapter(adapter: DataAdapter)

    @AddToEndSingle
    fun initHeaderView(value: Global?)

    @Skip
    fun hideLoading()

    @Skip
    fun showLoading()
}