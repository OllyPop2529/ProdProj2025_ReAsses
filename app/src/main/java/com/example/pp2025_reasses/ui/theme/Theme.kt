package com.example.pp2025_reasses.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(133, 23, 1),
    secondary = Color.White,
    tertiary = Color.Red,
    onBackground = Color(145, 145, 145, 255),
)

val LightColorScheme = lightColorScheme(
    primary = Color(227, 78, 48) ,
    onPrimary = Color.White,
    background = Color.White,
    onBackground = Color.White,
    tertiary = Color.Black
)

//Not Functional Yet
val HighContrastColorScheme = lightColorScheme(
    primary = Color(238, 81, 49, 255),
    onPrimary = Color.White,
    background = Color.White,
    onBackground = Color.White
)



@Composable
fun PP2025_ReassesTheme(
    themeMode: ActiveTheme,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val colorScheme = when (themeMode) {
        ActiveTheme.DARK -> {
            if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                dynamicDarkColorScheme(context)
            } else DarkColorScheme
        }
        ActiveTheme.HIGH_CONTRAST -> HighContrastColorScheme
        ActiveTheme.LIGHT -> {
            if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                dynamicLightColorScheme(context)
            } else LightColorScheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

enum class ActiveTheme {
    LIGHT,
    DARK,
    HIGH_CONTRAST
}