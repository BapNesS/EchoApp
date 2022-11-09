package com.baptistecarlier.echo.ui.component.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsVM = hiltViewModel(),
    onBack: () -> Unit
) {
    val state = viewModel.state.collectAsState().value
    LaunchedEffect(state) {
        if (state.done) {
            onBack()
        }
    }
    SettingsView(state, onBack, viewModel::updateYoutubeAccess, viewModel::updateLinkedInAccess)
}
