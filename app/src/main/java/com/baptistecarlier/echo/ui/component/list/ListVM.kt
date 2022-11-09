package com.baptistecarlier.echo.ui.component.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baptistecarlier.echo.domain.interactor.youtube.GetVideosUc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListVM @Inject constructor(
    private val getVideosUc: GetVideosUc
) : ViewModel() {

    private var _state = MutableStateFlow(ListUiState())
    val state: StateFlow<ListUiState> = _state

    init {
        refreshList()
    }

    fun refreshList() {
        _state.value = ListUiState(isLoading = true, list = _state.value.list)
        viewModelScope.launch {
            val list = getVideosUc()
            if (list.isEmpty()) {
                _state.value = ListUiState(isError = true)
            } else {
                _state.value = ListUiState(list = list)
            }
        }
    }
}
