package com.baptistecarlier.echo.data.youtube.reponse

import com.baptistecarlier.echo.data.network.NetworkResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YoutubeChannelResponse(
    @SerialName("kind")
    val kind: String? = null,
    @SerialName("pageInfo")
    val youtubePageInfoResponse: YoutubePageInfoResponse? = null,
    @SerialName("etag")
    val etag: String? = null,
    @SerialName("items")
    val items: List<YoutubeChannelItemResponse?>? = null
) : NetworkResponse

@Serializable
data class YoutubeChannelItemResponse(
    @SerialName("snippet")
    val snippet: YoutubeChannelItemSnippetResponse? = null,
    @SerialName("kind")
    val kind: String? = null,
    @SerialName("brandingSettings")
    val brandingSettings: YoutubeChannelItemBrandingSettingsResponse? = null,
    @SerialName("etag")
    val etag: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("statistics")
    val statistics: YoutubeChannelItemStatisticsResponse? = null
)

@Serializable
data class YoutubeChannelItemSnippetResponse(
    @SerialName("country")
    val country: String? = null,
    @SerialName("publishedAt")
    val publishedAt: String? = null,
    @SerialName("localized")
    val localized: YoutubeLocalizedResponse? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("thumbnails")
    val thumbnails: YoutubeThumbnailsResponse? = null
)

@Serializable
data class YoutubeLocalizedResponse(
    @SerialName("description")
    val description: String? = null,
    @SerialName("title")
    val title: String? = null
)

@Serializable
data class YoutubeThumbnailsResponse(
    @SerialName("default")
    val default: YoutubeThumbnailResponse? = null,
    @SerialName("high")
    val high: YoutubeThumbnailResponse? = null,
    @SerialName("medium")
    val medium: YoutubeThumbnailResponse? = null
)

@Serializable
data class YoutubeThumbnailResponse(
    @SerialName("width")
    val width: Int? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("height")
    val height: Int? = null
)

@Serializable
data class YoutubeChannelItemBrandingSettingsResponse(
    @SerialName("image")
    val image: YoutubeChannelItemBrandingSettingsImageResponse? = null,
    @SerialName("channel")
    val channel: YoutubeChannelItemBrandingSettingsChannelResponse? = null
)

@Serializable
data class YoutubeChannelItemBrandingSettingsImageResponse(
    @SerialName("bannerExternalUrl")
    val bannerExternalUrl: String? = null
)

@Serializable
data class YoutubeChannelItemBrandingSettingsChannelResponse(
    @SerialName("country")
    val country: String? = null,
    @SerialName("keywords")
    val keywords: String? = null,
    @SerialName("unsubscribedTrailer")
    val unsubscribedTrailer: String? = null,
    @SerialName("title")
    val title: String? = null
)

@Serializable
data class YoutubeChannelItemStatisticsResponse(
    @SerialName("videoCount")
    val videoCount: Int? = null,
    @SerialName("subscriberCount")
    val subscriberCount: Int? = null,
    @SerialName("viewCount")
    val viewCount: Int? = null,
    @SerialName("hiddenSubscriberCount")
    val hiddenSubscriberCount: Boolean? = null
)
