package lab.uro.kitori.quintessentialquintuplets.data.resource.remote.response

import com.squareup.moshi.Json
import java.io.Serializable

data class TwitterToken(
        @Json(name = "token_type") val tokenType: String?,
        @Json(name = "access_token") val accessToken: String?
) : Serializable
