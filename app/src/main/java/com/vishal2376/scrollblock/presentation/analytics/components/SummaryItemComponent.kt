package com.vishal2376.scrollblock.presentation.analytics.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vishal2376.scrollblock.R
import com.vishal2376.scrollblock.presentation.common.fontMontserrat
import com.vishal2376.scrollblock.presentation.common.fontMontserratRegular
import com.vishal2376.scrollblock.ui.theme.ScrollBlockTheme

@Composable
fun SummaryItemComponent(modifier: Modifier = Modifier) {
	Box(modifier = modifier.width(174.dp)) {
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
					modifier = Modifier.padding(start = 8.dp),
					text = "Time\nWasted", fontSize = 18.sp,
					fontFamily = fontMontserratRegular,
					fontWeight = FontWeight.SemiBold,
					color = Color.Black,
				)
				Icon(
					modifier = Modifier.padding(top = 6.dp),
					imageVector = Icons.Default.AccessTime,
					tint = Color.Black,
					contentDescription = null
				)
			}

			Text(
				modifier = Modifier
					.fillMaxWidth()
					.padding(bottom = 32.dp),
				text = "21h 31m",
				fontSize = 30.sp,
				fontFamily = fontMontserrat,
				color = Color.Black,
				textAlign = TextAlign.Center
			)
		}
	}
}

@Preview
@Composable
private fun SummaryItemComponentPreview() {
	ScrollBlockTheme {
		SummaryItemComponent()
	}
}