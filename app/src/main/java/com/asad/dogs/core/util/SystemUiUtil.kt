package com.asad.dogs.core.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

object SystemUiUtil {
    @Composable
    fun ConfigStatusBar(color: Color, userDarkIcons: Boolean = false) {
        val systemUiController = rememberSystemUiController()

        SideEffect {
            systemUiController.setStatusBarColor(
                color = color,
                darkIcons = userDarkIcons,
            )
        }
    }
}
