package com.baptistecarlier.echo.ui.component.list

import com.baptistecarlier.echo.domain.model.YoutubeVideo

data class ListUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val list: List<YoutubeVideo> = emptyList()
)
