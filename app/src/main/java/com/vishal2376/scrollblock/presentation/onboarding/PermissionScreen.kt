package com.vishal2376.scrollblock.presentation.onboarding


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vishal2376.scrollblock.R
import com.vishal2376.scrollblock.presentation.common.fontRoboto
import com.vishal2376.scrollblock.presentation.common.h1style
import com.vishal2376.scrollblock.presentation.common.h3style
import com.vishal2376.scrollblock.presentation.common.headlineStyle
import com.vishal2376.scrollblock.presentation.common.smallDescriptionStyle
import com.vishal2376.scrollblock.presentation.navigation.Screen
import com.vishal2376.scrollblock.ui.theme.ScrollBlockTheme
import com.vishal2376.scrollblock.ui.theme.blue
import com.vishal2376.scrollblock.ui.theme.red
import com.vishal2376.scrollblock.ui.theme.white
import com.vishal2376.scrollblock.utils.isAccessibilityServiceEnabled
import com.vishal2376.scrollblock.utils.openAccessibilitySettings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionScreen(onNavigate: (String) -> Unit) {
	val context = LocalContext.current

	Scaffold(topBar = {
		TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
			containerColor = Color.Transparent
		), title = {
			Text(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 8.dp),
				text = "Enable Permissions",
				style = h1style,
				color = white
			)
		})
	}, floatingActionButton = {
		FloatingActionButton(
			onClick = {
				if (isAccessibilityServiceEnabled(context)) {
					onNavigate(Screen.HomeScreen.name)
				} else {
					Toast.makeText(context, "Enable Accessibility Service", Toast.LENGTH_SHORT)
						.show()
				}
			},
			shape = CircleShape,
			containerColor = blue,
			contentColor = MaterialTheme.colorScheme.onPrimary
		) {
			Icon(
				imageVector = Icons.AutoMirrored.Default.ArrowForward, contentDescription = null
			)
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
				.verticalScroll(rememberScrollState())
				.background(blackGradient)
				.padding(innerPadding)
				.padding(horizontal = 24.dp),
			verticalArrangement = Arrangement.spacedBy(8.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {

			// accessibility service
			Column(modifier = Modifier.fillMaxWidth()) {
				Text(
					modifier = Modifier.padding(top = 8.dp),
					text = "1. Accessibility Service",
					style = headlineStyle,
					color = blue
				)
				Text(text = stringResource(R.string.service_desc), style = smallDescriptionStyle)
				Spacer(modifier = Modifier.height(16.dp))
				Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
					val textStyle = MaterialTheme.typography.bodyLarge.copy(
						fontFamily = fontRoboto, color = white
					)

					instructionSteps.forEach { step ->
						Text(text = step, style = textStyle)
					}
				}
			}
			Spacer(modifier = Modifier.height(8.dp))
			Button(
				onClick = { openAccessibilitySettings(context) },
				colors = ButtonDefaults.buttonColors(
					containerColor = MaterialTheme.colorScheme.onPrimary,
					contentColor = MaterialTheme.colorScheme.primary
				),
			) {
				Text(text = "Accessibility Settings")
			}
			Column {
				Text(
					text = "2. Optional Settings",
					style = headlineStyle,
					color = blue,
					modifier = Modifier.padding(top = 16.dp)
				)
				Text(
					text = "If you skip below steps, you might need to enable permissions manually each day",
					color = red,
					style = smallDescriptionStyle
				)
				Spacer(modifier = Modifier.height(8.dp))
				Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
					optionalSettings.forEach {
						Spacer(modifier = Modifier.height(8.dp))
						Text(text = it.title, style = h3style, fontWeight = FontWeight.Bold)
						Text(text = it.reason, style = smallDescriptionStyle)
					}
				}
			}
		}
	}
}

@Preview
@Composable
private fun PermissionScreenPreview() {
	ScrollBlockTheme {
		PermissionScreen({})
	}
}