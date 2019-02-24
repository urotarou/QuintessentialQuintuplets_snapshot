package lab.uro.kitori.quintessentialquintuplets

import android.app.Application
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import com.jakewharton.threetenabp.AndroidThreeTen

class QuintessentialQuintupletsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        val config = BundledEmojiCompatConfig(this)
        config.setReplaceAll(true)
        EmojiCompat.init(config)
    }
}
