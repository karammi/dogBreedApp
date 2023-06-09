package com.asad.dogs.core.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val md_theme_light_primary = Color(0xFF205fa6)
val md_theme_light_onPrimary = Color(0xFFffffff)
val md_theme_light_secondary = Color(0xFF555f71)
val md_theme_light_onSecondary = Color(0xFFffffff)
val md_theme_light_tertiary = Color(0xFF6e5676)
val md_theme_light_onTertiary = Color(0xFFffffff)
val md_theme_light_background = Color(0xFFfdfbff)
val md_theme_light_onBackground = Color(0xFF1b1b1d)
val md_theme_light_surface = Color(0xFFfdfbff)
val md_theme_light_onSurface = Color(0xFF1b1b1d)

val md_theme_dark_primary = Color(0xFFa4c8ff)
val md_theme_dark_onPrimary = Color(0xFF003061)
val md_theme_dark_secondary = Color(0xFFbdc7dc)
val md_theme_dark_onSecondary = Color(0xFF273141)
val md_theme_dark_tertiary = Color(0xFFdabce2)
val md_theme_dark_onTertiary = Color(0xFF3d2846)
val md_theme_dark_background = Color(0xFF1b1b1d)
val md_theme_dark_onBackground = Color(0xFFe3e2e6)
val md_theme_dark_surface = Color(0xFF1b1b1d)
val md_theme_dark_onSurface = Color(0xFFe3e2e6)


val seed = Color(0xFF3465a4)
val error = Color(0xFFba1b1b)

private val DarkColorScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    secondary = md_theme_dark_secondary,
    tertiary = md_theme_dark_tertiary,
    onPrimary = md_theme_dark_onPrimary,
    onSecondary = md_theme_dark_onSecondary,
    onTertiary = md_theme_dark_onTertiary,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    onBackground = md_theme_dark_onBackground,
    background = md_theme_dark_background,
)

private val LightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    secondary = md_theme_light_secondary,
    tertiary = md_theme_light_tertiary,
    onPrimary = md_theme_light_onPrimary,
    onSecondary = md_theme_light_onSecondary,
    onTertiary = md_theme_light_onTertiary,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    onBackground = md_theme_light_onBackground,
    background = md_theme_light_background,

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun DogsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
