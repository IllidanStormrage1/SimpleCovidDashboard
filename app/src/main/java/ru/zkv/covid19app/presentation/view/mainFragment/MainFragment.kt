package ru.zkv.covid19app.presentation.view.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.main_header_view.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.zkv.covid19app.App
import ru.zkv.covid19app.R
import ru.zkv.covid19app.data.response.Global
import ru.zkv.covid19app.presentation.adapter.DataAdapter
import ru.zkv.covid19app.presentation.presenter.MainPresenter
import javax.inject.Inject
import javax.inject.Provider


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : MvpAppCompatFragment(),
    MainView {

    @Inject
    lateinit var mainPresenterProvider: Provider<MainPresenter>
    private val presenter: MainPresenter by moxyPresenter { mainPresenterProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
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
        AlertDialog.Builder(requireContext()).run {
            setCancelable(false)
            setTitle(R.string.title)
            setMessage(errorText)
            setPositiveButton(R.string.accept) { _, _ -> presenter.onRefresh() }
            show()
        }
    }

    companion object {

        val TAG = this::class.simpleName

        @JvmStatic
        fun newInstance() = MainFragment()
    }
}