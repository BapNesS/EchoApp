package com.baptistecarlier.echo.ui.navigation

sealed class Screen(val route: String) {
    object List : Screen("list")
    object Settings : Screen("settings")
}
