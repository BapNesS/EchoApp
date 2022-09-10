package com.baptistecarlier.echo.ui.model

import androidx.annotation.StringRes
import com.baptistecarlier.echo.R

enum class HashtagItemSort(@StringRes val label: Int) {
    Name(R.string.sort_by_name),
    Ratio(R.string.sort_by_ratio)
}