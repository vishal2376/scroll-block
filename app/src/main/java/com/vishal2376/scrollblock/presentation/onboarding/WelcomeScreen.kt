package com.vishal2376.scrollblock.presentation.onboarding

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vishal2376.scrollblock.R
import com.vishal2376.scrollblock.presentation.common.descriptionStyle
import com.vishal2376.scrollblock.presentation.common.h2style
import com.vishal2376.scrollblock.presentation.common.titleStyle
import com.vishal2376.scrollblock.presentation.navigation.Screen
import com.vishal2376.scrollblock.ui.theme.ScrollBlockTheme
import com.vishal2376.scrollblock.ui.theme.blue
import com.vishal2376.scrollblock.ui.theme.orange
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(onNavigate: (String) -> Unit) {

	var animationPlayed by remember { mutableStateOf(false) }

	val textAlpha by animateFloatAsState(
		targetValue = if (animationPlayed) 1f else 0f,
		label = "",
		animationSpec = tween(delayMillis = 200, durationMillis = 1000)
	)

	val imgSize by animateDpAsState(
		targetValue = if (animationPlayed) 300.dp else 100.dp, animationSpec = spring(
			dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow
		), label = ""
	)

	LaunchedEffect(Unit) {
		delay(100)
		animationPlayed = true
	}

	Scaffold(floatingActionButton = {
		FloatingActionButton(
			onClick = {
				onNavigate(Screen.PermissionScreen.name)
			},
			shape = CircleShape,
			containerColor = blue,
			contentColor = MaterialTheme.colorScheme.onPrimary
		) {
			Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
		}
	}) { innerPadding ->

		val blackGradient = Brush.verticalGradient(
			listOf(
				MaterialTheme.colorScheme.primary,
				MaterialTheme.colorScheme.secondary
			)
		)

		Column(
			Modifier
				.fillMaxSize()
				.padding(innerPadding)
				.background(blackGradient),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			Text(text = "Want to reduce", style = descriptionStyle)
			Spacer(modifier = Modifier.height(8.dp))
			Text(text = "Scroll Addiction ?", style = titleStyle, color = orange)

			Spacer(modifier = Modifier.height(40.dp))

			Image(
				modifier = Modifier.size(imgSize),
				painter = painterResource(id = R.drawable.onboarding),
				contentDescription = null
			)
			Spacer(modifier = Modifier.height(40.dp))

			Column(
				modifier = Modifier
					.fillMaxWidth()
					.graphicsLayer { alpha = textAlpha },
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Text(text = "Welcome to", style = h2style)
				Spacer(modifier = Modifier.height(24.dp))
				Text(text = "ScrollBlock", style = titleStyle, color = blue)
				Spacer(modifier = Modifier.height(8.dp))
				Text(text = "Regain Control  Boost Productivity", style = descriptionStyle)
			}
		}
	}
}

@Preview
@Composable
private fun WelcomeScreenPreview() {
	ScrollBlockTheme {
		WelcomeScreen({})
	}
}