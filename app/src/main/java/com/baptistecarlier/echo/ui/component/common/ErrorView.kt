package com.baptistecarlier.echo.ui.component.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baptistecarlier.echo.R
import com.baptistecarlier.echo.ui.theme.EchoTheme

@Composable
fun ErrorView() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            style = MaterialTheme.typography.h6,
            text = stringResource(id = R.string.error_title)
        )
        Spacer(Modifier.padding(4.dp))
        Text(
            style = MaterialTheme.typography.body1,
            text = stringResource(id = R.string.error_content)
        )
    }
}

@Composable
fun ErrorView(onRefresh: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            style = MaterialTheme.typography.h6,
            text = stringResource(id = R.string.error_title)
        )
        Spacer(Modifier.padding(4.dp))
        Text(
            style = MaterialTheme.typography.body1,
            text = stringResource(id = R.string.error_content)
        )
        Spacer(Modifier.padding(16.dp))
        Button(onClick = { onRefresh() }) {
            Text(stringResource(id = R.string.refresh))
        }
    }
}

@Preview(name = "Not refreshable", group = "ErrorView", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Not refreshable", group = "ErrorView", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ErrorViewPreview() {
    EchoTheme { ErrorView() }
}

@Preview(name = "Refreshable", group = "ErrorView", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Refreshable", group = "ErrorView", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ErrorViewPreview_Refreshable() {
    EchoTheme { ErrorView {} }
}