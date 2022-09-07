package com.baptistecarlier.echo.ui.viewmodel.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baptistecarlier.echo.domain.interactor.linkedin.GetLinkedInAccessUc
import com.baptistecarlier.echo.domain.interactor.linkedin.StoreLinkedInAccessUc
import com.baptistecarlier.echo.domain.interactor.youtube.GetYoutubeAccessUc
import com.baptistecarlier.echo.domain.interactor.youtube.StoreYoutubeAccessUc
import com.baptistecarlier.echo.domain.model.LinkedInAccess
import com.baptistecarlier.echo.domain.model.YoutubeAccess
import com.baptistecarlier.echo.ui.state.settings.SettingsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenVM @Inject constructor(
    private val getYoutubeAccessUc: GetYoutubeAccessUc,
    private val getLinkedInAccessUc: GetLinkedInAccessUc,
    private val storeYoutubeAccessUc: StoreYoutubeAccessUc,
    private val storeLinkedInAccessUc: StoreLinkedInAccessUc
) : ViewModel() {

    private var _state = MutableStateFlow(SettingsScreenState())
    val state: StateFlow<SettingsScreenState> = _state

    init {
        viewModelScope.launch {
            val y = getYoutubeAccessUc()
            val l = getLinkedInAccessUc()
            _state.value = SettingsScreenState(false, y, l)
        }
    }

    private fun done() {
        _state.value =
            SettingsScreenState(true, _state.value.youtubeAccess, _state.value.linkedInAccess)
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
