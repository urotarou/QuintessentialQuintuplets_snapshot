package lab.uro.kitori.quintessentialquintuplets.test.di.feature.searchresult

import dagger.Module
import dagger.Provides
import lab.uro.kitori.quintessentialquintuplets.domain.usecase.LoadHeroineUseCase
import lab.uro.kitori.quintessentialquintuplets.domain.value.Heroine
import lab.uro.kitori.quintessentialquintuplets.presentation.datasource.LoadHeroineTweetDataSource
import lab.uro.kitori.quintessentialquintuplets.presentation.datasource.LoadHeroineTweetDataSourceFactory
import kotlin.coroutines.CoroutineContext

@Module
class SearchResultModule(
    private val coroutineContext: CoroutineContext,
    private val heroine: Heroine
) {
    @Provides
    fun provideLoadHeroineTweetDataSourceFactory(loadHeroineTweetDataSource: LoadHeroineTweetDataSource) =
        LoadHeroineTweetDataSourceFactory(loadHeroineTweetDataSource)

    @Provides
    fun provideLoadHeroineTweetDataSource(loadHeroineUseCase: LoadHeroineUseCase) =
        LoadHeroineTweetDataSource(coroutineContext, loadHeroineUseCase, heroine)
}
