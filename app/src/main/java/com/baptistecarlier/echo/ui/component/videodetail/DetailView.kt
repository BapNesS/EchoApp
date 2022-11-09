package com.baptistecarlier.echo.ui.component.videodetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
                var openDialog by rememberSaveable { mutableStateOf<PostOn?>(null) }
                if (openDialog != null) {
                    AlertDialog(
                        onDismissRequest = { openDialog = null },
                        text = { Text(text = "Are you sure you want to publish it right now?") },
                        buttons = {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.End
                            ) {
                                OutlinedButton(onClick = { openDialog = null }) {
                                    Text("Dismiss")
                                }
                                Spacer(Modifier.padding(4.dp))
                                Button(onClick = {
                                    openDialog?.let { onPost(it) }
                                    openDialog = null
                                }) {
                                    Text("Confirm")
                                }
                            }
                        }
                    )
                }

                PostOn.values().forEach { postOn ->
                    Button(
                        shape = RoundedCornerShape(16.dp),
                        onClick = {
                            if (postOn.requestConfirm) {
                                openDialog = postOn
                            } else {
                                onPost(postOn)
                            }
                        }
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
