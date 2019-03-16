package lab.uro.kitori.quintessentialquintuplets.presentation.view.bottomsheet

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import lab.uro.kitori.quintessentialquintuplets.R
import lab.uro.kitori.quintessentialquintuplets.databinding.BottomSheetTweetContextMenuBinding

class TweetContextMenuBottomSheet : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "TweetContextMenuBottomSheet"

        private const val KEY_ARGUMENT_TWEET_URL = "key_argument_tweet_url"
        private const val KEY_ARGUMENT_IMAGE_URL = "key_argument_image_url"

        fun newInstance(tweetUrl: String?, imageUrl: String?) =
            TweetContextMenuBottomSheet().apply {
                arguments = Bundle().apply {
                    putString(KEY_ARGUMENT_TWEET_URL, tweetUrl)
                    putString(KEY_ARGUMENT_IMAGE_URL, imageUrl)
                }
            }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        val binding = DataBindingUtil.inflate<BottomSheetTweetContextMenuBinding>(
            LayoutInflater.from(context), R.layout.bottom_sheet_tweet_context_menu, null, false
        )
        dialog.setContentView(binding.root)

        binding.shareTweetMaskView.setOnClickListener {
            arguments?.getString(KEY_ARGUMENT_TWEET_URL)?.let { url ->
                share(
                    getString(R.string.share_tweet_title),
                    getString(R.string.share_tweet_subject),
                    getString(R.string.share_tweet_text, url)
                )

            }
            dismiss()
        }
        binding.shareImageMaskView.setOnClickListener {
            arguments?.getString(KEY_ARGUMENT_IMAGE_URL)?.let { url ->
                share(
                    getString(R.string.share_image_title),
                    getString(R.string.share_image_subject),
                    getString(R.string.share_image_text, url)
                )
            }
            dismiss()
        }

        return dialog
    }

    private fun share(shareTitle: String = "", subject: String = "", text: String = "") {
        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        startActivity(Intent.createChooser(intent, shareTitle))
    }
}
