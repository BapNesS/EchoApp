package android.content

import android.net.Uri

internal fun Context.share(content: String) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, content)
        type = "text/plain"
    }
    startActivity(Intent.createChooser(shareIntent, null))
}

internal fun Context.shareOnTwitter(content: String) = with(StringBuilder()) {
    append("https://twitter.com/intent/tweet")
    append("?")
    append("text=${Uri.encode(content)}")
    append("&")
    append("content=Echo")
    append("&")
    append("related=YouTube,YouTubeTrends,YTCreators")
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(this.toString()))
    this@shareOnTwitter.startActivity(browserIntent)
}

internal fun Context.openYoutubeStudio() {
    openWebsite("https://studio.youtube.com")
}

internal fun Context.openYoutubeChannel(channelId: String) {
    openWebsite("https://www.youtube.com/channel/$channelId")
}

internal fun Context.openWebsite(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}