package com.baptistecarlier.echo.ui.component.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baptistecarlier.echo.R
import com.baptistecarlier.echo.domain.model.YoutubeVideo
import com.baptistecarlier.echo.ui.theme.EchoTheme
import java.util.*

@Composable
fun LinkedInPostView(
    previewComment: String,
    youtubeVideo: YoutubeVideo,
    onPostLinkedIn: () -> Unit,
    onPostTwitter: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        Modifier
            .fillMaxWidth()
            .clickable {
                expanded = !expanded
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(style = MaterialTheme.typography.subtitle1, text = youtubeVideo.title)
            AnimatedVisibility(visible = expanded) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Spacer(Modifier.padding(8.dp))
                    Text(
                        style = MaterialTheme.typography.overline,
                        text = stringResource(id = R.string.mycomment).uppercase(Locale.getDefault())
                    )
                    Text(
                        style = MaterialTheme.typography.body2,
                        text = previewComment
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(text = stringResource(id = R.string.publish_on))
                        Spacer(Modifier.padding(4.dp))
                        Button(onClick = { onPostLinkedIn() }) {
                            Text(text = stringResource(id = R.string.linkedin))
                        }
                        Spacer(Modifier.padding(4.dp))
                        Button(onClick = { onPostTwitter() }) {
                            Text(text = stringResource(id = R.string.twitter))
                        }
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
        LinkedInPostView("a", YoutubeVideo("b", "c", "d", "g", listOf("e", "f")), {}) { }
    }
}
