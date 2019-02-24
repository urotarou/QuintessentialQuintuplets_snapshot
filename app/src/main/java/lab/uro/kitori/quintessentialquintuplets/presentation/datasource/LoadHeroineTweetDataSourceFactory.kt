package lab.uro.kitori.quintessentialquintuplets.presentation.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.response.Tweet
import lab.uro.kitori.quintessentialquintuplets.domain.value.Heroine

class LoadHeroineTweetDataSourceFactory(
        private val coroutineScope: CoroutineScope,
        private val heroine: Heroine
) : DataSource.Factory<Long, Tweet>() {
    val dataSource = MutableLiveData<LoadHeroineTweetDataSource>()

    override fun create(): DataSource<Long, Tweet> {
        val source = LoadHeroineTweetDataSource(coroutineScope, heroine)
        dataSource.postValue(source)

        return source
    }
}
