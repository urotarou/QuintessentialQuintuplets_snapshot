package lab.uro.kitori.quintessentialquintuplets.data.repository

import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.api.TwitterApi
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.entity.Tweet
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.entity.Tweets
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.entity.TwitterToken
import timber.log.Timber
import javax.inject.Inject

class TwitterRepository @Inject constructor(
    private val twitterApi: TwitterApi
) {
    companion object {
        private const val TOKEN_TYPE_BEARER = "bearer"
    }

    private var token: TwitterToken? = null

    suspend fun getTimeline(
        screenName: String, nextId: Long? = null, count: Int = 100
    ): Array<Tweet> {
        val accessToken = getToken()?.accessToken ?: ""

        return twitterApi.getTimeline(accessToken, screenName, count, nextId)
    }

    suspend fun searchTweet(query: String, nextId: Long? = null, count: Int = 100): Tweets {
        val accessToken = getToken()?.accessToken ?: ""

        return twitterApi.searchTweet(accessToken, query, count)
    }

    private suspend fun getBearerToken(): TwitterToken? {
        val bearerToken = twitterApi.getBearerToken()

        if (TOKEN_TYPE_BEARER.equals(bearerToken.tokenType, true)) {
            token = bearerToken.copy(accessToken = "$TOKEN_TYPE_BEARER ${bearerToken.accessToken}")
        } else {
            Timber.e("unknown token type: ${bearerToken.tokenType}")
        }

        return token
    }

    private suspend fun getToken(): TwitterToken? = if (token != null) {
        token
    } else {
        getBearerToken()
    }
}
