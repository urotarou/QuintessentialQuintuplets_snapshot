package lab.uro.kitori.quintessentialquintuplets.extension

import android.content.Context
import android.content.Intent

fun Context.share(shareTitle: String = "", subject: String = "", text: String = "") {
    val intent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }

    startActivity(Intent.createChooser(intent, shareTitle))
}
