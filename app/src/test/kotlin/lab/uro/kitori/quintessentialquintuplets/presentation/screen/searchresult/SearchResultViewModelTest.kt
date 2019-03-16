package lab.uro.kitori.quintessentialquintuplets.presentation.screen.searchresult

import androidx.paging.PagedList
import io.mockk.mockkStatic
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.entity.Tweet
import lab.uro.kitori.quintessentialquintuplets.domain.value.Heroine
import lab.uro.kitori.quintessentialquintuplets.domain.value.LoadingState
import lab.uro.kitori.quintessentialquintuplets.test.dummy.dummyApplication
import lab.uro.kitori.quintessentialquintuplets.test.extension.useCoroutines
import lab.uro.kitori.quintessentialquintuplets.test.extension.useLiveData
import org.junit.jupiter.api.Assertions.assertTrue
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object SearchResultViewModelTest : Spek({
    useLiveData()
    useCoroutines()

    mockkStatic("lab.uro.kitori.quintessentialquintuplets.extension.ApplicationExtensionKt")

    val mockApplication by memoized { dummyApplication }

    Feature("検索結果画面") {
        lateinit var viewModel: SearchResultViewModel
        lateinit var resultLoadState: LoadingState
        lateinit var resultTweets: PagedList<Tweet>

        Scenario("検索開始") {
            Given("ViewModel初期化") {
                viewModel = SearchResultViewModel(mockApplication, Heroine.MIKU)

                viewModel.tweets.observeForever {
                    resultTweets = it
                }
                viewModel.loadingState.observeForever {
                    resultLoadState = it
                }
            }

            When("Tweetデータ読み込み") {
                runBlocking {
                    viewModel.search()
                    delay(1_000)
                }
            }

            Then("読み込みデータの確認") {
                assertTrue(resultTweets.loadedCount == 3)

                resultTweets.forEach {
                    assertTrue(it?.tweetText?.contains(Heroine.MIKU.keyword) == true)
                    assertTrue((it?.entities?.media?.size ?: 0) > 0)
                }
            }
            Then("読み込み状態の確認") {
                assertTrue(resultLoadState == LoadingState.LOADING)
            }
        }
    }
})
