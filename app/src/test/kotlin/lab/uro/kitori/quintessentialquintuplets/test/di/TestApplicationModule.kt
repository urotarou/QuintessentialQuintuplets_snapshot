package lab.uro.kitori.quintessentialquintuplets.test.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestApplicationModule(
    private val context: Context
) {
    @Singleton
    @Provides
    fun provideContext() = context
}
