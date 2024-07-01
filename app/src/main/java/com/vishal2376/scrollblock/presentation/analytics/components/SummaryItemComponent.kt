package com.vishal2376.scrollblock.presentation.analytics.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
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
	title: String, info: String, icon: Int, index: Int
) {
	var animationPlayed by remember { mutableStateOf(false) }
	val maxImgSize = 174.dp
	val itemRotation = -30f

	LaunchedEffect(key1 = Unit) {
		delay(200L * index)
		animationPlayed = true
	}

	val imgAlpha by animateFloatAsState(
		targetValue = if (animationPlayed) 1f else 0f,
		animationSpec = tween(400),
		label = "",
	)

	val imgSize by animateDpAsState(
		targetValue = if (animationPlayed) maxImgSize else 50.dp,
		animationSpec = spring(
			dampingRatio = Spring.DampingRatioMediumBouncy,
			stiffness = Spring.StiffnessLow
		), label = ""
	)

	val imgRotation by animateFloatAsState(
		targetValue = if (animationPlayed) 0f else itemRotation,
		animationSpec = spring(
			dampingRatio = Spring.DampingRatioMediumBouncy,
			stiffness = Spring.StiffnessLow
		), label = ""
	)

	Box(
		modifier = Modifier
			.width(imgSize)
			.scale(0.9f)
			.graphicsLayer {
				rotationZ = imgRotation
				alpha = imgAlpha
			}
	) {
		Image(
			painter = painterResource(R.drawable.summary_item),
			contentDescription = null,
			colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
		)
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
					modifier = Modifier.padding(start = 4.dp, top = 4.dp),
					text = title,
					style = summaryTitleStyle,
					color = MaterialTheme.colorScheme.primary
				)
				Icon(
					modifier = Modifier.padding(top = 8.dp),
					painter = painterResource(id = icon),
					tint = MaterialTheme.colorScheme.primary,
					contentDescription = null
				)
			}

			Text(
				modifier = Modifier
					.fillMaxWidth()
					.padding(bottom = 32.dp),
				text = info,
				style = summaryInfoStyle,
				textAlign = TextAlign.Center,
				color = MaterialTheme.colorScheme.primary
			)
		}
	}
}

@Preview
@Composable
private fun SummaryItemComponentPreview() {
	ScrollBlockTheme {
		SummaryItemComponent("Time\nWasted", "21h 32m", R.drawable.clock, 2)
	}
}