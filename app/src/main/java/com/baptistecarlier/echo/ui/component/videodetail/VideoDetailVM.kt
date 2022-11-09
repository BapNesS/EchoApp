package com.baptistecarlier.echo.ui.component.videodetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baptistecarlier.echo.domain.interactor.content.FormatContentUc
import com.baptistecarlier.echo.domain.interactor.linkedin.PostOnLinkedInUc
import com.baptistecarlier.echo.domain.interactor.youtube.GetVideoUc
import com.baptistecarlier.echo.domain.model.YoutubeVideo
import com.baptistecarlier.echo.ui.model.LinkedInViewItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoDetailVM @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getVideoUc: GetVideoUc,
    private val formatContentUc: FormatContentUc,
    private val postOnLinkedInUc: PostOnLinkedInUc
) : ViewModel() {

    private var _state = MutableStateFlow(VideoDetailUiState(isLoading = true))
    val state: StateFlow<VideoDetailUiState> = _state

    init {
        savedStateHandle.get<String>("id")?.let { pId ->
            viewModelScope.launch {
                val cacheVideo = getVideoUc(pId)

                if (cacheVideo == null) {
                    _state.value = VideoDetailUiState(isError = true)
                    return@launch
                }

                val formatted = LinkedInViewItem(formatContentUc(cacheVideo), cacheVideo)
                _state.value = VideoDetailUiState(linkedInViewItem = formatted)
            }
        }
    }

    fun postOnLinkedIn(linkedInPostContent: String, youtubeVideo: YoutubeVideo) {
        viewModelScope.launch {
            postOnLinkedInUc(
                linkedInPostContent = linkedInPostContent,
                youtubeVideo = youtubeVideo
            )
        }
    }
}