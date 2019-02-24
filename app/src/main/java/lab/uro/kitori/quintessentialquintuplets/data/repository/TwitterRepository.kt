package lab.uro.kitori.quintessentialquintuplets.data.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.TwitterClient
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.response.Tweet
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.response.Tweets
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.response.TwitterToken
import timber.log.Timber

class TwitterRepository(
        private val coroutineScope: CoroutineScope
) {
    companion object {
        private const val TOKEN_TYPE_BEARER = "bearer"
    }

    private var token: TwitterToken? = null

    suspend fun getTimeline(screenName: String, nextId: Long? = null, count: Int = 100): Array<Tweet> {
        val accessToken = getToken()?.accessToken ?: ""

        return TwitterClient.getClient().getTimeline(accessToken, screenName, count, nextId).await()
    }

    suspend fun searchTweet(query: String, nextId: Long? = null, count: Int = 100): Tweets {
        val accessToken = getToken()?.accessToken ?: ""

        return TwitterClient.getClient().searchTweet(accessToken, query, count).await()
    }

    private suspend fun getBearerToken(): TwitterToken? {
        val bearerToken = TwitterClient.getClient().getBearerToken().await()

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
        withContext(coroutineScope.coroutineContext) { getBearerToken() }
    }
}
