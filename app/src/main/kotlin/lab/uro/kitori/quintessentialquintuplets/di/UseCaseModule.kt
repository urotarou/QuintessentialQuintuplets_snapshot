package lab.uro.kitori.quintessentialquintuplets.di

import dagger.Module
import dagger.Provides
import lab.uro.kitori.quintessentialquintuplets.data.repository.ResourceRepository
import lab.uro.kitori.quintessentialquintuplets.data.repository.TwitterRepository
import lab.uro.kitori.quintessentialquintuplets.domain.usecase.LoadHeroineUseCase
import lab.uro.kitori.quintessentialquintuplets.domain.usecase.LoadLicenseUseCase

@Module
class UseCaseModule {
    @Provides
    fun provideLoadHeroineUseCase(twitterRepository: TwitterRepository): LoadHeroineUseCase =
        LoadHeroineUseCase(twitterRepository)

    @Provides
    fun provideLoadLicenseUseCase(resourceRepository: ResourceRepository): LoadLicenseUseCase =
        LoadLicenseUseCase(resourceRepository)
}
