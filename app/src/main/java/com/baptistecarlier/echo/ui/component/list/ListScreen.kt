package com.baptistecarlier.echo.ui.component.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baptistecarlier.echo.R
import com.baptistecarlier.echo.domain.model.YoutubeVideo
import com.baptistecarlier.echo.ui.component.common.ErrorView
import com.baptistecarlier.echo.ui.component.common.LoadingView
import com.baptistecarlier.echo.ui.navigation.Screen
import com.baptistecarlier.echo.ui.state.list.ListScreenState
import com.baptistecarlier.echo.ui.theme.EchoTheme
import com.baptistecarlier.echo.ui.viewmodel.list.LinkedInViewItem

@Composable
fun ListScreen(
    state: ListScreenState,
    onNavigate: (Screen) -> Unit,
    onRefresh: () -> Unit,
    onPostLinkedIn: (previewComment: String, youtubeVideo: YoutubeVideo) -> Unit,
    onPostTwitter: (previewComment: String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground,
                title = { Text(stringResource(id = R.string.screen_list)) },
                actions = {
                    IconButton(onClick = { onNavigate(Screen.Settings) }) {
                        Icon(Icons.Default.Settings, stringResource(id = R.string.screen_settings))
                    }
                },
                elevation = 0.dp
            )
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {

            if (state.isLoading) {
                LoadingView()
            }

            if (state.isError) {
                ErrorView(onRefresh)
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        if (!state.isLoading) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Button(onClick = { onRefresh() }) {
                                    Text(stringResource(id = R.string.refresh))
                                }
                            }
                        }
                    }
                    items(state.list) {
                        LinkedInPostView(previewComment = it.first, youtubeVideo = it.second, {
                            onPostLinkedIn(it.first, it.second)
                        }) {
                            onPostTwitter(it.first)
                        }
                    }
                }
            }
        }
    }
}

@Preview(name = "Loading", group = "ListView")
@Composable
fun ListViewPreview_Loading() {
    EchoTheme {
        ListScreen(state = ListScreenState(isLoading = true), {}, {}, { _, _ -> }) { }
    }
}

@Preview(name = "Error", group = "ListView")
@Composable
fun ListViewPreview_Error() {
    EchoTheme {
        ListScreen(state = ListScreenState(isError = true), {}, {}, { _, _ -> }) { }
    }
}

@Preview(name = "Error", group = "ListView")
@Composable
fun ListViewPreview_2items() {
    EchoTheme {
        ListScreen(
            state = ListScreenState(
                list = listOf(
                    LinkedInViewItem(
                        "linkedInPostContent",
                        YoutubeVideo("title", "date", "url1", "url2")
                    ),
                    LinkedInViewItem(
                        "linkedInPostContent",
                        YoutubeVideo("title", "date", "url1", "url2")
                    ),
                )
            ),
            {},
            {},
            { _, _ -> }
        ) { }
    }
}
