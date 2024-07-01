package com.vishal2376.scrollblock.presentation.analytics

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vishal2376.scrollblock.R
import com.vishal2376.scrollblock.domain.model.TimeWastedInfo
import com.vishal2376.scrollblock.presentation.analytics.components.SummaryItemComponent
import com.vishal2376.scrollblock.presentation.common.CustomPieChart
import com.vishal2376.scrollblock.presentation.common.descriptionStyle
import com.vishal2376.scrollblock.presentation.common.fontMontserrat
import com.vishal2376.scrollblock.presentation.common.h1style
import com.vishal2376.scrollblock.presentation.common.h2style
import com.vishal2376.scrollblock.presentation.home.components.PieChartIndicatorComponent
import com.vishal2376.scrollblock.ui.theme.ScrollBlockTheme
import com.vishal2376.scrollblock.ui.theme.pieChartColors
import com.vishal2376.scrollblock.ui.theme.white
import com.vishal2376.scrollblock.utils.formatTime

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

		val blackGradient = Brush.verticalGradient(
			listOf(
				MaterialTheme.colorScheme.primary,
				MaterialTheme.colorScheme.secondary
			)
		)

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
					.clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
					.background(blackGradient),
				horizontalAlignment = Alignment.CenterHorizontally,
			) {
				val timeWastedList = listOf(
					TimeWastedInfo("Instagram", 30000),
					TimeWastedInfo("Youtube", 13000),
					TimeWastedInfo("Linkedin", 5000),
					TimeWastedInfo("Snapchat", 1200)
				)

				val totalTimeWasted =
					timeWastedList.filter { it.timeWasted > 0 }.map { it.timeWasted }


				// pie chart
				Box(
					modifier = Modifier
						.clip(CircleShape)
						.clickable {
//							onNavigate(Screen.AnalyticScreen.name)
						},
					contentAlignment = Alignment.Center
				) {
					Column(
						modifier = Modifier.padding(top = 8.dp),
						horizontalAlignment = Alignment.CenterHorizontally
					) {
						Text(
							text = "This Week",
							textAlign = TextAlign.Center,
							style = descriptionStyle
						)
						Text(
							text = formatTime(totalTimeWasted.sum()),
							textAlign = TextAlign.Center,
							fontSize = 25.sp,
							fontFamily = fontMontserrat,
						)
					}
					CustomPieChart(
						data = totalTimeWasted, pieChartSize = 190.dp
					)
				}

				Spacer(modifier = Modifier.height(16.dp))

				// pie chart indicator
				LazyVerticalGrid(
					modifier = Modifier
						.fillMaxWidth()
						.padding(horizontal = 32.dp),
					columns = GridCells.Fixed(2),
					verticalArrangement = Arrangement.spacedBy(8.dp),
					horizontalArrangement = Arrangement.spacedBy(24.dp),
				) {
					items(timeWastedList) {
						PieChartIndicatorComponent(
							appName = it.name,
							time = it.timeWasted,
							color = pieChartColors[timeWastedList.indexOf(it) % pieChartColors.size]
						)
					}
				}

			}

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
					),
					verticalAlignment = Alignment.CenterVertically
				) {
					SummaryItemComponent(
						title = "Time\nWasted",
						info = "13h 40m",
						icon = R.drawable.clock,
						index = 1
					)
					SummaryItemComponent(
						title = "Total\nScrolls",
						info = "12.3k",
						icon = R.drawable.scroll,
						index = 2
					)
				}
				Row(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.spacedBy(
						8.dp,
						Alignment.CenterHorizontally
					),
					verticalAlignment = Alignment.CenterVertically
				) {
					SummaryItemComponent(
						title = "Scroll\nBlocked",
						info = "3.1k",
						icon = R.drawable.shield,
						index = 3
					)
					SummaryItemComponent(
						title = "App\nOpened",
						info = "1.8k",
						icon = R.drawable.dashboard,
						index = 4
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