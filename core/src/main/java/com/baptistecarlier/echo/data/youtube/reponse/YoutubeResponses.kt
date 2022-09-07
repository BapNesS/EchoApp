package com.baptistecarlier.echo.data.youtube.reponse

import com.baptistecarlier.echo.data.network.NetworkResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YoutubeSearchResponse(
    @SerialName("kind")
    val kind: String,
    @SerialName("etag")
    val etag: String,
    @SerialName("regionCode")
    val regionCode: String,
    @SerialName("pageInfo")
    val pageInfo: YoutubePageInfoResponse,
    @SerialName("items")
    val items: List<YoutubeSearchItemsResponse>,
) : NetworkResponse

@Serializable
data class YoutubeDetailResponse(
    @SerialName("items")
    val items: List<YoutubeDetailItemsResponse>,
) : NetworkResponse

@Serializable
data class YoutubeDetailItemsResponse(
    @SerialName("id")
    val id: String,
    @SerialName("snippet")
    val snippet: YoutubeSearchItemSnippetResponse,
)

@Serializable
data class YoutubeSearchItemsResponse(
    @SerialName("kind")
    val kind: String,
    @SerialName("etag")
    val etag: String,
    @SerialName("id")
    val id: YoutubeSearchItemIdResponse,
    @SerialName("snippet")
    val snippet: YoutubeSearchItemSnippetResponse,
)

@Serializable
data class YoutubeSearchItemSnippetThumbnailResponse(
    @SerialName("url")
    val url: String,
    @SerialName("width")
    val width: Int,
    @SerialName("height")
    val height: Int,
)

@Serializable
data class YoutubeSearchItemSnippetThumbnailsResponse(
    @SerialName("default")
    val default: YoutubeSearchItemSnippetThumbnailResponse,
    @SerialName("medium")
    val medium: YoutubeSearchItemSnippetThumbnailResponse,
    @SerialName("high")
    val high: YoutubeSearchItemSnippetThumbnailResponse,
)

@Serializable
data class YoutubeSearchItemSnippetResponse(
    @SerialName("channelId")
    val channelId: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("publishedAt")
    val publishedAt: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("thumbnails")
    val thumbnails: YoutubeSearchItemSnippetThumbnailsResponse? = null,
    @SerialName("tags")
    val tags: List<String>? = null,
)

@Serializable
data class YoutubeSearchItemIdResponse(
    @SerialName("kind")
    val kind: String,
    @SerialName("videoId")
    val videoId: String,
)

@Serializable
data class YoutubePageInfoResponse(
    @SerialName("totalResults")
    val totalResults: Int,
    @SerialName("resultsPerPage")
    val resultsPerPage: Int,
)
