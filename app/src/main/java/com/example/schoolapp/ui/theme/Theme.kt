package com.example.schoolapp.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Define your custom colors
val PrimaryColor = Color(0xFF6200EE)
val PrimaryVariantColor = Color(0xFF3700B3)
val SecondaryColor = Color(0xFF03DAC5)
val BackgroundColor = Color(0xFFF5F5F5)
val SurfaceColor = Color(0xFFFFFFFF)
val OnPrimaryColor = Color.White
val OnSecondaryColor = Color.Black
val OnBackgroundColor = Color.Black
val OnSurfaceColor = Color.Black

// Define your light color scheme
private val AppColorScheme = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = OnPrimaryColor,
    primaryContainer = PrimaryVariantColor,
    onPrimaryContainer = OnPrimaryColor,
    secondary = SecondaryColor,
    onSecondary = OnSecondaryColor,
    background = BackgroundColor,
    onBackground = OnBackgroundColor,
    surface = SurfaceColor,
    onSurface = OnSurfaceColor
)

// Apply the theme
@Composable
fun SchoolAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Typography, // Define your custom typography or use defaults
        shapes = Shapes,         // Define your custom shapes or use defaults
        content = content
    )
}
