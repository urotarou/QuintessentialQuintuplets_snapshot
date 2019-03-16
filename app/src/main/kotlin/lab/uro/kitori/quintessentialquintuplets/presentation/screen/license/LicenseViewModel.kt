package lab.uro.kitori.quintessentialquintuplets.presentation.screen.license

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import lab.uro.kitori.quintessentialquintuplets.domain.usecase.LoadLicenseUseCase
import lab.uro.kitori.quintessentialquintuplets.extension.getInjector
import lab.uro.kitori.quintessentialquintuplets.presentation.item.LicenseItem
import javax.inject.Inject

class LicenseViewModel(
    app: Application
) : AndroidViewModel(app) {
    private val _licenses = MutableLiveData<Array<LicenseItem>>()
    val licenses: LiveData<Array<LicenseItem>> = _licenses

    @Inject
    lateinit var loadLicenseUseCase: LoadLicenseUseCase

    init {
        app.getInjector()
            .plus(LicenseModule())
            .inject(this)
    }

    fun getLicense() {
        _licenses.postValue(
            loadLicenseUseCase().map { LicenseItem(it) }.toTypedArray()
        )
    }
}
