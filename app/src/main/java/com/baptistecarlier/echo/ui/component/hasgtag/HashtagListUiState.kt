package com.baptistecarlier.echo.ui.component.hasgtag

import com.baptistecarlier.echo.ui.model.HashtagRatioItem

data class HashtagListUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val list: List<HashtagRatioItem> = emptyList(),
    val nbVideo: Int = 0
)