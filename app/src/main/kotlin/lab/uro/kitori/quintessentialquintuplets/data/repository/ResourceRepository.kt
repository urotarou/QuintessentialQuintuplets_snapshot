package lab.uro.kitori.quintessentialquintuplets.data.repository

import lab.uro.kitori.quintessentialquintuplets.R
import lab.uro.kitori.quintessentialquintuplets.data.resource.local.entity.License

class ResourceRepository {
    fun loadLicenseResources() = arrayOf(
        License(
            R.string.license_kotlin_name,
            R.string.license_kotlin_text
        ),
        License(
            R.string.license_kotlin_coroutines_name,
            R.string.license_kotlin_coroutines_text
        ),
        License(
            R.string.license_material_design_icons_name,
            R.string.license_material_design_icons_text
        ),
        License(
            R.string.license_material_components_android_name,
            R.string.license_material_components_android_text
        ),
        License(
            R.string.license_androidx_name,
            R.string.license_androidx_text
        ),
        License(
            R.string.license_okhttp3_name,
            R.string.license_okhttp3_text
        ),
        License(
            R.string.license_retrofit2_name,
            R.string.license_retrofit2_text
        ),
        License(
            R.string.license_moshi_name,
            R.string.license_moshi_text
        ),
        License(
            R.string.license_threetenabp_name,
            R.string.license_threetenabp_text
        ),
        License(
            R.string.license_glide_name,
            R.string.license_glide_text
        ),
        License(
            R.string.license_photoview_name,
            R.string.license_photoview_text
        ),
        License(
            R.string.license_timber_name,
            R.string.license_timber_text
        ),
        License(
            R.string.license_dagger2_name,
            R.string.license_dagger2_text
        )
    )
}
