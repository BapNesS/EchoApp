package com.baptistecarlier.echo.domain.interactor.youtube

import com.baptistecarlier.echo.data.network.NetworkResult
import com.baptistecarlier.echo.data.youtube.YoutubeRepository
import com.baptistecarlier.echo.domain.model.YoutubeVideo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetVideosUc(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val youtubeRepository: YoutubeRepository,
    private val getYoutubeAccessUc: GetYoutubeAccessUc,
) {

    suspend operator fun invoke(): List<YoutubeVideo> = withContext(coroutineDispatcher) {
        val youtubeAccess = getYoutubeAccessUc()

        val searchResponse = youtubeRepository.search(youtubeAccess)
        if (searchResponse !is NetworkResult.Success) {
            return@withContext emptyList()
        }
        val ids = searchResponse.data.items.map { it.id.videoId }

        val detailsResponse = youtubeRepository.details(youtubeAccess, ids)

        if (detailsResponse !is NetworkResult.Success) {
            return@withContext emptyList()
        }
        return@withContext searchResponse.data.items.map { searchVideo ->
            val tags = detailsResponse.data.items
                .firstOrNull { tagVideo ->
                    tagVideo.id == searchVideo.id.videoId
                }
                ?.snippet?.tags ?: emptyList()
            YoutubeVideo(
                title = searchVideo.snippet.title.orEmpty(),
                date = searchVideo.snippet.publishedAt.orEmpty(),
                url = "https://www.youtube.com/watch?v=" + searchVideo.id.videoId,
                thumbUrl = searchVideo.snippet.thumbnails?.high?.url.orEmpty(),
                tags = tags,
            )
        }.sortedByDescending { it.date }
    }
}
