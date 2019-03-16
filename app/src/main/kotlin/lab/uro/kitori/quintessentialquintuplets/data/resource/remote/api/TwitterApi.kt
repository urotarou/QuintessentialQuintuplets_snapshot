package lab.uro.kitori.quintessentialquintuplets.data.resource.remote.api

import lab.uro.kitori.quintessentialquintuplets.BuildConfig
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.entity.Tweet
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.entity.Tweets
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.entity.TwitterToken
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.entity.TwitterUser
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface TwitterApi {
    companion object {
        private const val BASIC_AUTH_VALUE = BuildConfig.TWITTER_AUTH

        private const val API_BASE_URL = "https://api.twitter.com/"
        private const val API_OAUTH_TYPE = "oauth2/"
        private const val API_VERSION = "1.1/"

        const val API_END_POINT = API_BASE_URL
    }

    @Headers(
        value = [
            "Authorization: Basic $BASIC_AUTH_VALUE",
            "Content-Type: application/x-www-form-urlencoded;charset=UTF-8"
        ]
    )
    @FormUrlEncoded
    @POST("${API_OAUTH_TYPE}token")
    suspend fun getBearerToken(
        @Field("grant_type") body: String = "client_credentials"
    ): TwitterToken

    @GET("${API_VERSION}users/show.json")
    suspend fun getUserInfo(
        @Header("Authorization") authHeader: String,
        @Query("screen_name") screenName: String
    ): TwitterUser

    @GET("${API_VERSION}statuses/user_timeline.json")
    suspend fun getTimeline(
        @Header("Authorization") authHeader: String,
        @Query("screen_name") screenName: String,
        @Query("count") count: Int,
        @Query("max_id") maxId: Long? = null,
        @Query("exclude_replies") excludeReplies: Boolean? = true,
        @Query("include_rts") includeRts: Boolean? = false
    ): Array<Tweet>

    @GET("${API_VERSION}search/tweets.json")
    suspend fun searchTweet(
        @Header("Authorization") authHeader: String,
        @Query("q") query: String,
        @Query("count") count: Int,
        @Query("max_id") maxId: Long? = null,
        @Query("lang") language: String = "ja",
        @Query("local") local: String = "ja"
    ): Tweets
}
