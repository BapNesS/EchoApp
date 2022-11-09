package com.baptistecarlier.echo.ui.component.videodetail

import android.content.share
import android.content.shareOnTwitter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.baptistecarlier.echo.domain.model.YoutubeVideo
import com.baptistecarlier.echo.ui.model.PostOn

@Composable
fun VideoDetailScreen(
    viewModel: VideoDetailVM = hiltViewModel(),
    onBack: () -> Unit
) {
    val state = viewModel.state.collectAsState().value

    val context = LocalContext.current
    val onPost: (postOn: PostOn, previewComment: String, youtubeVideo: YoutubeVideo) -> Unit =
        { postOn, previewComment, youtubeVideo ->
            when (postOn) {
                PostOn.LinkedIn -> viewModel.postOnLinkedIn(previewComment, youtubeVideo)
                PostOn.Twitter -> context.shareOnTwitter(previewComment)
                PostOn.JustText -> context.share(previewComment)
            }
        }

    VideoDetailView(state, onBack, onPost)
}
