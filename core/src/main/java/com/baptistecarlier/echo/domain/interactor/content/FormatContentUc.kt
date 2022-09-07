package com.baptistecarlier.echo.domain.interactor.content

import com.baptistecarlier.echo.domain.model.YoutubeVideo

class FormatContentUc {

    operator fun invoke(youtubeVideo: YoutubeVideo): String {
        val formattedTags = youtubeVideo.tags.joinToString(
            transform = { "#${it.replace(" ", "")}" },
            separator = " "
        )
        mutableListOf(
            "\uD83D\uDEA8 NOUVELLE VIDÉO",
            youtubeVideo.title,
            "\uD83D\uDCCC ${youtubeVideo.url}"
        )
            .apply {
                if (formattedTags.isNotBlank()) {
                    add(formattedTags)
                }
                return joinToString(separator = "\n\n")
            }
    }
}
