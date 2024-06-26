package com.vishal2376.scrollblock.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vishal2376.scrollblock.presentation.analytics.AnalyticScreen
import com.vishal2376.scrollblock.presentation.home.HomeScreen
import com.vishal2376.scrollblock.presentation.onboarding.PermissionScreen
import com.vishal2376.scrollblock.presentation.onboarding.WelcomeScreen
import com.vishal2376.scrollblock.presentation.viewmodels.MainViewModel
import com.vishal2376.scrollblock.utils.isAccessibilityServiceEnabled


@Composable
fun NavGraph(viewModel: MainViewModel) {
	val context = LocalContext.current
	val navController = rememberNavController()

	val todayAppUsage by viewModel.todayAppUsageList.collectAsState(initial = emptyList())
	val isServiceEnabled by remember {
		mutableStateOf(isAccessibilityServiceEnabled(context))
	}

	val startRoute = if (isServiceEnabled) {
		Screen.HomeScreen.name
	} else {
		Screen.WelcomeScreen.name
	}

	NavHost(
		navController = navController, startDestination = startRoute
	) {
		composable(Screen.HomeScreen.name) {
			HomeScreen(
				todayAppUsage,
				appState = viewModel.appState,
				onMainEvent = viewModel::onEvent,
				onNavigate = { route ->
					navController.navigate(route = route)
				})
		}

		composable(Screen.WelcomeScreen.name) {
			WelcomeScreen(onNavigate = { route ->
				navController.navigate(route = route) {
					popUpTo(Screen.WelcomeScreen.name) {
						inclusive = true
					}
				}
			})
		}

		composable(Screen.PermissionScreen.name) {
			PermissionScreen(onNavigate = { route ->
				navController.navigate(route = route) {
					popUpTo(Screen.PermissionScreen.name) {
						inclusive = true
					}
				}
			})
		}

		composable(Screen.AnalyticScreen.name) {
			AnalyticScreen(onBack = { navController.navigateUp() })
		}
	}
}