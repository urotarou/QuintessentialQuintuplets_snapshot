package lab.uro.kitori.quintessentialquintuplets.domain.value

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import lab.uro.kitori.quintessentialquintuplets.R

enum class Heroine(
    @StringRes val nameResId: Int,
    @ColorRes val colorResId: Int,
    val keyword: String
) {
    ICHIKA(R.string.heroine_ichika, R.color.color_ichika, "㊊"),
    NINO(R.string.heroine_nino, R.color.color_nino, "㊋"),
    MIKU(R.string.heroine_miku, R.color.color_miku, "㊌"),
    YOTSUBA(R.string.heroine_yotsuba, R.color.color_yotsuba, "㊍"),
    ITSUKI(R.string.heroine_itsuki, R.color.color_itsuki, "㊎")
}
