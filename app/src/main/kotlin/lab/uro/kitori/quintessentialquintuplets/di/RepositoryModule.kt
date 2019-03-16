package lab.uro.kitori.quintessentialquintuplets.di

import dagger.Module
import dagger.Provides
import lab.uro.kitori.quintessentialquintuplets.data.repository.ResourceRepository
import lab.uro.kitori.quintessentialquintuplets.data.repository.TwitterRepository
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.api.TwitterApi
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideResourceRepository(): ResourceRepository = ResourceRepository()

    @Singleton
    @Provides
    fun provideTwitterRepository(twitterApi: TwitterApi): TwitterRepository =
        TwitterRepository(twitterApi)
}
