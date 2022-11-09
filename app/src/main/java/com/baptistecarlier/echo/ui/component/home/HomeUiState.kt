package com.baptistecarlier.echo.ui.component.home

import com.baptistecarlier.echo.domain.model.YoutubeChannelInfos
import com.baptistecarlier.echo.ui.model.LinkedInViewItem

data class HomeUiState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val isError: Boolean = false,
    val youtubeChannelInfos: YoutubeChannelInfos? = null,
    val list: List<LinkedInViewItem> = emptyList()
)
