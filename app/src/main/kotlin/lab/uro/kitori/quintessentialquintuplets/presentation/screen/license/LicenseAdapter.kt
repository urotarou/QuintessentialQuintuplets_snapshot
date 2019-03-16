package lab.uro.kitori.quintessentialquintuplets.presentation.screen.license

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lab.uro.kitori.quintessentialquintuplets.presentation.item.LicenseItem
import lab.uro.kitori.quintessentialquintuplets.presentation.view.viewholder.LicenseViewHolder

class LicenseAdapter : RecyclerView.Adapter<LicenseViewHolder>() {
    private val items = arrayListOf<LicenseItem>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LicenseViewHolder.create(parent)

    override fun onBindViewHolder(holder: LicenseViewHolder, position: Int) {
        val item = items[position]

        holder.bind(item)
    }

    fun setItems(licenses: Array<LicenseItem>) {
        items.addAll(licenses)

        notifyDataSetChanged()
    }
}
