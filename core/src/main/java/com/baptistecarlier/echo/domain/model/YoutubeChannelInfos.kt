package com.baptistecarlier.echo.domain.model

data class YoutubeChannelInfos(
    val channelId: String,
    val subscribers: Int,
    val videos: Int,
    val name: String,
    val profilePictureUrl: String
)
