package com.baptistecarlier.echo.domain.model

import android.os.Build
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY

class YoutubeVideo private constructor(
    val title: String,
    val date: String,
    val url: String,
    val thumbUrl: String,
    val tags: List<String> = emptyList(),
) {
    companion object {
        operator fun invoke(
            title: String,
            date: String,
            url: String,
            thumbUrl: String,
            tags: List<String> = emptyList(),
        ) = YoutubeVideo(decode(title), date, decode(url), decode(thumbUrl), tags.map { decode(it) })

        private fun decode(htmlString: String): String {
            val decoded = if (Build.VERSION.SDK_INT >= 24) {
                Html.fromHtml(htmlString, FROM_HTML_MODE_LEGACY)
            } else {
                Html.fromHtml(htmlString)
            }
            return decoded.toString()
        }
    }
}
