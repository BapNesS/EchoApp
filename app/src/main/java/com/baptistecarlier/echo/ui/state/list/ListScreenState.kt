package com.baptistecarlier.echo.ui.state.list

import com.baptistecarlier.echo.ui.viewmodel.list.LinkedInViewItem

data class ListScreenState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val list: List<LinkedInViewItem> = emptyList()
)
