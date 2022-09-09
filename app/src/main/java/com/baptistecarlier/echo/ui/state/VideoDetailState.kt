package com.baptistecarlier.echo.ui.state

import com.baptistecarlier.echo.ui.model.LinkedInViewItem

data class VideoDetailState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val linkedInViewItem: LinkedInViewItem? = null
)