package com.baptistecarlier.echo.domain.interactor.youtube

import com.baptistecarlier.echo.data.network.NetworkResult
import com.baptistecarlier.echo.data.youtube.YoutubeRepository
import com.baptistecarlier.echo.domain.model.YoutubeChannelInfos
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetChannelInfoUc(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val youtubeRepository: YoutubeRepository,
    private val getYoutubeAccessUc: GetYoutubeAccessUc
) {

    suspend operator fun invoke(): YoutubeChannelInfos? = withContext(coroutineDispatcher) {
        val youtubeAccess = getYoutubeAccessUc()

        val channelInfosResponse = youtubeRepository.channelInfos(youtubeAccess)

        if (channelInfosResponse !is NetworkResult.Success) {
            return@withContext null
        }

        val item = channelInfosResponse.data.items?.first() ?: return@withContext null

        val profilePictureUrl: String = mutableListOf<String?>()
            .apply {
                add(item.snippet?.thumbnails?.high?.url.takeUnless { it == null })
                add(item.snippet?.thumbnails?.medium?.url.takeUnless { it == null })
                add(item.snippet?.thumbnails?.default?.url.takeUnless { it == null })
            }
            .firstOrNull() ?: ""

        return@withContext YoutubeChannelInfos(
            channelId = youtubeAccess.second,
            subscribers = item.statistics?.subscriberCount ?: 0,
            videos = item.statistics?.videoCount ?: 0,
            name = item.snippet?.title ?: "-",
            profilePictureUrl = profilePictureUrl
        )
    }
}
