package ru.zkv.covid19app.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_header_view.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.zkv.covid19app.App
import ru.zkv.covid19app.R
import ru.zkv.covid19app.data.response.Global
import ru.zkv.covid19app.presentation.adapter.DataAdapter
import ru.zkv.covid19app.presentation.presenter.MainPresenter
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : @javax.inject.Inject MvpAppCompatActivity(), MainView {

    @Inject
    lateinit var mainPresenterProvider: Provider<MainPresenter>
    private val presenter: MainPresenter by moxyPresenter { mainPresenterProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout.run {
            setProgressViewOffset(true, 0, 300)
            setOnRefreshListener { presenter.onRefresh() }
            setColorSchemeResources(R.color.colorRed, R.color.colorLightGreen, R.color.colorBlue)
        }

        mainRecyclerView.setHasFixedSize(true)
    }

    override fun setRecyclerViewAdapter(adapter: DataAdapter) {
        mainRecyclerView.adapter = adapter
    }

    override fun initHeaderView(value: Global) =
        value.run {
            textViewTotalConfirmed.text = totalConfirmed.toString()
            textViewTotalRecovered.text = totalRecovered.toString()
            textViewTotalDeaths.text = totalDeaths.toString()

            textViewNewConfirmedHeader.text = resources.getString(R.string.plus, newConfirmed)
            textViewNewRecoveredHeader.text = resources.getString(R.string.plus, newRecovered)
            textViewNewDeathsHeader.text = resources.getString(R.string.plus, newDeaths)
        }

    override fun showLoading(isLoading: Boolean) {
        swipeRefreshLayout.isRefreshing = isLoading
    }

    override fun showError(errorText: String) {
        AlertDialog.Builder(this).run {
            setCancelable(false)
            setTitle(R.string.title)
            setMessage(errorText)
            setPositiveButton(R.string.accept) { _, _ -> presenter.onRefresh() }
            show()
        }
    }
}
