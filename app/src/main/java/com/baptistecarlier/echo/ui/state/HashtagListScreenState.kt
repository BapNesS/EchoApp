package com.baptistecarlier.echo.ui.state

import com.baptistecarlier.echo.ui.model.HashtagRatioItem

data class HashtagListScreenState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val list: List<HashtagRatioItem> = emptyList(),
    val nbVideo: Int = 0
)