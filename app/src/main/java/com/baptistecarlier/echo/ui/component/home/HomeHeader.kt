package com.baptistecarlier.echo.ui.component.home

import android.content.openYoutubeChannel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.baptistecarlier.echo.domain.model.YoutubeChannelInfos
import com.baptistecarlier.echo.ui.theme.EchoTheme

@Composable
fun HomeHeader(youtubeUser: YoutubeChannelInfos?, onSettings: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(top = 24.dp)
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val shape = RoundedCornerShape(8.dp)
        val error = ColorPainter(MaterialTheme.colors.surface)
        val context = LocalContext.current
        AsyncImage(
            modifier = Modifier
                .size(48.dp)
                .clip(shape)
                .clickable {
                    youtubeUser?.channelId?.let {
                        context.openYoutubeChannel(it)
                    }
                },
            model = youtubeUser?.profilePictureUrl ?: "",
            error = error,
            placeholder = error,
            contentDescription = null
        )
        Text(
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            text = youtubeUser?.name ?: " "
        )
        Button(
            modifier = Modifier
                .size(48.dp)
                .clip(shape),
            contentPadding = PaddingValues(12.dp),
            onClick = onSettings
        ) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = Icons.Filled.Settings,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun HomeHeaderPreview() {
    EchoTheme {
        HomeHeader(
            YoutubeChannelInfos(
                "",
                0,
                0,
                "Name Name Name Name Name Name Name Name Name Name Name Name Name ",
                "https://i.picsum.photos/id/408/200/200.jpg?hmac=VJjKULX_XeyV5C9mbWyv6XTsG5EV-ZBsqbiQIi6xTeg"
            )
        ) { }
    }
}