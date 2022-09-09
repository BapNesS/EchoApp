package com.baptistecarlier.echo.ui.component.common

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.baptistecarlier.echo.R
import com.baptistecarlier.echo.domain.model.YoutubeVideo
import com.baptistecarlier.echo.ui.theme.EchoTheme

@Composable
fun VideoItem(verticalPadding: Dp = 0.dp, youtubeVideo: YoutubeVideo, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = verticalPadding),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            style = MaterialTheme.typography.subtitle2,
            text = youtubeVideo.title
        )
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
            val tags = if (youtubeVideo.tags.isNotEmpty()) {
                youtubeVideo.tags.joinToString(", ")
            } else {
                stringResource(id = R.string.notags)
            }
            Text(
                style = MaterialTheme.typography.body2,
                text = tags
            )
        }
    }
}

@Preview(name = "VideoItem", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "VideoItem", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun VideoItemPreview() {
    EchoTheme {
        VideoItem(youtubeVideo = YoutubeVideo("a", "b", "c", "d", "f", listOf("e", "f"))) { }
    }
}