package lab.uro.kitori.quintessentialquintuplets.data.resource.remote.response

import com.squareup.moshi.Json
import java.io.Serializable

data class Tweets(
        @Json(name = "statuses") val tweets: Array<Tweet>?
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tweets

        if (tweets != null) {
            if (other.tweets == null) return false
            if (!tweets.contentEquals(other.tweets)) return false
        } else if (other.tweets != null) return false

        return true
    }

    override fun hashCode(): Int {
        return tweets?.contentHashCode() ?: 0
    }
}
