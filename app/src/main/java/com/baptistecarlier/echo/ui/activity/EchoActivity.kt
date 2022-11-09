package com.baptistecarlier.echo.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.baptistecarlier.echo.ui.component.EchoNavigation
import com.baptistecarlier.echo.ui.theme.EchoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EchoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EchoTheme {
                EchoNavigation()
            }
        }
    }
}
