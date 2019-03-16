package lab.uro.kitori.quintessentialquintuplets.extension

import android.content.Context
import lab.uro.kitori.quintessentialquintuplets.R
import org.threeten.bp.LocalDateTime
import org.threeten.bp.Month
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoUnit
import java.util.*

fun String.toTweetDateTime(context: Context): String {
    // createdAt= "曜日 月 日 時:分:秒 +0000 年"
    val dateTime = split(" ")
    if (dateTime.isNullOrEmpty() || dateTime.count() != 6) return ""

    val time = dateTime[3].split(":")
    if (time.count() != 3) return ""

    val year = dateTime[5].toInt()
    val month = Month.values().find { it.name.startsWith(dateTime[1], true) }?.value ?: 1
    val day = dateTime[2].toInt()
    val hour = time[0].toInt()
    val minute = time[1].toInt()
    val second = time[2].toInt()

    val currentDateTime = LocalDateTime.now()
    val tweetDateTime = LocalDateTime.of(year, month, day, hour, minute, second)
        .plusSeconds(ZonedDateTime.now().offset.totalSeconds.toLong())

    val betweenDays = ChronoUnit.DAYS.between(tweetDateTime, currentDateTime)
    if (betweenDays > 0) return tweetDateTime.format(
        DateTimeFormatter.ofPattern(
            "yyyy/MM/dd", Locale.getDefault()
        )
    )

    val betweenHours = ChronoUnit.HOURS.between(tweetDateTime, currentDateTime)
    if (betweenHours > 0) return context.getString(R.string.twitter_date_time_hour, betweenHours)

    val betweenMinutes = ChronoUnit.MINUTES.between(tweetDateTime, currentDateTime)
    if (betweenMinutes > 0) return context.getString(
        R.string.twitter_date_time_minutes, betweenMinutes
    )

    val betweenSeconds = ChronoUnit.SECONDS.between(tweetDateTime, currentDateTime)
    if (betweenSeconds >= 10) return context.getString(
        R.string.twitter_date_time_seconds, betweenSeconds
    )

    return context.getString(R.string.twitter_date_time_now)
}
