package com.baptistecarlier.echo.ui.component.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.baptistecarlier.echo.domain.model.YoutubeVideo
import com.baptistecarlier.echo.ui.navigation.Screen

@Composable
fun HomeScreen(
    viewModel: HomeVM = hiltViewModel(),
    onNavigate: (Screen) -> Unit,
    onGoVideoDetail: (YoutubeVideo) -> Unit
) {
    val state = viewModel.state.collectAsState().value
    HomeView(state, onNavigate, viewModel::refresh) { onGoVideoDetail(it) }
}
