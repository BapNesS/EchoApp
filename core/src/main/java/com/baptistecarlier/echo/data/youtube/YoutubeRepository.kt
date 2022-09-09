package com.baptistecarlier.echo.data.youtube

import com.baptistecarlier.echo.data.network.NetworkResult
import com.baptistecarlier.echo.data.network.safeApiCall
import com.baptistecarlier.echo.data.youtube.reponse.YoutubeChannelResponse
import com.baptistecarlier.echo.data.youtube.reponse.YoutubeDetailResponse
import com.baptistecarlier.echo.data.youtube.reponse.YoutubeSearchResponse
import com.baptistecarlier.echo.domain.model.YoutubeAccess
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineDispatcher

class YoutubeRepository(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val client: HttpClient,
) {

    /**
     * TODO Pagination
     */
    suspend fun search(youtubeAccess: YoutubeAccess): NetworkResult<YoutubeSearchResponse> =
        safeApiCall(coroutineDispatcher) {
            val get = client
                .get(urlString = "youtube/v3/search") {
                    parameter("key", youtubeAccess.first)
                    parameter("channelId", youtubeAccess.second)
                    parameter("maxResults", "50")
                    // parameter("publishedAfter","2022-08-01T00%3A00%3A00Z")
                    parameter("type", "video")
                    parameter("part", "snippet")
                }
            get.body()
        }

    suspend fun details(
        youtubeAccess: YoutubeAccess,
        ids: List<String>
    ): NetworkResult<YoutubeDetailResponse> =
        safeApiCall(coroutineDispatcher) {
            val iIds = ids.joinToString(separator = ",")
            val get = client
                .get(urlString = "youtube/v3/videos") {
                    parameter("key", youtubeAccess.first)
                    parameter("id", iIds)
                    parameter("fields", "items(id,snippet(tags))")
                    parameter("part", "snippet")
                }
            get.body()
        }

    suspend fun channelInfos(
        youtubeAccess: YoutubeAccess
    ): NetworkResult<YoutubeChannelResponse> =
        safeApiCall(coroutineDispatcher) {
            val get = client
                .get(urlString = "youtube/v3/channels") {
                    parameter("key", youtubeAccess.first)
                    parameter("id", youtubeAccess.second)
                    parameter("part", "brandingSettings")
                    parameter("part", "snippet")
                    parameter("part", "statistics")
                }
            get.body()
        }
}
