package lab.uro.kitori.quintessentialquintuplets.presentation.screen.license

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lab.uro.kitori.quintessentialquintuplets.R
import lab.uro.kitori.quintessentialquintuplets.presentation.view.viewholder.LicenseViewHolder

class LicenseAdapter(
        context: Context
) : RecyclerView.Adapter<LicenseViewHolder>() {
    private val items = arrayOf(
            License(context.getString(R.string.license_kotlin_name), context.getString(R.string.license_kotlin_text)),
            License(context.getString(R.string.license_kotlin_coroutines_name), context.getString(R.string.license_kotlin_coroutines_text)),
            License(context.getString(R.string.license_material_design_icons_name), context.getString(R.string.license_material_design_icons_text)),
            License(context.getString(R.string.license_material_components_android_name), context.getString(R.string.license_material_components_android_text)),
            License(context.getString(R.string.license_androidx_name), context.getString(R.string.license_androidx_text)),
            License(context.getString(R.string.license_okhttp3_name), context.getString(R.string.license_okhttp3_text)),
            License(context.getString(R.string.license_retrofit2_name), context.getString(R.string.license_retrofit2_text)),
            License(context.getString(R.string.license_retrofit2_kotlin_coroutines_adapter_name), context.getString(R.string.license_retrofit2_kotlin_coroutines_adapter_text)),
            License(context.getString(R.string.license_moshi_name), context.getString(R.string.license_moshi_text)),
            License(context.getString(R.string.license_threetenabp_name), context.getString(R.string.license_threetenabp_text)),
            License(context.getString(R.string.license_glide_name), context.getString(R.string.license_glide_text)),
            License(context.getString(R.string.license_photoview_name), context.getString(R.string.license_photoview_text)),
            License(context.getString(R.string.license_timber_name), context.getString(R.string.license_timber_text))
    )

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            LicenseViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_license, parent, false))

    override fun onBindViewHolder(holder: LicenseViewHolder, position: Int) {
        val item = items[position]

        holder.bind(item)
    }

    data class License(
            val libraryName: String,
            val licenseText: String,

            var isExpand: Boolean = false
    )
}
