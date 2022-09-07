package com.baptistecarlier.echo.data.linkedin.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShareRequest(
    @SerialName("content")
    val content: ContentRequest,
    @SerialName("owner")
    val owner: String,
    @SerialName("text")
    val text: TextRequest,
) {
    companion object {
        fun build(
            linkedInId: String,
            linkedInPostContent: String,
            youtubeVideoTitle: String,
            youtubeVideoLink: String,
            youtubeThumbUrl: String,
        ): ShareRequest {
            return ShareRequest(
                content = ContentRequest(
                    title = youtubeVideoTitle,
                    contentEntities = listOf(
                        ContentEntitiesRequest(
                            entityLocation = youtubeVideoLink,
                            thumbnails = listOf(ThumbnailsRequest(resolvedUrl = youtubeThumbUrl))
                        )
                    )
                ),
                owner = "urn:li:person:$linkedInId",
                text = TextRequest(linkedInPostContent),
            )
        }
    }
}

@Serializable
data class ContentRequest(
    @SerialName("contentEntities")
    val contentEntities: List<ContentEntitiesRequest>,
    @SerialName("title")
    val title: String,
)

@Serializable
data class ContentEntitiesRequest(
    @SerialName("entityLocation")
    val entityLocation: String,
    @SerialName("thumbnails")
    val thumbnails: List<ThumbnailsRequest>,
)

@Serializable
data class ThumbnailsRequest(
    @SerialName("resolvedUrl")
    val resolvedUrl: String,
)

@Serializable
data class TextRequest(
    @SerialName("text")
    val text: String,
)
