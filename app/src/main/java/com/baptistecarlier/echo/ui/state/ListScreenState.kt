package com.baptistecarlier.echo.ui.state

import com.baptistecarlier.echo.domain.model.YoutubeVideo

data class ListScreenState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val list: List<YoutubeVideo> = emptyList()
)
