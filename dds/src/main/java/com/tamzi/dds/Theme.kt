package com.tamzi.dds

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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.tamzi.dds.atoms.color.*
import com.tamzi.dds.atoms.type.DashikiTypography

val DarkColorPalette = darkColorScheme(
    primary = dashikiAmber,
    secondary = dashikiBlue,
    background = dashikiBlack,
)

val LightColorPalette = lightColorScheme(
    primary = dashikiPurple,
    secondary = dashikiLimeGreenish,
    background = dashikiWhite,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

/**
 * This is the theme that is used with the apps:
 * 1. Near Education
 * 2. Nearer Catalog
 *
 */
@Composable
fun DashikiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorPallete = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Suppress deprecation - statusBarColor is deprecated but still widely used for compatibility
            @Suppress("DEPRECATION")
            window.statusBarColor = colorPallete.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorPallete,
        typography = DashikiTypography,
        content = content
    )
}