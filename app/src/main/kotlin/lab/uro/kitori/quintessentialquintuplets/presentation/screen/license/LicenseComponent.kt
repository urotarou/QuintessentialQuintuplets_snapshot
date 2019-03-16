package lab.uro.kitori.quintessentialquintuplets.presentation.screen.license

import dagger.Subcomponent

@Subcomponent(
    modules = [
        LicenseModule::class
    ]
)
interface LicenseComponent {
    fun inject(viewModel: LicenseViewModel)
}
