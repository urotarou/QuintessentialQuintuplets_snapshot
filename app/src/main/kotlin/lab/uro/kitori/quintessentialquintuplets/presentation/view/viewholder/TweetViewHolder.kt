package lab.uro.kitori.quintessentialquintuplets.presentation.view.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lab.uro.kitori.quintessentialquintuplets.R
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.entity.Tweet
import lab.uro.kitori.quintessentialquintuplets.databinding.ItemTweetBinding
import lab.uro.kitori.quintessentialquintuplets.domain.value.Heroine
import lab.uro.kitori.quintessentialquintuplets.extension.toTweetDateTime
import lab.uro.kitori.quintessentialquintuplets.presentation.listener.TweetActionListener
import lab.uro.kitori.quintessentialquintuplets.presentation.value.RecyclerViewType
import lab.uro.kitori.quintessentialquintuplets.presentation.view.GlideApp

data class TweetViewHolder(
    val binding: ItemTweetBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup) = TweetViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                RecyclerViewType.TWEET.viewType,
                parent,
                false
            )
        )
    }

    fun bind(item: Tweet?, heroine: Heroine, tweetActionListener: TweetActionListener) {
        item ?: return

        val tweetUrl = binding.root.context.getString(
            R.string.link_to_tweet, item.user?.screenName, item.id
        )
        binding.root.setOnClickListener {
            tweetActionListener.onTextClick(tweetUrl)
        }

        GlideApp.with(binding.root.context)
            .load(item.user?.profileImageUrl)
            .fitCenter()
            .into(binding.iconImageView)

        binding.iconMaskView.setOnClickListener {
            val link = binding.root.context.getString(R.string.link_to_user, item.user?.screenName)
            tweetActionListener.onUserClick(link)
        }

        binding.nameTextView.text = binding.root.context.getString(
            R.string.tweet_user, item.user?.userName, item.user?.screenName
        )

        binding.datetimeTextView.text = item.createdAt?.toTweetDateTime(binding.root.context)
        binding.messageTextView.text = item.tweetText

        val mediaUrl = item.entities?.media?.get(0)?.mediaUrl
        if (mediaUrl != null) {
            binding.illustrationImageView.visibility = View.VISIBLE
            GlideApp.with(binding.root.context)
                .load(mediaUrl)
                .fitCenter()
                .into(binding.illustrationImageView)
            binding.illustrationImageView.setBackgroundColor(
                getHeroineColor(binding.root.context, heroine)
            )

            binding.illustrationMaskView.setOnClickListener {
                tweetActionListener.onImageClick(mediaUrl)
            }
            binding.illustrationMaskView.setOnLongClickListener {
                tweetActionListener.onImageLongClick(tweetUrl, mediaUrl)
            }
        } else {
            binding.illustrationImageView.visibility = View.GONE
        }
    }

    private fun getHeroineColor(context: Context, heroine: Heroine) =
        ContextCompat.getColor(context, heroine.colorResId)
}
