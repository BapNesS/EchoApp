package com.baptistecarlier.echo.domain.interactor.youtube

import com.baptistecarlier.echo.data.Cache
import com.baptistecarlier.echo.domain.model.YoutubeVideo
import kotlinx.coroutines.CoroutineDispatcher

class GetVideoUc(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val cache: Cache
) {

    suspend operator fun invoke(videoId: String): YoutubeVideo? = with(coroutineDispatcher) {
        return@with cache.getVideo(videoId)
    }
}