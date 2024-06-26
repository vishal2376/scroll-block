package com.vishal2376.scrollblock.presentation.analytics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vishal2376.scrollblock.R
import com.vishal2376.scrollblock.presentation.analytics.components.SummaryItemComponent
import com.vishal2376.scrollblock.presentation.common.h1style
import com.vishal2376.scrollblock.presentation.common.h2style
import com.vishal2376.scrollblock.ui.theme.ScrollBlockTheme
import com.vishal2376.scrollblock.ui.theme.blackGradient
import com.vishal2376.scrollblock.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticScreen(onBack: () -> Unit) {
	Scaffold(topBar = {
		TopAppBar(colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
			title = {
				Text(
					text = "Analytics", style = h2style
				)
			},
			navigationIcon = {
				IconButton(onClick = {
					onBack()
				}) {
					Icon(
						imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null
					)
				}
			})

	}) { innerPadding ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.verticalScroll(rememberScrollState())
				.background(MaterialTheme.colorScheme.primary)
				.padding(innerPadding),
			verticalArrangement = Arrangement.spacedBy(16.dp),
		) {
			Column(
				Modifier
					.fillMaxWidth()
					.height(350.dp)
					.padding(top = 24.dp)
					.clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
					.background(blackGradient),
				horizontalAlignment = Alignment.CenterHorizontally,
			) { }

			Row(
				modifier = Modifier.padding(horizontal = 16.dp),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.spacedBy(8.dp)
			) {
				Icon(
					imageVector = Icons.Default.BarChart,
					contentDescription = null,
					tint = white
				)
				Text(text = "Summary", style = h1style)
			}
			Column(
				modifier = Modifier.fillMaxWidth(),
				verticalArrangement = Arrangement.spacedBy(8.dp),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Row(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.spacedBy(
						8.dp,
						Alignment.CenterHorizontally
					)
				) {
					SummaryItemComponent(
						title = "Time\nWasted",
						info = "21h 23m",
						icon = R.drawable.clock
					)
					SummaryItemComponent(
						title = "Total\nScrolls",
						info = "12.3k",
						icon = R.drawable.scroll
					)
				}
				Row(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.spacedBy(
						8.dp,
						Alignment.CenterHorizontally
					)
				) {
					SummaryItemComponent(
						title = "Scroll\nBlocked",
						info = "3.1k",
						icon = R.drawable.shield
					)
					SummaryItemComponent(
						title = "App\nOpened",
						info = "1.8k",
						icon = R.drawable.dashboard
					)
				}
			}
		}
	}
}

@Preview
@Composable
private fun AnalyticScreenPreview() {
	ScrollBlockTheme {
		AnalyticScreen({})
	}
}