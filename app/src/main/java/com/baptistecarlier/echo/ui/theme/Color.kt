package com.baptistecarlier.echo.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Mustard = Color(0xFFffd54f)
val LemonCurry = Color(0xffc8a415)
val DogwoodRose = Color(0xffd81b60)
val PinkRaspberry = Color(0xffa00037)
val Plump = Color(0xff5e35b1)

val Boston = Color(0xFFC80000)
val Crimson = Color(0xFF9e0000)

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Colors.youtubeVariant: Color
    get() {
        return if (isLight) Boston else Crimson
    }