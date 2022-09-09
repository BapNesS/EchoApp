package com.baptistecarlier.echo.ui.component

import android.content.share
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
import com.baptistecarlier.echo.ui.component.hasgtag.HashtagListScreen
import com.baptistecarlier.echo.ui.component.home.HomeScreen
import com.baptistecarlier.echo.ui.component.list.ListScreen
import com.baptistecarlier.echo.ui.component.settings.SettingsScreen
import com.baptistecarlier.echo.ui.component.videodetail.PostOn
import com.baptistecarlier.echo.ui.component.videodetail.VideoDetailScreen
import com.baptistecarlier.echo.ui.navigation.Screen
import com.baptistecarlier.echo.ui.navigation.navigate
import com.baptistecarlier.echo.ui.viewmodel.*

@Composable
fun MainNavigation() {

    val navController = rememberNavController()
    val startDestination = Screen.Home

    // Navigation Units
    val onBack: () -> Unit = { navController.popBackStack() }
    val onNavigate: (Screen) -> Unit = { navController.navigate(it.route) }
    val onGoVideoDetail: (YoutubeVideo) -> Unit = {
        navController.navigate(Screen.VideoDetail, it.id)
    }

    NavHost(navController = navController, startDestination = startDestination.route) {
        composable(Screen.Home.route) {
            val viewModel: HomeScreenVM = hiltViewModel()
            val state = viewModel.state.collectAsState().value

            val onRefresh = { viewModel.refreshList() }

            HomeScreen(state, onNavigate, onRefresh, { onGoVideoDetail(it) })
        }

        composable(Screen.List.route) {
            val viewModel: ListScreenVM = hiltViewModel()
            val state = viewModel.state.collectAsState().value

            ListScreen(state, onBack, { onGoVideoDetail(it) }) { viewModel.refreshList() }
        }

        composable(Screen.HashtagList.route) {
            val viewModel: HashtagListScreenVM = hiltViewModel()
            val state = viewModel.state.collectAsState().value

            HashtagListScreen(state, onBack, { viewModel.refreshList() }) { viewModel.sortBy(it) }
        }

        composable(Screen.VideoDetail.route) {
            val viewModel: VideoDetailScreenVM = hiltViewModel()
            val state = viewModel.state.collectAsState().value

            val context = LocalContext.current
            val onPost: (postOn: PostOn, previewComment: String, youtubeVideo: YoutubeVideo) -> Unit =
                { postOn, previewComment, youtubeVideo ->
                    when (postOn) {
                        PostOn.LinkedIn -> viewModel.post(previewComment, youtubeVideo)
                        PostOn.Twitter -> context.shareOnTwitter(previewComment)
                        PostOn.JustText -> context.share(previewComment)
                    }
                }

            VideoDetailScreen(state, onBack, onPost)
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
