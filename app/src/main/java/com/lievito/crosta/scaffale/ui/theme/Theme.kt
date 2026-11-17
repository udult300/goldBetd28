package com.lievito.crosta.scaffale.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val MuseumLight = lightColorScheme(
    primary = SeedAccent,
    onPrimary = SeedSurface,
    secondary = SeedMuted,
    onSecondary = SeedSurface,
    background = SeedBg,
    onBackground = SeedText,
    surface = SeedSurface,
    onSurface = SeedText,
    surfaceVariant = SeedBg,
    onSurfaceVariant = SeedMuted,
    outline = SeedMuted,
)

// Не используем тёмную тему: музейная бумага.
private val MuseumDark = darkColorScheme(
    primary = SeedAccent,
    onPrimary = SeedText,
    secondary = SeedMuted,
    onSecondary = SeedText,
    background = SeedText,
    onBackground = SeedSurface,
    surface = SeedText,
    onSurface = SeedSurface,
)

@Composable
fun MuseumLievitoTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) MuseumDark else MuseumLight,
        typography = MuseumTypography,
        content = content,
    )
}
