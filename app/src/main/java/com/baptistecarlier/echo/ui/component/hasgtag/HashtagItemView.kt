package com.baptistecarlier.echo.ui.component.hasgtag

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.baptistecarlier.echo.ui.model.HashtagRatioItem

@Composable
fun HashtagItemView(verticalPadding: Dp = 0.dp, hashtagRatioItem: HashtagRatioItem, total: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = verticalPadding),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.subtitle2,
                text = hashtagRatioItem.label
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
                val fl = "%.1f".format(hashtagRatioItem.percentage * 100)
                Text(
                    style = MaterialTheme.typography.body2,
                    text = "$fl % (${hashtagRatioItem.count}/$total)"
                )
            }
        }
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            color = if (isSystemInDarkTheme()) {
                MaterialTheme.colors.primary
            } else {
                MaterialTheme.colors.secondary
            },
            progress = hashtagRatioItem.percentage
        )
    }
}
