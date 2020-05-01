package ru.zkv.covid19app.presentation.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import ru.zkv.covid19app.R
import ru.zkv.covid19app.data.response.Countries
import ru.zkv.covid19app.domain.inflate
import ru.zkv.covid19app.domain.surrogateAll

class DataAdapter : RecyclerView.Adapter<DataAdapter.ListViewHolder>() {

    private val itemList = mutableListOf<Countries>()

    fun attachData(newData: Collection<Countries>) {
        itemList.surrogateAll(newData)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) =
        holder.bind(item = itemList[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(parent.inflate(R.layout.recyclerview_item))

    override fun getItemCount(): Int = itemList.size

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

}
