package com.baptistecarlier.echo.ui.component

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.baptistecarlier.echo.domain.model.YoutubeVideo
import com.baptistecarlier.echo.ui.component.hasgtag.HashtagListScreen
import com.baptistecarlier.echo.ui.component.home.HomeScreen
import com.baptistecarlier.echo.ui.component.list.ListScreen
import com.baptistecarlier.echo.ui.component.settings.SettingsScreen
import com.baptistecarlier.echo.ui.component.videodetail.VideoDetailScreen
import com.baptistecarlier.echo.ui.navigation.Screen
import com.baptistecarlier.echo.ui.navigation.navigate

@Composable
fun EchoNavigation() {

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
            HomeScreen(onNavigate = onNavigate, onGoVideoDetail = onGoVideoDetail)
        }
        composable(Screen.List.route) {
            ListScreen(onBack = onBack, onGoVideoDetail = onGoVideoDetail)
        }
        composable(Screen.HashtagList.route) {
            HashtagListScreen(onBack = onBack)
        }
        composable(Screen.VideoDetail.route) {
            VideoDetailScreen(onBack = onBack)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(onBack = onBack)
        }
    }
}
