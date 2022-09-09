package com.baptistecarlier.echo.ui.state

import com.baptistecarlier.echo.domain.model.YoutubeChannelInfos
import com.baptistecarlier.echo.ui.model.LinkedInViewItem

data class HomeState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val youtubeChannelInfos: YoutubeChannelInfos? = null,
    val list: List<LinkedInViewItem> = emptyList()
)
