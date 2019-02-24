package lab.uro.kitori.quintessentialquintuplets.extension

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.api.TwitterApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun OkHttpClient.forTwitterApi(): TwitterApi = Retrofit.Builder()
        .client(this)
        .baseUrl(TwitterApi.API_END_POINT)
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(TwitterApi::class.java)
