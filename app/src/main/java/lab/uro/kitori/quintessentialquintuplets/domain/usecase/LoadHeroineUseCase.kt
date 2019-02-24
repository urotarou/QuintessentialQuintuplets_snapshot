package lab.uro.kitori.quintessentialquintuplets.domain.usecase

import kotlinx.coroutines.CoroutineScope
import lab.uro.kitori.quintessentialquintuplets.data.repository.TwitterRepository

class LoadHeroineUseCase(
        coroutineScope: CoroutineScope
) {
    companion object {
        private const val TWITTER_SCREEN_NAME = "5Hanayome"
    }

    private val repository = TwitterRepository(coroutineScope)

    suspend fun loadHeroine() = repository.getTimeline(TWITTER_SCREEN_NAME)

    suspend fun loadHeroine(key: Long?) = repository.getTimeline(TWITTER_SCREEN_NAME, key)
}
