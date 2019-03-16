package lab.uro.kitori.quintessentialquintuplets.presentation.screen.searchresult

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.entity.Tweet
import lab.uro.kitori.quintessentialquintuplets.domain.value.Heroine
import lab.uro.kitori.quintessentialquintuplets.domain.value.LoadingState
import lab.uro.kitori.quintessentialquintuplets.presentation.listener.TweetActionListener
import lab.uro.kitori.quintessentialquintuplets.presentation.value.RecyclerViewType
import lab.uro.kitori.quintessentialquintuplets.presentation.view.viewholder.LoadingViewHolder
import lab.uro.kitori.quintessentialquintuplets.presentation.view.viewholder.TweetViewHolder

class SearchResultAdapter(
    private val heroine: Heroine,
    private val tweetActionListener: TweetActionListener
) : PagedListAdapter<Tweet, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Tweet>() {
            override fun areItemsTheSame(oldItem: Tweet, newItem: Tweet): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Tweet, newItem: Tweet): Boolean =
                oldItem == newItem
        }
    }

    private var loadingState = LoadingState.IDLE

    override fun getItemCount() = super.getItemCount() + if (hasLoadingItem()) 1 else 0

    override fun getItemViewType(position: Int) =
        if (hasLoadingItem() && position == itemCount - 1) {
            RecyclerViewType.LOADING.viewType
        } else {
            RecyclerViewType.TWEET.viewType
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        RecyclerViewType.LOADING.viewType -> LoadingViewHolder.create(parent)
        RecyclerViewType.TWEET.viewType -> TweetViewHolder.create(parent)
        else -> LoadingViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LoadingViewHolder -> Unit // nothing to do
            is TweetViewHolder -> {
                val item = getItem(position)
                item ?: return

                holder.bind(item, heroine, tweetActionListener)
            }
        }
    }

    fun changeLoadingState(state: LoadingState) {
        loadingState = state

        if (!hasLoadingItem()) notifyItemRemoved(itemCount)
    }

    private fun hasLoadingItem() = loadingState == LoadingState.LOADING
}
