package lab.uro.kitori.quintessentialquintuplets.test.dummy

import io.mockk.coEvery
import io.mockk.mockk
import lab.uro.kitori.quintessentialquintuplets.data.repository.TwitterRepository

val dummyTwitterRepository = mockk<TwitterRepository>().apply {
    coEvery { getTimeline(DUMMY_TWITTER_SCREEN_NAME, any()) } returns dummyTweets
}
