package lab.uro.kitori.quintessentialquintuplets.presentation.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.response.Tweet
import lab.uro.kitori.quintessentialquintuplets.domain.usecase.LoadHeroineUseCase
import lab.uro.kitori.quintessentialquintuplets.domain.value.Heroine
import lab.uro.kitori.quintessentialquintuplets.domain.value.LoadingState
import timber.log.Timber

class LoadHeroineTweetDataSource(
        private val coroutineScope: CoroutineScope,
        private val heroine: Heroine
) : PageKeyedDataSource<Long, Tweet>() {
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> = _loadingState

    private val loadHeroineUseCase = LoadHeroineUseCase(coroutineScope)

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, Tweet>) {
        coroutineScope.launch {
            runCatching {
                loadHeroineUseCase.loadHeroine()
            }.fold({
                val filteredTweets = it.filter { tweet ->
                    (tweet.tweetText?.contains(heroine.keyword) ?: false) &&
                            ((tweet.entities?.media?.size ?: 0) > 0)
                }
                val nextPageKey = it.lastOrNull()?.id?.let { id -> id - 1 }

                if (filteredTweets.isNotEmpty() && nextPageKey != null) {
                    _loadingState.postValue(LoadingState.LOADING)
                } else {
                    _loadingState.postValue(LoadingState.LOADED)
                }

                callback.onResult(filteredTweets, null, nextPageKey)
            }, {
                Timber.e(it)
            })
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Tweet>) {
        // nothing to do
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Tweet>) {
        coroutineScope.launch {
            runCatching {
                loadHeroineUseCase.loadHeroine(params.key)
            }.fold({
                val filteredTweets = it.filter { tweet ->
                    (tweet.tweetText?.contains(heroine.keyword) ?: false) &&
                            ((tweet.entities?.media?.size ?: 0) > 0)
                }
                val nextPageKey = it.lastOrNull()?.id?.let { id -> id - 1 }

                if (filteredTweets.isNotEmpty() && nextPageKey != null) {
                    _loadingState.postValue(LoadingState.LOADING)
                } else {
                    _loadingState.postValue(LoadingState.LOADED)
                }

                callback.onResult(filteredTweets, nextPageKey)
            }, {
                Timber.e(it)
            })
        }
    }
}
