package com.baptistecarlier.echo.ui.component

import android.content.shareOnTwitter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.baptistecarlier.echo.domain.model.YoutubeVideo
import com.baptistecarlier.echo.ui.component.list.ListScreen
import com.baptistecarlier.echo.ui.component.settings.SettingsScreen
import com.baptistecarlier.echo.ui.navigation.Screen
import com.baptistecarlier.echo.ui.viewmodel.list.ListScreenVM
import com.baptistecarlier.echo.ui.viewmodel.settings.SettingsScreenVM

@Composable
fun MainNavigation() {

    val navController = rememberNavController()
    val startDestination = Screen.List

    val onBack: () -> Unit = { navController.popBackStack() }
    val onNavigate: (Screen) -> Unit = { navController.navigate(it.route) }

    NavHost(navController = navController, startDestination = startDestination.route) {
        composable(Screen.List.route) {
            val viewModel: ListScreenVM = hiltViewModel()
            val state = viewModel.state.collectAsState().value

            val context = LocalContext.current

            val onRefresh = { viewModel.refreshList() }
            val onPostLinkedIn: (previewComment: String, youtubeVideo: YoutubeVideo) -> Unit =
                { p, y -> viewModel.post(p, y) }
            val onPostTwitter: (previewComment: String) -> Unit =
                { p -> context.shareOnTwitter(p) }

            ListScreen(state, onNavigate, onRefresh, onPostLinkedIn, onPostTwitter)
        }
        composable(Screen.Settings.route) {
            val viewModel: SettingsScreenVM = hiltViewModel()
            val state = viewModel.state.collectAsState().value

            val updateYoutubeAccess: (apiKey: String, channelId: String) -> Unit =
                { a, c -> viewModel.updateYoutubeAccess(a, c) }
            val updateLinkedInAccess: (accessId: String, linkedInId: String) -> Unit =
                { a, l -> viewModel.updateLinkedInAccess(a, l) }

            LaunchedEffect(state) {
                if (state.done) {
                    onBack()
                }
            }

            SettingsScreen(state, onBack, updateYoutubeAccess, updateLinkedInAccess)
        }
    }
}
