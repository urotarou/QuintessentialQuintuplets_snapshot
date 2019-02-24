package lab.uro.kitori.quintessentialquintuplets.presentation.view.viewholder

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import lab.uro.kitori.quintessentialquintuplets.R
import lab.uro.kitori.quintessentialquintuplets.databinding.ItemLicenseBinding
import lab.uro.kitori.quintessentialquintuplets.presentation.screen.license.LicenseAdapter

data class LicenseViewHolder(
        val binding: ItemLicenseBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(license: LicenseAdapter.License) {
        binding.libraryNameTextView.text = license.libraryName

        binding.licenseTextView.text = license.licenseText
        if (license.isExpand) {
            binding.licenseTextView.visibility = View.VISIBLE
        } else {
            binding.licenseTextView.visibility = View.GONE
        }

        val fadeInAnimation = AnimationUtils.loadAnimation(itemView.context, R.anim.anim_fade_in)
        val fadeOutAnimation = AnimationUtils.loadAnimation(itemView.context, R.anim.anim_fade_out)
        fadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
                // nothing to do
            }

            override fun onAnimationEnd(animation: Animation?) {
                binding.arrowImageView.setImageResource(R.drawable.ic_expand_more_large)
                binding.licenseTextView.visibility = View.GONE
                license.isExpand = false
            }

            override fun onAnimationStart(animation: Animation?) {
                // nothing to do
            }
        })

        binding.touchAreaView.setOnClickListener {
            when (binding.licenseTextView.visibility) {
                View.VISIBLE -> {
                    binding.licenseTextView.startAnimation(fadeOutAnimation)
                }
                else -> {
                    binding.arrowImageView.setImageResource(R.drawable.ic_expand_less_large)
                    binding.licenseTextView.visibility = View.VISIBLE
                    binding.licenseTextView.startAnimation(fadeInAnimation)
                    license.isExpand = true
                }
            }
        }
    }
}
