package lab.uro.kitori.quintessentialquintuplets.test.dummy

import android.app.Application
import io.mockk.every
import io.mockk.mockk
import lab.uro.kitori.quintessentialquintuplets.extension.getInjector
import lab.uro.kitori.quintessentialquintuplets.test.di.DaggerTestApplicationComponent
import lab.uro.kitori.quintessentialquintuplets.test.di.TestApplicationModule

val dummyApplication = mockk<Application>().apply {
    every { getInjector() } returns DaggerTestApplicationComponent
        .builder()
        .testApplicationModule(TestApplicationModule(this))
        .build()
}
