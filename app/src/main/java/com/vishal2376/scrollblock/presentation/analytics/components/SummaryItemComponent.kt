package com.vishal2376.scrollblock.presentation.analytics.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vishal2376.scrollblock.R
import com.vishal2376.scrollblock.presentation.common.summaryInfoStyle
import com.vishal2376.scrollblock.presentation.common.summaryTitleStyle
import com.vishal2376.scrollblock.ui.theme.ScrollBlockTheme
import kotlinx.coroutines.delay

@Composable
fun SummaryItemComponent(
	title: String, info: String, icon: Int
) {
	var isVisible by remember { mutableStateOf(false) }

	val random = (1..4).random()

	val scaleAnim by animateFloatAsState(
		targetValue = if (isVisible) 0.9f else 0.1f,
		animationSpec = spring(
			dampingRatio = Spring.DampingRatioMediumBouncy,
			stiffness = Spring.StiffnessLow
		), label = ""
	)

	LaunchedEffect(key1 = Unit) {
		delay(100L * random)
		isVisible = true
	}

	Box(
		modifier = Modifier
			.width(174.dp)
			.scale(scaleAnim)
			.graphicsLayer {
				alpha = scaleAnim
			}
	) {
		Image(painter = painterResource(R.drawable.summary_item), contentDescription = null)
		Column(
			modifier = Modifier
				.matchParentSize()
				.padding(horizontal = 15.dp),
			verticalArrangement = Arrangement.SpaceBetween
		) {
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(vertical = 8.dp),
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Text(
					modifier = Modifier.padding(start = 8.dp, top = 4.dp),
					text = title,
					style = summaryTitleStyle
				)
				Icon(
					modifier = Modifier.padding(top = 8.dp),
					painter = painterResource(id = icon),
					tint = Color.Black,
					contentDescription = null
				)
			}

			Text(
				modifier = Modifier
					.fillMaxWidth()
					.padding(bottom = 32.dp),
				text = info,
				style = summaryInfoStyle,
				textAlign = TextAlign.Center
			)
		}
	}
}

@Preview
@Composable
private fun SummaryItemComponentPreview() {
	ScrollBlockTheme {
		SummaryItemComponent("Time\nWasted", "21h 32m", R.drawable.clock)
	}
}