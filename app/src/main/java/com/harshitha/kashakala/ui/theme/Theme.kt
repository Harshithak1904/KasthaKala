package com.harshitha.kashakala.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = BrownPrimary,
    secondary = TerracottaSecondary,
    tertiary = OliveTertiary,
    background = Color.Black,
    surface = Color.DarkGray,
    onPrimary = WhiteText,
    onSecondary = WhiteText
)

private val LightColorScheme = lightColorScheme(
    primary = BrownPrimary,
    secondary = TerracottaSecondary,
    tertiary = OliveTertiary,
    background = BeigeBackground,
    surface = OffWhiteSurface,
    onPrimary = WhiteText,
    onSecondary = BlackText
)

@Composable
fun KashakalaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
