package com.vishal2376.scrollblock.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.vishal2376.scrollblock.presentation.common.AppTheme

private val DarkColorScheme = darkColorScheme(
	primary = black500, secondary = black200, onPrimary = white, onSecondary = gray
)

private val AmoledColorScheme = darkColorScheme(
	primary = black900,
	secondary = black700,
	onPrimary = white,
	onSecondary = gray
)

private val LightColorScheme = lightColorScheme(
	primary = black500, secondary = black200, onPrimary = white, onSecondary = gray

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
fun ScrollBlockTheme(
	theme: AppTheme = AppTheme.AMOLED,
	content: @Composable () -> Unit
) {
	val colorScheme = when (theme) {
		AppTheme.LIGHT -> LightColorScheme
		AppTheme.DARK -> DarkColorScheme
		AppTheme.AMOLED -> AmoledColorScheme
	}

	MaterialTheme(
		colorScheme = colorScheme, typography = Typography, content = content
	)
}