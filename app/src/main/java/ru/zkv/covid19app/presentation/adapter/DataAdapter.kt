package ru.zkv.covid19app.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.zkv.covid19app.R
import ru.zkv.covid19app.data.response.Country
import ru.zkv.covid19app.domain.inflate
import ru.zkv.covid19app.domain.surrogateAll

class DataAdapter : RecyclerView.Adapter<ListViewHolder>() {

    private val items = mutableListOf<Country>()

    fun attachData(newData: Collection<Country>) {
        val isInsertion = items.isEmpty()
        items.surrogateAll(newData)
        if (isInsertion)
            notifyItemRangeInserted(0, itemCount)
        else
            notifyItemRangeChanged(0, itemCount, Unit)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) =
        holder.bind(item = items[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(containerView = parent.inflate(R.layout.recyclerview_new_item))

    override fun getItemCount(): Int = items.size
}
