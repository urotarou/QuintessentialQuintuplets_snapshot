package lab.uro.kitori.quintessentialquintuplets.presentation.view.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lab.uro.kitori.quintessentialquintuplets.databinding.ItemLoadingBinding
import lab.uro.kitori.quintessentialquintuplets.presentation.value.RecyclerViewType

data class LoadingViewHolder(
    val binding: ItemLoadingBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup) = LoadingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                RecyclerViewType.LOADING.viewType,
                parent,
                false
            )
        )
    }
}
