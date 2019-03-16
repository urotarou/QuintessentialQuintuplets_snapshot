package lab.uro.kitori.quintessentialquintuplets.domain.usecase

import lab.uro.kitori.quintessentialquintuplets.data.repository.ResourceRepository
import lab.uro.kitori.quintessentialquintuplets.data.resource.local.entity.License
import org.junit.jupiter.api.Assertions.assertTrue
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object LoadLicenseUseCaseTest : Spek({
    val mockRepository by memoized { ResourceRepository() }

    Feature("ライセンスのリソースの読み込み") {
        lateinit var useCase: LoadLicenseUseCase
        lateinit var result: Array<License>

        Scenario("ライセンスの読み込み") {
            Given("ライセンス読み込みユースケースを作成") {
                useCase = LoadLicenseUseCase(mockRepository)
            }

            When("ライセンス読み込み") {
                result = useCase()
            }

            Then("ライセンスの件数が合っている") {
                assertTrue(result.size == 13)
            }
            Then("ライセンスの名称を持っている") {
                result.forEach {
                    assertTrue(it.libraryName > 0)
                }
            }
            Then("ライセンスのリソースを持っている") {
                result.forEach {
                    assertTrue(it.licenseText > 0)
                }
            }
        }
    }
})
