package lab.uro.kitori.quintessentialquintuplets

import android.app.Application
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import timber.log.Timber

class QuintessentialQuintupletsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        Stetho.initializeWithDefaults(this)

        AndroidThreeTen.init(this)

        val config = BundledEmojiCompatConfig(this)
        config.setReplaceAll(true)
        EmojiCompat.init(config)
    }
}
