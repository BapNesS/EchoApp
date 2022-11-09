package com.baptistecarlier.echo.ui.component.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baptistecarlier.echo.R
import com.baptistecarlier.echo.domain.model.YoutubeVideo
import com.baptistecarlier.echo.ui.component.common.ErrorView
import com.baptistecarlier.echo.ui.component.common.LoadingView
import com.baptistecarlier.echo.ui.component.common.VideoItem
import com.baptistecarlier.echo.ui.theme.EchoTheme

@Composable
fun ListView(
    state: ListUiState,
    onBack: () -> Unit,
    onGoVideoDetail: (YoutubeVideo) -> Unit,
    onRefresh: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground,
                title = { Text(stringResource(id = R.string.screen_list)) },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.Default.ArrowBack, stringResource(id = R.string.back))
                    }
                },
                elevation = 0.dp
            )
        }
    ) { innerPadding ->

        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            if (state.isLoading) {
                LoadingView()
            } else if (state.isError) {
                ErrorView(onRefresh)
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.list) { youtubeVideo ->
                        VideoItem(8.dp, youtubeVideo) { onGoVideoDetail(youtubeVideo) }
                    }
                }
            }
        }
    }
}

@Preview(
    name = "Loading",
    group = "ListScreen",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Loading",
    group = "ListScreen",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ListScreenPreview_Loading() {
    EchoTheme {
        ListView(
            state = ListUiState(isLoading = true), {}, {}, {}
        )
    }
}

@Preview(
    name = "Error",
    group = "ListScreen",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Error",
    group = "ListScreen",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ListScreenPreview_Error() {
    EchoTheme {
        ListView(
            state = ListUiState(isError = true), {}, {}, {}
        )
    }
}

@Preview(
    name = "2 items",
    group = "ListScreen",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "2 items",
    group = "ListScreen",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ListScreenPreview_2items() {
    EchoTheme {
        ListView(
            state = ListUiState(
                list = listOf(
                    YoutubeVideo("id", "title", "date", "url1", "url2"),
                    YoutubeVideo("id", "title", "date", "url1", "url2", listOf("tag1", "tag2")),
                ),
            ),
            {}, {}, {}
        )
    }
}
