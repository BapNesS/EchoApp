package com.baptistecarlier.echo.ui.model

import androidx.annotation.StringRes
import com.baptistecarlier.echo.R

enum class PostOn(val requestConfirm: Boolean, @StringRes val label: Int) {
    LinkedIn(true, R.string.linkedin),
    Twitter(false, R.string.twitter),
    JustText(false, R.string.share)
}