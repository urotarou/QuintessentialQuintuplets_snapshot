package lab.uro.kitori.quintessentialquintuplets.presentation.item

import android.content.Context
import lab.uro.kitori.quintessentialquintuplets.data.resource.local.entity.License
import java.io.Serializable

data class LicenseItem(
    private val license: License,
    var isExpand: Boolean = false
) : Serializable {
    fun getLicenseName(context: Context) = context.getString(license.libraryName) ?: ""

    fun getLicenseText(context: Context) = context.getString(license.licenseText) ?: ""
}
