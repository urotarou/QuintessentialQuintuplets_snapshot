package lab.uro.kitori.quintessentialquintuplets.di

import dagger.Component
import lab.uro.kitori.quintessentialquintuplets.presentation.screen.license.LicenseComponent
import lab.uro.kitori.quintessentialquintuplets.presentation.screen.license.LicenseModule
import lab.uro.kitori.quintessentialquintuplets.presentation.screen.searchresult.SearchResultComponent
import lab.uro.kitori.quintessentialquintuplets.presentation.screen.searchresult.SearchResultModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        UseCaseModule::class,
        RepositoryModule::class,
        ApiModule::class
    ]
)
interface ApplicationComponent {
    fun plus(module: SearchResultModule): SearchResultComponent

    fun plus(module: LicenseModule): LicenseComponent
}
