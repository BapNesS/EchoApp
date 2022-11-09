package com.baptistecarlier.echo.ui.component.home

import android.content.openYoutubeStudio
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baptistecarlier.echo.R
import com.baptistecarlier.echo.domain.model.YoutubeVideo
import com.baptistecarlier.echo.ui.component.common.ErrorView
import com.baptistecarlier.echo.ui.component.common.LoadingView
import com.baptistecarlier.echo.ui.component.common.VideoItem
import com.baptistecarlier.echo.ui.model.LinkedInViewItem
import com.baptistecarlier.echo.ui.navigation.Screen
import com.baptistecarlier.echo.ui.theme.EchoTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeView(
    state: HomeUiState,
    onNavigate: (Screen) -> Unit,
    onRefresh: () -> Unit,
    onGoVideoDetail: (YoutubeVideo) -> Unit
) {
    Scaffold(
        topBar = {
            HomeHeader(state.youtubeChannelInfos) { onNavigate(Screen.Settings) }
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {

            if (state.isLoading) {
                LoadingView()
            } else if (state.isError) {
                ErrorView(onRefresh)
            } else {
                SwipeRefresh(
                    state = rememberSwipeRefreshState(state.isRefreshing),
                    onRefresh = onRefresh,
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            val context = LocalContext.current
                            Stats(state.youtubeChannelInfos) { context.openYoutubeStudio() }
                        }
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier.weight(1f),
                                    style = MaterialTheme.typography.h5,
                                    fontWeight = FontWeight.SemiBold,
                                    maxLines = 1,
                                    text = stringResource(id = R.string.mylastvideos)
                                )
                                TextButton(
                                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.secondary),
                                    onClick = { onNavigate(Screen.List) }
                                ) {
                                    Text(text = stringResource(R.string.all_videos))
                                }
                            }
                        }
                        items(state.list.take(3)) {
                            VideoItem(8.dp, it.second) { onGoVideoDetail(it.second) }
                        }
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier.weight(1f),
                                    style = MaterialTheme.typography.h5,
                                    fontWeight = FontWeight.SemiBold,
                                    maxLines = 1,
                                    text = stringResource(id = R.string.mytags)
                                )
                                TextButton(
                                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.secondary),
                                    onClick = { onNavigate(Screen.HashtagList) }
                                ) {
                                    Text(text = stringResource(R.string.all_videos))
                                }
                            }
                        }
                        item {
                            val tags =
                                state.list.flatMap { it.second.tags }.toSet().toList().sorted()
                            Text(
                                style = MaterialTheme.typography.body2,
                                text = tags.joinToString(", ")
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    name = "Loading",
    group = "ListView",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Loading",
    group = "ListView",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ListViewPreview_Loading() {
    EchoTheme {
        HomeView(state = HomeUiState(isLoading = true), {}, {}, {})
    }
}

@Preview(
    name = "Error",
    group = "ListView",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Error",
    group = "ListView",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ListViewPreview_Error() {
    EchoTheme {
        HomeView(state = HomeUiState(isError = true), {}, {}, {})
    }
}

@Preview(
    name = "2 items",
    group = "ListView",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "2 items",
    group = "ListView",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ListViewPreview_2items() {
    EchoTheme {
        HomeView(
            state = HomeUiState(
                list = listOf(
                    LinkedInViewItem(
                        "linkedInPostContent",
                        YoutubeVideo("id", "title", "date", "url1", "url2")
                    ),
                    LinkedInViewItem(
                        "linkedInPostContent",
                        YoutubeVideo("id", "title", "date", "url1", "url2", listOf("tag1", "tag2"))
                    ),
                )
            ),
            {}, {}, {}
        )
    }
}
