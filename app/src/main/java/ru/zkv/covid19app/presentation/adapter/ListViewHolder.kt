package ru.zkv.covid19app.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import ru.zkv.covid19app.data.response.Countries

class ListViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(item: Countries) = containerView.run {
        textViewCountryNameMain.text = item.country
        textViewNewConfirmedMain.text = item.newConfirmed.toString()
        textViewNewRecoveredMain.text = item.newRecovered.toString()
        textViewNewDeathMain.text = item.newDeaths.toString()
    }
}
// https://www.countryflags.io/ru/shiny/64.png