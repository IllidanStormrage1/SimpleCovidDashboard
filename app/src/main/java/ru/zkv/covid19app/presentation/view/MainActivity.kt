package ru.zkv.covid19app.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_header_view.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.zkv.covid19app.App
import ru.zkv.covid19app.R
import ru.zkv.covid19app.data.response.Global
import ru.zkv.covid19app.presentation.adapter.DataAdapter
import ru.zkv.covid19app.presentation.adapter.SpacingItemDecoration
import ru.zkv.covid19app.presentation.presenter.MainPresenter
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    lateinit var mainPresenterProvider: Provider<MainPresenter>
    private val presenter: MainPresenter by moxyPresenter { mainPresenterProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRecyclerView.addItemDecoration(SpacingItemDecoration(resources.getDimensionPixelSize(R.dimen.card_padding)))
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
        progressBar.isVisible = isLoading
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
