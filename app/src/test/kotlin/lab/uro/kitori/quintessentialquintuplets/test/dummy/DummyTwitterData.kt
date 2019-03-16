package lab.uro.kitori.quintessentialquintuplets.test.dummy

import io.mockk.every
import io.mockk.mockk
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.entity.Tweet
import lab.uro.kitori.quintessentialquintuplets.data.resource.remote.entity.TwitterMedia
import lab.uro.kitori.quintessentialquintuplets.domain.value.Heroine

const val DUMMY_TWITTER_ACCESS_TOKEN = "dummy-access-token"
const val DUMMY_TWITTER_SCREEN_NAME = "5Hanayome"

val dummyTweets = arrayOf(
    createDummyTweet(1000, Heroine.ICHIKA, 100),
    createDummyTweet(1001, Heroine.ICHIKA, null),

    createDummyTweet(2000, Heroine.NINO, 200),
    createDummyTweet(2001, Heroine.NINO, 201),
    createDummyTweet(2002, Heroine.NINO, null),

    createDummyTweet(3000, Heroine.MIKU, 300),
    createDummyTweet(3001, Heroine.MIKU, 301),
    createDummyTweet(3002, Heroine.MIKU, 302),
    createDummyTweet(3003, Heroine.MIKU, null),

    createDummyTweet(4000, Heroine.YOTSUBA, 400),
    createDummyTweet(4001, Heroine.YOTSUBA, 401),
    createDummyTweet(4002, Heroine.YOTSUBA, 402),
    createDummyTweet(4003, Heroine.YOTSUBA, 403),
    createDummyTweet(4004, Heroine.YOTSUBA, null),

    createDummyTweet(5000, Heroine.ITSUKI, 500),
    createDummyTweet(5001, Heroine.ITSUKI, 501),
    createDummyTweet(5002, Heroine.ITSUKI, 502),
    createDummyTweet(5003, Heroine.ITSUKI, 503),
    createDummyTweet(5004, Heroine.ITSUKI, 504),
    createDummyTweet(5005, Heroine.ITSUKI, null),

    createDummyTweet(9000, null, 777),
    createDummyTweet(9001, null, null)
)

fun createDummyTweet(id: Long, heroine: Heroine?, tweetMediaId: Long?) = mockk<Tweet>().apply {
    every { this@apply.id } returns id

    heroine?.let {
        every { tweetText } returns "dummy tweet $tweetMediaId ${it.keyword}"
    } ?: run {
        every { tweetText } returns "可愛い五つ子とキャッキャウフフ"
    }

    tweetMediaId?.let {
        every { entities } returns Tweet.Entities(
            arrayOf(
                TwitterMedia(tweetMediaId, "https://pbs.twimg.com/media/D08-zkyUYAE750Z.jpg")
            )
        )
    } ?: run {
        every { entities } returns Tweet.Entities(arrayOf())
    }
}
