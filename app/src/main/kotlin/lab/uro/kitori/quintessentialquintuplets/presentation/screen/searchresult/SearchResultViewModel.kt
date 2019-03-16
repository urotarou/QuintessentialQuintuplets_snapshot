package lab.uro.kitori.quintessentialquintuplets.presentation.screen.searchresult

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.entity.Tweet
import lab.uro.kitori.quintessentialquintuplets.domain.value.Heroine
import lab.uro.kitori.quintessentialquintuplets.domain.value.LoadingState
import lab.uro.kitori.quintessentialquintuplets.extension.attach
import lab.uro.kitori.quintessentialquintuplets.extension.getInjector
import lab.uro.kitori.quintessentialquintuplets.presentation.datasource.LoadHeroineTweetDataSourceFactory
import javax.inject.Inject

class SearchResultViewModel(
    app: Application,
    heroine: Heroine
) : AndroidViewModel(app), CoroutineScope {
    private val job = Job()
    override val coroutineContext = Dispatchers.Default + job

    private val _tweets = MediatorLiveData<PagedList<Tweet>>()
    val tweets: LiveData<PagedList<Tweet>> = _tweets
    private val _loadingState = MediatorLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> = _loadingState

    @Inject
    lateinit var dataSourceFactory: LoadHeroineTweetDataSourceFactory

    init {
        app.getInjector()
            .plus(SearchResultModule(coroutineContext, heroine))
            .inject(this)
    }

    override fun onCleared() {
        job.cancel()

        super.onCleared()
    }

    fun search() {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setPrefetchDistance(5)
            .build()

        _tweets.attach(LivePagedListBuilder(dataSourceFactory, config).build())
        _loadingState.attach(Transformations.switchMap(dataSourceFactory.dataSource) { it.loadingState })
    }
}
