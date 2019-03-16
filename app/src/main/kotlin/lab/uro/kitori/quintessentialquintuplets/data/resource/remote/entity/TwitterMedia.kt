package lab.uro.kitori.quintessentialquintuplets.data.resource.remote.entity

import com.squareup.moshi.Json
import java.io.Serializable

data class TwitterMedia(
    @Json(name = "id") val id: Long?,
    @Json(name = "media_url_https") val mediaUrl: String?
) : Serializable
