package lab.uro.kitori.quintessentialquintuplets.test.di.feature.searchresult

import dagger.Subcomponent
import lab.uro.kitori.quintessentialquintuplets.presentation.screen.searchresult.SearchResultViewModel

@Subcomponent(
    modules = [
        SearchResultModule::class
    ]
)
interface SearchResultComponent {
    fun inject(viewModel: SearchResultViewModel)
}
