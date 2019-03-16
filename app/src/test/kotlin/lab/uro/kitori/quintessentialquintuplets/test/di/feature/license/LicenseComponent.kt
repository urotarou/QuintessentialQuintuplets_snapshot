package lab.uro.kitori.quintessentialquintuplets.test.di.feature.license

import dagger.Subcomponent
import lab.uro.kitori.quintessentialquintuplets.presentation.screen.license.LicenseViewModel

@Subcomponent(
    modules = [
        LicenseModule::class
    ]
)
interface LicenseComponent {
    fun inject(viewModel: LicenseViewModel)
}
