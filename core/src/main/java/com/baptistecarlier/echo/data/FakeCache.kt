package com.baptistecarlier.echo.data

import com.baptistecarlier.echo.domain.model.YoutubeVideo

interface Cache {
    fun storeVideos(videos: List<YoutubeVideo>)
    fun getVideos(): List<YoutubeVideo>
    fun getVideo(videoId: String): YoutubeVideo?
}

/**
 * TODO Use a real database
 */
@Deprecated("It's a mock for now")
class FakeCache : Cache {

    private var videos: List<YoutubeVideo> = emptyList()

    override fun storeVideos(videos: List<YoutubeVideo>) {
        this.videos = videos
    }

    override fun getVideos(): List<YoutubeVideo> = videos

    override fun getVideo(videoId: String): YoutubeVideo? = videos.find { it.id == videoId }
}