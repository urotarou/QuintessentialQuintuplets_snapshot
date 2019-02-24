package lab.uro.kitori.quintessentialquintuplets.data.resource.remote.response

import com.squareup.moshi.Json
import java.io.Serializable

data class Tweet(
        @Json(name = "id") val id: Long?,
        @Json(name = "text") val tweetText: String?,
        @Json(name = "created_at") val createdAt: String?,
        @Json(name = "user") val user: TwitterUser?,
        @Json(name = "entities") val entities: Entities?
) : Serializable {
    data class Entities(
            @Json(name = "media") val media: Array<TwitterMedia>?
    ) : Serializable
}
