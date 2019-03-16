package lab.uro.kitori.quintessentialquintuplets.presentation.view.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lab.uro.kitori.quintessentialquintuplets.R
import lab.uro.kitori.quintessentialquintuplets.databinding.ItemLicenseBinding
import lab.uro.kitori.quintessentialquintuplets.presentation.item.LicenseItem
import lab.uro.kitori.quintessentialquintuplets.presentation.value.RecyclerViewType

data class LicenseViewHolder(
    val binding: ItemLicenseBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup) = LicenseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                RecyclerViewType.LICENSE.viewType,
                parent,
                false
            )
        )
    }

    fun bind(license: LicenseItem) {
        val context = binding.root.context

        binding.libraryNameTextView.text = license.getLicenseName(context)

        binding.licenseTextView.text = license.getLicenseText(context)
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
