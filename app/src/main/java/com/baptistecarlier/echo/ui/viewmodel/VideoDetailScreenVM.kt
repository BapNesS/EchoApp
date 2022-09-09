package com.baptistecarlier.echo.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baptistecarlier.echo.domain.interactor.content.FormatContentUc
import com.baptistecarlier.echo.domain.interactor.linkedin.PostOnLinkedInUc
import com.baptistecarlier.echo.domain.interactor.youtube.GetVideoUc
import com.baptistecarlier.echo.domain.model.YoutubeVideo
import com.baptistecarlier.echo.ui.model.LinkedInViewItem
import com.baptistecarlier.echo.ui.state.VideoDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoDetailScreenVM @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getVideoUc: GetVideoUc,
    private val formatContentUc: FormatContentUc,
    private val postOnLinkedInUc: PostOnLinkedInUc
) : ViewModel() {

    private var _state = MutableStateFlow(VideoDetailState(isLoading = true))
    val state: StateFlow<VideoDetailState> = _state

    init {
        savedStateHandle.get<String>("id")?.let { pId ->
            viewModelScope.launch {
                val cacheVideo = getVideoUc(pId)

                if (cacheVideo == null) {
                    _state.value = VideoDetailState(isError = true)
                    return@launch
                }

                val formatted = LinkedInViewItem(formatContentUc(cacheVideo), cacheVideo)
                _state.value = VideoDetailState(linkedInViewItem = formatted)
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