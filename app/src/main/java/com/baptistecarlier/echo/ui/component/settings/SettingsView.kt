package com.baptistecarlier.echo.ui.component.settings

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baptistecarlier.echo.R
import com.baptistecarlier.echo.domain.model.LinkedInAccess
import com.baptistecarlier.echo.domain.model.YoutubeAccess
import com.baptistecarlier.echo.ui.component.common.LoadingView
import com.baptistecarlier.echo.ui.theme.EchoTheme

@Composable
fun SettingsView(
    state: SettingsUiState,
    onBack: () -> Unit,
    updateYoutubeAccess: (apiKey: String, channelId: String) -> Unit,
    updateLinkedInAccess: (accessId: String, linkedInId: String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground,
                title = { Text(stringResource(id = R.string.screen_settings)) },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.Default.ArrowBack, stringResource(id = R.string.back))
                    }
                },
                elevation = 0.dp
            )
        }
    ) { innerPadding ->
        if (state.linkedInAccess != null && state.youtubeAccess != null) {

            Column(
                Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {

                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    ItemSettings(
                        R.string.settings_overline_youtube,
                        R.string.settings_apikey,
                        state.youtubeAccess.first,
                        R.string.settings_youtube_channelid,
                        state.youtubeAccess.second
                    ) { first, second -> updateYoutubeAccess(first, second) }

                    ItemSettings(
                        R.string.settings_overline_linkedin,
                        R.string.settings_linkedin_accessid,
                        state.linkedInAccess.first,
                        R.string.settings_linkedin_linkedinid,
                        state.linkedInAccess.second
                    ) { first, second -> updateLinkedInAccess(first, second) }
                }
            }
        } else {
            LoadingView()
        }
    }
}

@Preview(name = "Loading", group = "SettingsScreenPreview", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Loading", group = "SettingsScreenPreview", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SettingsScreenPreview_Loading() {
    EchoTheme {
        SettingsView(
            SettingsUiState(),
            {},
            { _, _ -> },
            { _, _ -> }
        )
    }
}

@Preview(name = "Preview", group = "SettingsScreenPreview", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Preview", group = "SettingsScreenPreview", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SettingsScreenPreview() {
    EchoTheme {
        SettingsView(
            SettingsUiState(
                false,
                YoutubeAccess("a", "b"),
                LinkedInAccess("c", "d")
            ),
            {},
            { _, _ -> },
            { _, _ -> }
        )
    }
}
