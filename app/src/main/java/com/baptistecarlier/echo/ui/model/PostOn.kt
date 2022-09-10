package com.baptistecarlier.echo.ui.model

import androidx.annotation.StringRes
import com.baptistecarlier.echo.R

enum class PostOn(@StringRes val label: Int) {
    LinkedIn(R.string.linkedin),
    Twitter(R.string.twitter),
    JustText(R.string.share)
}