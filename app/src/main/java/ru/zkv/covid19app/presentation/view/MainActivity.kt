package ru.zkv.covid19app.presentation.view

import android.os.Bundle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    lateinit var mainPresenterProvider: javax.inject.Provider<MainPresenter>
    private val presenter: MainPresenter by moxyPresenter { mainPresenterProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout.run {
            setProgressViewOffset(true, 0, 300)
            setOnRefreshListener { presenter.onRefresh() }
        }
    }

    override fun setRecyclerViewAdapter(adapter: DataAdapter) {
        mainRecyclerView.adapter = adapter
    }

    override fun initHeaderView(value: Global) =
        value.run {
            textViewTotalConfirmed.text = TotalConfirmed.toString()
            textViewTotalConfirmed.text = TotalConfirmed.toString()
            textViewTotalRecovered.text = totalRecovered.toString()
            textViewTotalDeaths.text = totalDeaths.toString()
            textViewNewConfirmedHeader.text = newConfirmed.toString()
            textViewNewRecoveredHeader.text = newRecovered.toString()
            textViewNewDeathsHeader.text = newDeaths.toString()
        }

    override fun showLoading(isLoading: Boolean) {
        swipeRefreshLayout.isRefreshing = isLoading
    }

    override fun showError() {
        MaterialAlertDialogBuilder(this).run {
            setCancelable(false)
            setTitle(R.string.title)
            setMessage(R.string.text)
            setPositiveButton(R.string.accept) { _, _ -> presenter.onRefresh() }
            show()
        }
    }
}
