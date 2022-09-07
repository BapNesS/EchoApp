package com.baptistecarlier.echo.ui.viewmodel.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baptistecarlier.echo.domain.interactor.content.FormatContentUc
import com.baptistecarlier.echo.domain.interactor.linkedin.PostOnLinkedInUc
import com.baptistecarlier.echo.domain.interactor.youtube.GetVideosUc
import com.baptistecarlier.echo.domain.model.YoutubeVideo
import com.baptistecarlier.echo.ui.state.list.ListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal typealias LinkedInViewItem = Pair<String, YoutubeVideo>

@HiltViewModel
class ListScreenVM @Inject constructor(
    private val getVideosUc: GetVideosUc,
    private val formatContentUc: FormatContentUc,
    private val postOnLinkedInUc: PostOnLinkedInUc,
) : ViewModel() {

    private var _state = MutableStateFlow(ListScreenState())
    val state: StateFlow<ListScreenState> = _state

    init {
        refreshList()
    }

    fun refreshList() {
        _state.value = ListScreenState(isLoading = true, list = _state.value.list)
        viewModelScope.launch {
            val list = getVideosUc()
            if (list.isEmpty()) {
                _state.value = ListScreenState(isError = true)
            } else {
                val itemList = list.map { LinkedInViewItem(formatContentUc(it), it) }
                _state.value = ListScreenState(list = itemList)
            }
        }
    }

    fun post(linkedInPostContent: String, youtubeVideo: YoutubeVideo) {
        viewModelScope.launch {
            postOnLinkedInUc(
                linkedInPostContent = linkedInPostContent,
                youtubeVideo = youtubeVideo
            )
        }
    }
}
