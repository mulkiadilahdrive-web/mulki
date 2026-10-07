package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val KiperLightColorScheme = lightColorScheme(
  primary = TurquoisePrimary,
  onPrimary = PureWhite,
  primaryContainer = TurquoiseContainer,
  onPrimaryContainer = OnTurquoiseContainer,
  secondary = ForestGreen,
  onSecondary = PureWhite,
  secondaryContainer = GreenContainer,
  onSecondaryContainer = OnGreenContainer,
  tertiary = GoldPrimary,
  onTertiary = TextPrimary,
  tertiaryContainer = GoldContainer,
  onTertiaryContainer = GoldDark,
  background = CreamBackground,
  onBackground = TextPrimary,
  surface = PureWhite,
  onSurface = TextPrimary,
  surfaceVariant = SurfaceSoft,
  onSurfaceVariant = TextSecondary,
  outline = OutlineSoft,
  error = ErrorRed,
  errorContainer = ErrorContainer,
  onError = PureWhite
)

private val KiperDarkColorScheme = darkColorScheme(
  primary = TurquoiseLight,
  onPrimary = TurquoiseDark,
  primaryContainer = TurquoiseDark,
  onPrimaryContainer = TurquoiseContainer,
  secondary = LightGreen,
  onSecondary = ForestGreen,
  secondaryContainer = ForestGreen,
  onSecondaryContainer = GreenContainer,
  tertiary = GoldPrimary,
  onTertiary = TextPrimary,
  tertiaryContainer = GoldDark,
  onTertiaryContainer = GoldLight,
  background = Color(0xFF131D1B),
  onBackground = Color(0xFFE2EBE8),
  surface = Color(0xFF1C2724),
  onSurface = Color(0xFFE2EBE8),
  surfaceVariant = Color(0xFF263531),
  onSurfaceVariant = Color(0xFFB0C2BD),
  outline = Color(0xFF435853)
)

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false, // Keep branded Kiper educational colors consistent
  content: @Composable () -> Unit,
) {
  val colorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }
    darkTheme -> KiperDarkColorScheme
    else -> KiperLightColorScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}
