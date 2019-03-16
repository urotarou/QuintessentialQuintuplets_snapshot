package lab.uro.kitori.quintessentialquintuplets.data.resource.local.entity

import androidx.annotation.StringRes
import java.io.Serializable

data class License(
    @StringRes val libraryName: Int,
    @StringRes val licenseText: Int
) : Serializable
