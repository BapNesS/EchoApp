package com.baptistecarlier.echo.ui.component.videodetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baptistecarlier.echo.R
import com.baptistecarlier.echo.domain.model.YoutubeVideo
import com.baptistecarlier.echo.ui.component.common.layout.FlowRow
import com.baptistecarlier.echo.ui.model.PostOn
import com.baptistecarlier.echo.ui.theme.EchoTheme
import java.util.*

@Composable
fun DetailView(
    previewComment: String,
    youtubeVideo: YoutubeVideo,
    onPost: (PostOn) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(36.dp)) {
        Text(style = MaterialTheme.typography.subtitle1, text = youtubeVideo.title)

        Card(
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                style = MaterialTheme.typography.body2,
                text = previewComment
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                style = MaterialTheme.typography.caption,
                text = stringResource(id = R.string.publish_on).uppercase(Locale.getDefault())
            )
            FlowRow(
                horizontalGap = 8.dp,
                verticalGap = 0.dp
            ) {
                PostOn.values().forEach { postOn ->
                    Button(
                        shape = RoundedCornerShape(16.dp),
                        onClick = { onPost(postOn) }
                    ) {
                        Text(
                            style = MaterialTheme.typography.body2,
                            text = stringResource(id = postOn.label)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun LinkedInPostViewPreview() {
    EchoTheme {
        DetailView("a", YoutubeVideo("a", "b", "c", "d", "g", listOf("e", "f"))) { }
    }
}
