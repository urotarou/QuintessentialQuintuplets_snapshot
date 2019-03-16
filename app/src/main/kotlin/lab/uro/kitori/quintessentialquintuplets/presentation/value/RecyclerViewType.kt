package lab.uro.kitori.quintessentialquintuplets.presentation.value

import androidx.annotation.LayoutRes
import lab.uro.kitori.quintessentialquintuplets.R

enum class RecyclerViewType(@LayoutRes val viewType: Int) {
    LOADING(R.layout.item_loading),
    TWEET(R.layout.item_tweet),
    LICENSE(R.layout.item_license)
}
