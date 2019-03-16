package lab.uro.kitori.quintessentialquintuplets.domain.usecase

import lab.uro.kitori.quintessentialquintuplets.data.repository.TwitterRepository
import lab.uro.kitori.quintessentialquintuplets.domain.value.Heroine
import javax.inject.Inject

class LoadHeroineUseCase @Inject constructor(
    private val repository: TwitterRepository
) {
    companion object {
        private const val TWITTER_SCREEN_NAME = "5Hanayome"
    }

    suspend operator fun invoke(heroine: Heroine, key: Long? = null) =
        repository.getTimeline(TWITTER_SCREEN_NAME, key)
            .filter { tweet ->
                (tweet.tweetText?.contains(heroine.keyword) ?: false) &&
                        ((tweet.entities?.media?.size ?: 0) > 0)
            }
}
