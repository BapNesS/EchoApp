package com.baptistecarlier.echo.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Settings : Screen("settings")
    object List : Screen("videolist")
    object VideoDetail : Screen("detail/{id}/")
    object HashtagList : Screen("hashtaglist")
}

fun NavController.navigate(screen: Screen) {
    navigate(screen.route)
}

fun NavController.routeWithArgs(screen: Screen, arg: String): String {
    return screen.route.replaceFirst("\\{[a-zA-Z]*\\}".toRegex(), arg)
}

fun NavController.navigate(screen: Screen, arg: String) {
    val screenRoute = routeWithArgs(screen, arg)
    navigate(screenRoute)
}

fun NavController.navigate(screen: Screen, arg: String, builder: NavOptionsBuilder.() -> Unit) {
    val screenRoute = routeWithArgs(screen, arg)
    navigate(screenRoute, navOptions(builder))
}