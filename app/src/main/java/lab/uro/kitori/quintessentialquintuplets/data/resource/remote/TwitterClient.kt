package lab.uro.kitori.quintessentialquintuplets.data.resource.remote

import lab.uro.kitori.quintessentialquintuplets.BuildConfig
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.api.TwitterApi
import lab.uro.kitori.quintessentialquintuplets.extension.forTwitterApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object TwitterClient {
    private var twitterApi: TwitterApi? = null

    fun getClient(): TwitterApi {
        val api = twitterApi

        return if (api != null) {
            api
        } else {
            val newApi = buildNetworkClient().forTwitterApi()
            twitterApi = newApi
            newApi
        }
    }

    private fun buildNetworkClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        val logging = HttpLoggingInterceptor()
        logging.level = when {
            BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BASIC
            else -> HttpLoggingInterceptor.Level.NONE
        }
        builder.addInterceptor(logging)

        return builder.build()
    }
}
