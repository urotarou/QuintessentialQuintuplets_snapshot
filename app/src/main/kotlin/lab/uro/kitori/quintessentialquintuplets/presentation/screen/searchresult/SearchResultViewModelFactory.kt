package lab.uro.kitori.quintessentialquintuplets.presentation.screen.searchresult

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lab.uro.kitori.quintessentialquintuplets.domain.value.Heroine

class SearchResultViewModelFactory(
    private val app: Application,
    private val heroine: Heroine
) : ViewModelProvider.AndroidViewModelFactory(app) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return SearchResultViewModel(app, heroine) as T
    }
}
