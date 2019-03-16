package lab.uro.kitori.quintessentialquintuplets

import android.app.Application
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import lab.uro.kitori.quintessentialquintuplets.di.ApplicationComponent
import lab.uro.kitori.quintessentialquintuplets.di.ApplicationModule
import lab.uro.kitori.quintessentialquintuplets.di.DaggerApplicationComponent
import timber.log.Timber

class QuintessentialQuintupletsApplication : Application() {
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()

        Timber.plant(Timber.DebugTree())
        Stetho.initializeWithDefaults(this)

        AndroidThreeTen.init(this)

        val config = BundledEmojiCompatConfig(this)
        config.setReplaceAll(true)
        EmojiCompat.init(config)
    }
}
