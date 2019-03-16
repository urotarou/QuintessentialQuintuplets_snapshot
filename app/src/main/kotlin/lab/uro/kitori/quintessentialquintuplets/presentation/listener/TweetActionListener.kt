package lab.uro.kitori.quintessentialquintuplets.presentation.listener

interface TweetActionListener {
    fun onUserClick(userName: String)

    fun onTextClick(tweetUrl: String)

    fun onImageClick(imageUrl: String)

    fun onImageLongClick(tweetUrl: String, imageUrl: String): Boolean
}
