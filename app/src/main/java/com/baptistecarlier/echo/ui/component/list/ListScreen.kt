package com.baptistecarlier.echo.ui.component.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.baptistecarlier.echo.domain.model.YoutubeVideo

@Composable
fun ListScreen(
    viewModel: ListVM = hiltViewModel(),
    onBack: () -> Unit,
    onGoVideoDetail: (YoutubeVideo) -> Unit
) {
    val state = viewModel.state.collectAsState().value

    ListView(state, onBack, { onGoVideoDetail(it) }) { viewModel.refreshList() }
}
