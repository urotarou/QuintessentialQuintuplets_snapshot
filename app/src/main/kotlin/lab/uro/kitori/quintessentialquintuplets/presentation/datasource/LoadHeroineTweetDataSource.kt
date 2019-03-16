package lab.uro.kitori.quintessentialquintuplets.presentation.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.entity.Tweet
import lab.uro.kitori.quintessentialquintuplets.domain.usecase.LoadHeroineUseCase
import lab.uro.kitori.quintessentialquintuplets.domain.value.Heroine
import lab.uro.kitori.quintessentialquintuplets.domain.value.LoadingState
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class LoadHeroineTweetDataSource(
    private val coroutineContext: CoroutineContext,
    private val loadHeroineUseCase: LoadHeroineUseCase,
    private val heroine: Heroine
) : PageKeyedDataSource<Long, Tweet>() {
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> = _loadingState

    override fun loadInitial(
        params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, Tweet>
    ) {
        CoroutineScope(coroutineContext).launch {
            runCatching {
                loadHeroineUseCase(heroine)
            }.fold({
                val nextPageKey = it.lastOrNull()?.id?.let { id -> id - 1 }

                if (it.isNotEmpty() && nextPageKey != null) {
                    _loadingState.postValue(LoadingState.LOADING)
                } else {
                    _loadingState.postValue(LoadingState.LOADED)
                }

                callback.onResult(it, null, nextPageKey)
            }, {
                Timber.e(it)
            })
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Tweet>) {
        // nothing to do
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Tweet>) {
        CoroutineScope(coroutineContext).launch {
            runCatching {
                loadHeroineUseCase(heroine, params.key)
            }.fold({
                val nextPageKey = it.lastOrNull()?.id?.let { id -> id - 1 }

                if (it.isNotEmpty() && nextPageKey != null) {
                    _loadingState.postValue(LoadingState.LOADING)
                } else {
                    _loadingState.postValue(LoadingState.LOADED)
                }

                callback.onResult(it, nextPageKey)
            }, {
                Timber.e(it)
            })
        }
    }
}
