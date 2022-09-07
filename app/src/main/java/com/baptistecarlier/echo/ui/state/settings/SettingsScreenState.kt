package com.baptistecarlier.echo.ui.state.settings

import com.baptistecarlier.echo.domain.model.LinkedInAccess
import com.baptistecarlier.echo.domain.model.YoutubeAccess

data class SettingsScreenState(
    val done: Boolean = false,
    val youtubeAccess: YoutubeAccess? = null,
    val linkedInAccess: LinkedInAccess? = null
)