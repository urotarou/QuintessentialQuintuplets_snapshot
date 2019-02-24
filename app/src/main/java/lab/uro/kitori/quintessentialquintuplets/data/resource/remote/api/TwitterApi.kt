package lab.uro.kitori.quintessentialquintuplets.data.resource.remote.api

import kotlinx.coroutines.Deferred
import lab.uro.kitori.quintessentialquintuplets.BuildConfig
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.response.Tweet
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.response.Tweets
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.response.TwitterToken
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.response.TwitterUser
import retrofit2.http.*


interface TwitterApi {
    companion object {
        private const val BASIC_AUTH_VALUE = BuildConfig.TWITTER_AUTH

        private const val API_BASE_URL = "https://api.twitter.com/"
        private const val API_OAUTH_TYPE = "oauth2/"
        private const val API_VERSION = "1.1/"

        const val API_END_POINT = API_BASE_URL
    }

    @Headers(value = [
        "Authorization: Basic $BASIC_AUTH_VALUE",
        "Content-Type: application/x-www-form-urlencoded;charset=UTF-8"
    ])
    @FormUrlEncoded
    @POST("${API_OAUTH_TYPE}token")
    fun getBearerToken(@Field("grant_type") body: String = "client_credentials"): Deferred<TwitterToken>

    @GET("${API_VERSION}users/show.json")
    fun getUserInfo(
            @Header("Authorization") authHeader: String,
            @Query("screen_name") screenName: String): Deferred<TwitterUser>

    @GET("${API_VERSION}statuses/user_timeline.json")
    fun getTimeline(
            @Header("Authorization") authHeader: String,
            @Query("screen_name") screenName: String,
            @Query("count") count: Int,
            @Query("max_id") maxId: Long? = null,
            @Query("exclude_replies") excludeReplies: Boolean? = true,
            @Query("include_rts") includeRts: Boolean? = false): Deferred<Array<Tweet>>

    @GET("${API_VERSION}search/tweets.json")
    fun searchTweet(
            @Header("Authorization") authHeader: String,
            @Query("q") query: String,
            @Query("count") count: Int,
            @Query("max_id") maxId: Long? = null,
            @Query("lang") language: String = "ja",
            @Query("local") local: String = "ja"): Deferred<Tweets>
}
