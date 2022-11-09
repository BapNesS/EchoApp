package com.baptistecarlier.echo.ui.component.settings

import com.baptistecarlier.echo.domain.model.LinkedInAccess
import com.baptistecarlier.echo.domain.model.YoutubeAccess

data class SettingsUiState(
    val done: Boolean = false,
    val youtubeAccess: YoutubeAccess? = null,
    val linkedInAccess: LinkedInAccess? = null
)