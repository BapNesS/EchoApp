package com.baptistecarlier.echo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baptistecarlier.echo.domain.interactor.content.FormatContentUc
import com.baptistecarlier.echo.domain.interactor.linkedin.PostOnLinkedInUc
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
    private val getChannelInfoUc: GetChannelInfoUc,
    private val postOnLinkedInUc: PostOnLinkedInUc,
) : ViewModel() {

    private var _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        refreshList()
    }

    fun refreshList() {
        _state.value = HomeState(isLoading = true, list = _state.value.list)
        viewModelScope.launch {
            val channelInfos = getChannelInfoUc()
            val list = getVideosUc(true)
            if (channelInfos == null) {
                _state.value = HomeState(isError = true)
            } else if (list.isEmpty()) {
                _state.value = HomeState(isError = true)
            } else {
                val itemList = list.map { LinkedInViewItem(formatContentUc(it), it) }
                _state.value = HomeState(youtubeChannelInfos = channelInfos, list = itemList)
            }
        }
    }
}
