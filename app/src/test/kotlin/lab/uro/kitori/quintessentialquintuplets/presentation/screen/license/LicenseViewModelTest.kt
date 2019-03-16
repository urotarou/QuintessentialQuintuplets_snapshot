package lab.uro.kitori.quintessentialquintuplets.presentation.screen.license

import io.mockk.mockkStatic
import lab.uro.kitori.quintessentialquintuplets.data.resource.local.entity.License
import lab.uro.kitori.quintessentialquintuplets.presentation.item.LicenseItem
import lab.uro.kitori.quintessentialquintuplets.test.dummy.dummyApplication
import lab.uro.kitori.quintessentialquintuplets.test.extension.useLiveData
import org.junit.jupiter.api.Assertions.assertTrue
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

object LicenseViewModelTest : Spek({
    useLiveData()

    mockkStatic("lab.uro.kitori.quintessentialquintuplets.extension.ApplicationExtensionKt")

    val mockApplication by memoized { dummyApplication }

    Feature("ライセンス一覧画面") {
        lateinit var viewModel: LicenseViewModel
        lateinit var resultLicenses: Array<LicenseItem>

        Scenario("表示開始") {
            Given("ViewModel初期化") {
                viewModel = LicenseViewModel(mockApplication)

                viewModel.licenses.observeForever {
                    resultLicenses = it
                }
            }

            When("ライセンスデータ読み込み") {
                viewModel.getLicense()
            }

            Then("読み込みデータの確認") {
                assertTrue(resultLicenses.size == 13)

                resultLicenses.forEach {
                    it::class.memberProperties.forEach { property ->
                        property.isAccessible = true

                        if (property.name == "license") {
                            @Suppress("UNCHECKED_CAST")
                            val licenseItemValue =
                                (property as KProperty1<LicenseItem, License>).get(it)

                            assertTrue(licenseItemValue.libraryName > 0)
                            assertTrue(licenseItemValue.licenseText > 0)
                        }
                    }
                }
            }
        }
    }
})
