package com.baptistecarlier.echo.ui.component.home

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baptistecarlier.echo.R
import com.baptistecarlier.echo.domain.model.YoutubeChannelInfos
import com.baptistecarlier.echo.ui.theme.EchoTheme
import com.baptistecarlier.echo.ui.theme.youtubeVariant
import java.util.*

@Composable
fun Stats(youtubeStats: YoutubeChannelInfos?, onClick: () -> Unit) {
    val contentColor = Color.White
    Box(
        Modifier
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colors.youtubeVariant)
            .clickable { onClick() }
    ) {
        Image(
            modifier = Modifier
                .size(168.dp)
                .align(BiasAlignment(1.15f, 1.1f)),
            painter = painterResource(id = R.drawable.ic_youtubestudio),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.SemiBold,
                color = contentColor,
                text = stringResource(id = R.string.youtubestats_title)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                Column {
                    Text(
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        color = contentColor,
                        text = youtubeStats?.subscribers.toString()
                    )
                    Text(
                        style = MaterialTheme.typography.body1,
                        color = contentColor,
                        text = stringResource(id = R.string.youtubestats_subscribers)
                    )
                }
                Column {
                    Text(
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        color = contentColor,
                        text = youtubeStats?.videos.toString()
                    )
                    Text(
                        style = MaterialTheme.typography.body1,
                        color = contentColor,
                        text = stringResource(id = R.string.youtubestats_videos)
                    )
                }
            }
            Spacer(Modifier.padding(8.dp))
            Text(
                style = MaterialTheme.typography.button,
                color = contentColor,
                text = stringResource(id = R.string.youtubestats_link).uppercase(Locale.getDefault())
            )
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun StatsPreview() {
    EchoTheme {
        Stats(
            YoutubeChannelInfos(
                "",
                0,
                0,
                "Name Name Name Name Name Name Name Name Name Name Name Name Name ",
                "https://i.picsum.photos/id/408/200/200.jpg?hmac=VJjKULX_XeyV5C9mbWyv6XTsG5EV-ZBsqbiQIi6xTeg"
            )
        ) {}
    }
}