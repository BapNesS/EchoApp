package com.baptistecarlier.echo.ui.component.videodetail

import com.baptistecarlier.echo.ui.model.LinkedInViewItem

data class VideoDetailUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val linkedInViewItem: LinkedInViewItem? = null
)