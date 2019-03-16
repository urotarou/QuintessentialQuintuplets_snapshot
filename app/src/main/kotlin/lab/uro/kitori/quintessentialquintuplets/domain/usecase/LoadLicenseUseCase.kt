package lab.uro.kitori.quintessentialquintuplets.domain.usecase

import lab.uro.kitori.quintessentialquintuplets.data.repository.ResourceRepository
import javax.inject.Inject

class LoadLicenseUseCase @Inject constructor(
    private val repository: ResourceRepository
) {
    operator fun invoke() = repository.loadLicenseResources()
}
