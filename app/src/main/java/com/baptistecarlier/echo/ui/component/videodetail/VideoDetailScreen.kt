package com.baptistecarlier.echo.ui.component.videodetail

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
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
import com.baptistecarlier.echo.ui.model.LinkedInViewItem
import com.baptistecarlier.echo.ui.state.VideoDetailState
import com.baptistecarlier.echo.ui.theme.EchoTheme

@Composable
fun VideoDetailScreen(
    state: VideoDetailState,
    onBack: () -> Unit,
    onPost: (
        postOn: PostOn,
        previewComment: String,
        youtubeVideo: YoutubeVideo
    ) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground,
                title = { Text(stringResource(id = R.string.screen_videodetail)) },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.Default.ArrowBack, stringResource(id = R.string.back))
                    }
                },
                elevation = 0.dp
            )
        }
    ) { innerPadding ->

        val linkedInViewItem = state.linkedInViewItem

        if (state.isLoading) {
            LoadingView()
        } else if (state.isError || linkedInViewItem == null) {
            ErrorView()
        } else {

            Column(
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {

                DetailView(
                    previewComment = linkedInViewItem.first,
                    youtubeVideo = linkedInViewItem.second
                ) { onPost(it, linkedInViewItem.first, linkedInViewItem.second) }
            }
        }
    }
}

@Preview(
    name = "Loading",
    group = "VideoDetailScreenPreview",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Loading",
    group = "VideoDetailScreenPreview",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun VideoDetailScreenPreview_Loading() {
    EchoTheme {
        VideoDetailScreen(state = VideoDetailState(isLoading = true), {}, { _, _, _ -> })
    }
}

@Preview(
    name = "Error",
    group = "VideoDetailScreenPreview",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Error",
    group = "VideoDetailScreenPreview",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun VideoDetailScreenPreview_Error() {
    EchoTheme {
        VideoDetailScreen(state = VideoDetailState(isError = true), {}, { _, _, _ -> })
    }
}

@Preview(
    name = "Null item",
    group = "VideoDetailScreenPreview",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Null item",
    group = "VideoDetailScreenPreview",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun VideoDetailScreenPreview_NullItem() {
    EchoTheme {
        VideoDetailScreen(state = VideoDetailState(linkedInViewItem = null), {}, { _, _, _ -> })
    }
}

@Preview(
    name = "Standard",
    group = "VideoDetailScreenPreview",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Standard",
    group = "VideoDetailScreenPreview",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun VideoDetailScreenPreview_Standard() {
    EchoTheme {
        VideoDetailScreen(
            state = VideoDetailState(
                linkedInViewItem = LinkedInViewItem(
                    "linkedInPostContent",
                    YoutubeVideo("id", "title", "date", "url1", "url2", listOf("tag1", "tag2"))
                )
            ),
            {}, { _, _, _ -> }
        )
    }
}