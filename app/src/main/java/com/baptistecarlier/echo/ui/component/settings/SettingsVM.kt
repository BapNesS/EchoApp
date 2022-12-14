package com.baptistecarlier.echo.ui.component.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baptistecarlier.echo.domain.interactor.linkedin.GetLinkedInAccessUc
import com.baptistecarlier.echo.domain.interactor.linkedin.StoreLinkedInAccessUc
import com.baptistecarlier.echo.domain.interactor.youtube.GetYoutubeAccessUc
import com.baptistecarlier.echo.domain.interactor.youtube.StoreYoutubeAccessUc
import com.baptistecarlier.echo.domain.model.LinkedInAccess
import com.baptistecarlier.echo.domain.model.YoutubeAccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsVM @Inject constructor(
    private val getYoutubeAccessUc: GetYoutubeAccessUc,
    private val getLinkedInAccessUc: GetLinkedInAccessUc,
    private val storeYoutubeAccessUc: StoreYoutubeAccessUc,
    private val storeLinkedInAccessUc: StoreLinkedInAccessUc
) : ViewModel() {

    private var _state = MutableStateFlow(SettingsUiState())
    val state: StateFlow<SettingsUiState> = _state

    init {
        viewModelScope.launch {
            val y = getYoutubeAccessUc()
            val l = getLinkedInAccessUc()
            _state.value = SettingsUiState(false, y, l)
        }
    }

    private fun done() {
        _state.value =
            SettingsUiState(true, _state.value.youtubeAccess, _state.value.linkedInAccess)
    }

    fun updateYoutubeAccess(apiKey: String, channelId: String) {
        viewModelScope.launch {
            storeYoutubeAccessUc(YoutubeAccess(apiKey, channelId))
            done()
        }
    }

    fun updateLinkedInAccess(accessId: String, linkedInId: String) {
        viewModelScope.launch {
            storeLinkedInAccessUc(LinkedInAccess(accessId, linkedInId))
            done()
        }
    }
}
