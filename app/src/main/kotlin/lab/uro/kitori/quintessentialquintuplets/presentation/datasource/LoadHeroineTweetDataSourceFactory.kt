package lab.uro.kitori.quintessentialquintuplets.presentation.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.entity.Tweet
import javax.inject.Inject

class LoadHeroineTweetDataSourceFactory @Inject constructor(
    private val loadHeroineTweetDataSource: LoadHeroineTweetDataSource
) : DataSource.Factory<Long, Tweet>() {
    val dataSource = MutableLiveData<LoadHeroineTweetDataSource>()

    override fun create(): DataSource<Long, Tweet> {
        dataSource.postValue(loadHeroineTweetDataSource)

        return loadHeroineTweetDataSource
    }
}
