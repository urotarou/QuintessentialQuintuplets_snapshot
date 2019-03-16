package lab.uro.kitori.quintessentialquintuplets.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import lab.uro.kitori.quintessentialquintuplets.BuildConfig
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.api.TwitterApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {
    @Singleton
    @Provides
    fun provideTwitterApi(client: OkHttpClient): TwitterApi = Retrofit.Builder()
        .client(client)
        .baseUrl(TwitterApi.API_END_POINT)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .build()
        .create(TwitterApi::class.java)

    @Singleton
    @Provides
    fun buildNetworkClient(): OkHttpClient {
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
