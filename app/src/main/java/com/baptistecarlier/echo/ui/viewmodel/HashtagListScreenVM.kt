package com.baptistecarlier.echo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baptistecarlier.echo.domain.interactor.youtube.GetVideosUc
import com.baptistecarlier.echo.domain.model.YoutubeVideo
import com.baptistecarlier.echo.ui.component.hasgtag.HashtagItemSort
import com.baptistecarlier.echo.ui.model.HashtagRatioItem
import com.baptistecarlier.echo.ui.state.HashtagListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HashtagListScreenVM @Inject constructor(
    private val getVideosUc: GetVideosUc
) : ViewModel() {

    private var _state = MutableStateFlow(HashtagListScreenState())
    val state: StateFlow<HashtagListScreenState> = _state

    private var _sort: HashtagItemSort = HashtagItemSort.Name

    init {
        refreshList()
    }

    fun refreshList() {
        _state.value = HashtagListScreenState(isLoading = true, list = _state.value.list)
        viewModelScope.launch {
            val list = getVideosUc()
            if (list.isEmpty()) {
                _state.value = HashtagListScreenState(isError = true)
            } else {
                val hashtagRatioItemList = toHashtagList(list)
                val hasgtagList = sortBy(hashtagRatioItemList, _sort)
                _state.value = HashtagListScreenState(list = hasgtagList, nbVideo = list.size)
            }
        }
    }

    private fun toHashtagList(youtubeVideoList: List<YoutubeVideo>): List<HashtagRatioItem> {
        val nbVideos = youtubeVideoList.size
        val workingMap = mutableMapOf<String, Int>()
        youtubeVideoList.flatMap { it.tags.toSet() }.groupingBy { it }.eachCountTo(workingMap)
        val hashtagRatioItemList = workingMap.map {
            HashtagRatioItem(
                label = it.key,
                count = it.value,
                percentage = ((it.value / nbVideos.toFloat()))
            )
        }
        return hashtagRatioItemList
    }

    private fun sortBy(
        list: List<HashtagRatioItem>,
        sort: HashtagItemSort
    ): List<HashtagRatioItem> {
        return when (sort) {
            HashtagItemSort.Name ->
                list
                    .sortedByDescending { it.count }
                    .sortedBy { it.label }
            HashtagItemSort.Ratio ->
                list
                    .sortedBy { it.label }
                    .sortedByDescending { it.count }
        }
    }

    fun sortBy(sort: HashtagItemSort) {
        _sort = sort
        _state.value.let {
            val size = it.nbVideo
            val list = sortBy(it.list, sort)
            _state.value = HashtagListScreenState(list = list, nbVideo = size)
        }
    }
}