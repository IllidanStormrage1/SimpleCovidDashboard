package ru.zkv.covid19app.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recyclerview_new_item.view.*
import ru.zkv.covid19app.R
import ru.zkv.covid19app.data.response.Country

class ListViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(item: Country) = containerView.run {
        textViewCountryNameMain.text = item.country
        Glide.with(context).load("https://www.countryflags.io/${item.countryCode}/flat/64.png")
            .into(imageViewCountry)

        textViewNewConfirmedMain.text = resources.getString(R.string.plus, item.newConfirmed)
        textViewNewRecoveredMain.text = resources.getString(R.string.plus, item.newRecovered)
        textViewNewDeathMain.text = resources.getString(R.string.plus, item.newDeaths)

        textViewConfirmedMain.text = resources.getString(R.string.plus, item.totalConfirmed)
        textViewRecoveredMain.text = resources.getString(R.string.plus, item.totalRecovered)
        textViewDeathMain.text = resources.getString(R.string.plus, item.totalDeaths)
    }
}