package com.baptistecarlier.echo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baptistecarlier.echo.domain.interactor.youtube.GetVideosUc
import com.baptistecarlier.echo.ui.state.ListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListScreenVM @Inject constructor(
    private val getVideosUc: GetVideosUc
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
                _state.value = ListScreenState(list = list)
            }
        }
    }
}
