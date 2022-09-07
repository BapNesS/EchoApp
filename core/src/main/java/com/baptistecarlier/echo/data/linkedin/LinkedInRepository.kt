package com.baptistecarlier.echo.data.linkedin

import com.baptistecarlier.echo.data.linkedin.request.ShareRequest
import com.baptistecarlier.echo.data.linkedin.response.ShareResponse
import com.baptistecarlier.echo.data.network.NetworkResult
import com.baptistecarlier.echo.data.network.safeApiCall
import com.baptistecarlier.echo.domain.model.LinkedInAccess
import com.baptistecarlier.echo.domain.model.YoutubeVideo
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineDispatcher

class LinkedInRepository(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val client: HttpClient,
) {

    suspend fun post(
        linkedInAccess: LinkedInAccess,
        linkedInPostContent: String,
        youtubeVideo: YoutubeVideo
    ): NetworkResult<ShareResponse> = safeApiCall(coroutineDispatcher) {
        val body = ShareRequest.build(
            linkedInAccess.second,
            linkedInPostContent, youtubeVideo.title, youtubeVideo.url, youtubeVideo.thumbUrl
        )
        val post = client
            .post(urlString = "v2/shares") {
                contentType(ContentType.Application.Json)
                header("Authorization", "Bearer ${linkedInAccess.first}")
                setBody(body)
            }
        post.body()
    }
}
