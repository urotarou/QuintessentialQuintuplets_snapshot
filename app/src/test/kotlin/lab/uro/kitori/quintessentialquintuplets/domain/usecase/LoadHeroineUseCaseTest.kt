package lab.uro.kitori.quintessentialquintuplets.domain.usecase

import kotlinx.coroutines.runBlocking
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.entity.Tweet
import lab.uro.kitori.quintessentialquintuplets.domain.value.Heroine
import lab.uro.kitori.quintessentialquintuplets.test.dummy.dummyTweets
import lab.uro.kitori.quintessentialquintuplets.test.dummy.dummyTwitterRepository
import org.junit.jupiter.api.Assertions.assertTrue
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object LoadHeroineUseCaseTest : Spek({
    val mockTweets by memoized { dummyTweets }
    val mockRepository by memoized { dummyTwitterRepository }

    beforeGroup {
        assertTrue(mockTweets.size > 5)
        assertTrue(mockTweets.filter {
            it.tweetText?.contains(Heroine.ICHIKA.keyword) ?: false
        }.size > 1)
        assertTrue(mockTweets.filter {
            it.tweetText?.contains(Heroine.MIKU.keyword) ?: false
        }.size > 1)
        assertTrue(mockTweets.filter {
            it.tweetText?.contains(Heroine.NINO.keyword) ?: false
        }.size > 1)
        assertTrue(mockTweets.filter {
            it.tweetText?.contains(Heroine.YOTSUBA.keyword) ?: false
        }.size > 1)
        assertTrue(mockTweets.filter {
            it.tweetText?.contains(Heroine.ITSUKI.keyword) ?: false
        }.size > 1)
        assertTrue(mockTweets.filter {
            (it.entities?.media?.size ?: 0) > 0
        }.size > 5)
        assertTrue(mockTweets.filter {
            (it.entities?.media?.isEmpty() == true)
        }.size > 5)
    }

    Feature("ヒロインの読み込み") {
        Scenario("初回の読み込み") {
            val heroine = Heroine.NINO
            lateinit var useCase: LoadHeroineUseCase
            lateinit var result: List<Tweet>

            Given("ヒロイン読み込みユースケースを生成") {
                useCase = LoadHeroineUseCase(mockRepository)
            }

            When("二乃を指定して読み込み") {
                runBlocking {
                    result = useCase(heroine)
                }
            }

            Then("有効な二乃のツイートと数が等しい") {
                val test = mockTweets.filter {
                    (it.tweetText?.contains(heroine.keyword) ?: false) &&
                            ((it.entities?.media?.size ?: 0) > 0)
                }
                assertTrue(result.size == test.size)
            }
            Then("テキストに二乃のキーワードが入っている") {
                result.forEach {
                    assertTrue(it.tweetText?.contains(heroine.keyword) ?: false)
                }
            }
            Then("画像データを持っている") {
                result.forEach {
                    assertTrue((it.entities?.media?.size ?: 0) > 0)
                }
            }
        }

        Scenario("2回目以降の読み込み") {
            val heroine = Heroine.MIKU
            lateinit var useCase: LoadHeroineUseCase
            lateinit var result: List<Tweet>

            Given("ヒロイン読み込みユースケースを生成") {
                useCase = LoadHeroineUseCase(mockRepository)
            }

            When("三玖を指定して読み込み(ページ指定)") {
                runBlocking {
                    result = useCase(heroine, 1)
                }
            }

            Then("有効な三玖のツイートと数が等しい") {
                val test = mockTweets.filter {
                    (it.tweetText?.contains(heroine.keyword) ?: false) &&
                            ((it.entities?.media?.size ?: 0) > 0)
                }
                assertTrue(result.size == test.size)
            }
            Then("テキストに三玖のキーワードが入っている") {
                result.forEach {
                    assertTrue(it.tweetText?.contains(heroine.keyword) ?: false)
                }
            }
            Then("画像データを持っている") {
                result.forEach {
                    assertTrue((it.entities?.media?.size ?: 0) > 0)
                }
            }
        }
    }
})
