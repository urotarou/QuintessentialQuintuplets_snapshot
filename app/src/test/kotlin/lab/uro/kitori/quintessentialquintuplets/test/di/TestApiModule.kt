package lab.uro.kitori.quintessentialquintuplets.test.di

import dagger.Module
import dagger.Provides
import io.mockk.coEvery
import io.mockk.mockk
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.api.TwitterApi
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.entity.TwitterToken
import lab.uro.kitori.quintessentialquintuplets.test.dummy.DUMMY_TWITTER_ACCESS_TOKEN
import lab.uro.kitori.quintessentialquintuplets.test.dummy.DUMMY_TWITTER_SCREEN_NAME
import lab.uro.kitori.quintessentialquintuplets.test.dummy.dummyTweets
import javax.inject.Singleton

@Module
class TestApiModule {
    @Singleton
    @Provides
    fun provideTwitterApi(): TwitterApi = mockk<TwitterApi>().apply {
        coEvery {
            getBearerToken()
        } returns TwitterToken("bearer", DUMMY_TWITTER_ACCESS_TOKEN)

        coEvery {
            getTimeline("bearer $DUMMY_TWITTER_ACCESS_TOKEN", DUMMY_TWITTER_SCREEN_NAME, 100, any())
        } returns dummyTweets
    }
}
