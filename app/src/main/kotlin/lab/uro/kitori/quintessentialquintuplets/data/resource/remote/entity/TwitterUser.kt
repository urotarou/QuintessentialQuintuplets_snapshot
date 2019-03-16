package lab.uro.kitori.quintessentialquintuplets.data.resource.remote.entity

import com.squareup.moshi.Json
import java.io.Serializable

data class TwitterUser(
    @Json(name = "id") val id: Long?,
    @Json(name = "name") val userName: String?,
    @Json(name = "screen_name") val screenName: String?,
    @Json(name = "profile_image_url_https") val profileImageUrl: String?,
    @Json(name = "profile_banner_url") val profileBannerUrl: String?,
    val description: String?,
    @Json(name = "url") val siteUrl: String?,
    @Json(name = "status") val tweet: Tweet?
) : Serializable
