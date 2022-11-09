package com.baptistecarlier.echo.ui.component.hasgtag

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HashtagListScreen(
    viewModel: HashtagListVM = hiltViewModel(),
    onBack: () -> Unit
) {
    val state = viewModel.state.collectAsState().value
    HashtagListView(state, onBack, { viewModel.refreshList() }) { viewModel.sortBy(it) }
}
