package lab.uro.kitori.quintessentialquintuplets.presentation.screen.searchresult

import dagger.Subcomponent

@Subcomponent(
    modules = [
        SearchResultModule::class
    ]
)
interface SearchResultComponent {
    fun inject(viewModel: SearchResultViewModel)
}
