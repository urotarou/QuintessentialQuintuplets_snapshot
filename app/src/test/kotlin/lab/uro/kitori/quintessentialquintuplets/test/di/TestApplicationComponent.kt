package lab.uro.kitori.quintessentialquintuplets.test.di

import dagger.Component
import lab.uro.kitori.quintessentialquintuplets.di.ApplicationComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        TestApplicationModule::class,
        TestUseCaseModule::class,
        TestRepositoryModule::class,
        TestApiModule::class
    ]
)
interface TestApplicationComponent : ApplicationComponent
