package lab.uro.kitori.quintessentialquintuplets.presentation.screen.searchresult

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.response.Tweet
import lab.uro.kitori.quintessentialquintuplets.domain.value.Heroine
import lab.uro.kitori.quintessentialquintuplets.domain.value.LoadingState
import lab.uro.kitori.quintessentialquintuplets.presentation.datasource.LoadHeroineTweetDataSourceFactory

class SearchResultViewModel(
        app: Application,
        heroine: Heroine
) : AndroidViewModel(app), CoroutineScope {
    private val job = Job()
    override val coroutineContext = Dispatchers.Default + job
    private val coroutineScope = CoroutineScope(coroutineContext)

    val tweets: LiveData<PagedList<Tweet>>
    val loadingState: LiveData<LoadingState>

    init {
        val factory = LoadHeroineTweetDataSourceFactory(coroutineScope, heroine)
        val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setPrefetchDistance(5)
                .build()

        tweets = LivePagedListBuilder(factory, config).build()
        loadingState = Transformations.switchMap(factory.dataSource) { it.loadingState }
    }

    override fun onCleared() {
        job.cancel()

        super.onCleared()
    }
}
