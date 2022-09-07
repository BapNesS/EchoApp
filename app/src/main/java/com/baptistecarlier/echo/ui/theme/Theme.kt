package com.baptistecarlier.echo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Color(0xFFffd54f),
    primaryVariant = Color(0xffc8a415),
    onPrimary = Color(0xff000000),
    secondary = Color(0xffd81b60),
    secondaryVariant = Color(0xffa00037),
    onSecondary = Color(0xffffffff),
    background = Color(0xffffffff),
    onBackground = Color(0xff000000),
    surface = Color(0xFFF8F8F8),
    onSurface = Color(0xff000000),
    error = Color(0xff5e35b1),
    onError = Color(0xffffffff)
)

@Composable
fun EchoTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
