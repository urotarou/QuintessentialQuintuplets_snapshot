package lab.uro.kitori.quintessentialquintuplets.presentation.screen.imageviewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import lab.uro.kitori.quintessentialquintuplets.R
import lab.uro.kitori.quintessentialquintuplets.databinding.FragmentImageViewerBinding
import lab.uro.kitori.quintessentialquintuplets.presentation.view.GlideApp

class ImageViewerFragment : Fragment() {
    companion object {
        const val TAG = "ImageViewerFragment"

        private const val KEY_ARGUMENT_IMAGE_URL = "key_argument_image_url"

        fun newInstance(imageUrl: String) = ImageViewerFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_ARGUMENT_IMAGE_URL, imageUrl)
            }
        }
    }

    private lateinit var binding: FragmentImageViewerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_viewer, container, false)

        initLayout()

        return binding.root
    }

    private fun initLayout() {
        GlideApp.with(this)
                .load(getImageUrl())
                .into(binding.photoView)
    }

    private fun getImageUrl() = arguments?.getString(KEY_ARGUMENT_IMAGE_URL)
}
