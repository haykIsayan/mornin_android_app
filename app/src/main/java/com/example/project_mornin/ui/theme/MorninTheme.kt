package com.example.project_mornin.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Define colors
val PrimaryOrange = Color(0xFFFF7A00)
val DeepBlack = Color(0xFF121212)
val LightCream = Color(0xFFFFF5E9)
val AccentGold = Color(0xFFFFB84D)
val SecondaryOrange = Color(0xFFE05E00)
val NeutralGray = Color(0xFF8A8A8A)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryOrange,
    onPrimary = Color.White,
    secondary = SecondaryOrange,
    onSecondary = Color.White,
    tertiary = AccentGold,
    onTertiary = DeepBlack,
    background = LightCream,
    onBackground = DeepBlack,
    surface = Color.White,
    onSurface = DeepBlack,
    surfaceVariant = Color(0xFFF0E8DE),
    outline = NeutralGray
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryOrange,
    onPrimary = DeepBlack,
    secondary = SecondaryOrange,
    onSecondary = DeepBlack,
    tertiary = AccentGold,
    onTertiary = DeepBlack,
    background = DeepBlack,
    onBackground = LightCream,
    surface = Color(0xFF1E1E1E),
    onSurface = LightCream,
    surfaceVariant = Color(0xFF2D2D2D),
    outline = NeutralGray
)

@Composable
fun MorninTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}