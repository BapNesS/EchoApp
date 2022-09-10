package com.baptistecarlier.echo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baptistecarlier.echo.domain.interactor.content.FormatContentUc
import com.baptistecarlier.echo.domain.interactor.youtube.GetChannelInfoUc
import com.baptistecarlier.echo.domain.interactor.youtube.GetVideosUc
import com.baptistecarlier.echo.ui.model.LinkedInViewItem
import com.baptistecarlier.echo.ui.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenVM @Inject constructor(
    private val getVideosUc: GetVideosUc,
    private val formatContentUc: FormatContentUc,
    private val getChannelInfoUc: GetChannelInfoUc
) : ViewModel() {

    private var _state = MutableStateFlow(HomeState(isLoading = true))
    val state: StateFlow<HomeState> = _state

    init {
        retrieveData()
    }

    fun refresh() {
        _state.value = HomeState(
            isRefreshing = true,
            youtubeChannelInfos = _state.value.youtubeChannelInfos,
            list = _state.value.list
        )
        retrieveData()
    }

    private fun retrieveData() = viewModelScope.launch {
        val channelInfos = getChannelInfoUc()
        val list = getVideosUc(true)

        _state.value = if (channelInfos == null || list.isEmpty()) {
            HomeState(isError = true)
        } else {
            val itemList = list.map { LinkedInViewItem(formatContentUc(it), it) }
            HomeState(youtubeChannelInfos = channelInfos, list = itemList)
        }
    }
}
