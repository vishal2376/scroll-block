package com.vishal2376.scrollblock.services

import android.accessibilityservice.AccessibilityService
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.vishal2376.scrollblock.data.local.AppUsageDao
import com.vishal2376.scrollblock.data.local.SummaryDao
import com.vishal2376.scrollblock.domain.model.AppUsage
import com.vishal2376.scrollblock.utils.NOTIFICATION_ID
import com.vishal2376.scrollblock.utils.NotificationHelper
import com.vishal2376.scrollblock.utils.SettingsStore
import com.vishal2376.scrollblock.utils.SupportedApps
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject
import kotlin.math.max

@AndroidEntryPoint
class ScrollAccessibility : AccessibilityService() {
	@Inject
	lateinit var appUsageDao: AppUsageDao

	@Inject
	lateinit var summaryDao: SummaryDao

	@Inject
	lateinit var store: SettingsStore

	private var isInstagramDisabled = true
	private var isYoutubeDisabled = true
	private var isLinkedinDisabled = true
	private var isSnapchatDisabled = true

	companion object {
		private const val MIN_VALID_SCROLL_COUNT = 3
		private const val MIN_VALID_TIME_SPENT = 5
	}

	private var appStatus = mapOf(
		SupportedApps.Instagram to isInstagramDisabled,
		SupportedApps.Youtube to isYoutubeDisabled,
		SupportedApps.YoutubeRevanced to isYoutubeDisabled,
		SupportedApps.YoutubeRevancedExtended to isYoutubeDisabled,
		SupportedApps.Linkedin to isLinkedinDisabled,
		SupportedApps.Snapchat to isSnapchatDisabled
	)

	private var currentIndex = 0
	private var startTime = 0
	private var endTime = 0

	// App Usage Info
	private var appPackageName = ""
	private var appScrollCount = 0
	private var appTimeSpent = 0
	private var appOpenCount = 0
	private var appScrollBlocked = 0

	private val serviceScope = CoroutineScope(Dispatchers.IO + Job())
	private val supportedApps = listOf(
		SupportedApps.Instagram,
		SupportedApps.Youtube,
		SupportedApps.Linkedin,
		SupportedApps.YoutubeRevanced,
		SupportedApps.YoutubeRevancedExtended,
		SupportedApps.Snapchat
	)

	override fun onServiceConnected() {
		super.onServiceConnected()
		startServiceAndForeground()
	}

	override fun onAccessibilityEvent(event: AccessibilityEvent?) {
		event?.let {
			// Detect Window Changes
			if (event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {

				serviceScope.launch {
					appStatus = mapOf(
						SupportedApps.Instagram to store.instagramKey.first(),
						SupportedApps.Youtube to store.youtubeKey.first(),
						SupportedApps.YoutubeRevanced to store.youtubeKey.first(),
						SupportedApps.YoutubeRevancedExtended to store.youtubeKey.first(),
						SupportedApps.Linkedin to store.linkedinKey.first(),
						SupportedApps.Snapchat to store.snapchatKey.first()
					)
				}

				if (isValidAppUsage()) {
					// Calculate App Usage
					endTime = LocalTime.now().toSecondOfDay()
					appTimeSpent = max(0, endTime - startTime)
					appOpenCount++

					// Save App Usage in DB
					saveAppUsage()
				}
			}

			supportedApps.forEach {
				if (event.packageName == it.packageName) {
					appPackageName = it.packageName

					// Detect targeted content
					val viewId = "${it.packageName}:id/${it.blockId}"
					val blockContent =
						rootInActiveWindow?.findAccessibilityNodeInfosByViewId(viewId)

					// Detect Scrolling
					if (blockContent != null) {
						if (blockContent.isNotEmpty() && (event.eventType == AccessibilityEvent.TYPE_VIEW_SCROLLED || event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED)) {
							if (!appStatus[it]!!) {
								// Start Scrolling time
								if (startTime == 0) {
									startTime = LocalTime.now().toSecondOfDay()
								}

								// Detect single scroll per content
								if (currentIndex != event.fromIndex) {
									appScrollCount++
									currentIndex = event.fromIndex
								}
							} else {
								performGlobalAction(GLOBAL_ACTION_BACK)
								Toast.makeText(
									this@ScrollAccessibility, "Feature Blocked", Toast.LENGTH_SHORT
								).show()
							}
						}
					}
				}
			}
		}
	}

	private fun isValidAppUsage(): Boolean {
		val currentTime = LocalTime.now().toSecondOfDay()
		val isValidTimeSpent = startTime != 0 && ((currentTime - startTime) >= MIN_VALID_TIME_SPENT)
		val isValidScrollCount = appScrollCount >= MIN_VALID_SCROLL_COUNT

		return appPackageName.isNotEmpty() && (isValidTimeSpent || isValidScrollCount)
	}

	private fun saveAppUsage() {

		val appUsage = AppUsage(
			packageName = appPackageName,
			scrollCount = appScrollCount,
			timeSpent = appTimeSpent,
			appOpenCount = appOpenCount,
			scrollsBlocked = appScrollBlocked
		)

		serviceScope.launch {
			appUsageDao.insertAppUsage(appUsage)
			resetAppUsage()
		}
	}

	private fun resetAppUsage() {
		appPackageName = ""
		appScrollCount = 0
		appTimeSpent = 0
		appOpenCount = 0
		appScrollBlocked = 0

		startTime = 0
		endTime = 0
	}

	private fun startServiceAndForeground() {
		val intent = Intent(this, ScrollAccessibility::class.java)
		ContextCompat.startForegroundService(this, intent)

		Handler(Looper.getMainLooper()).postDelayed({
			val notificationHelper = NotificationHelper(this@ScrollAccessibility)
			startForeground(NOTIFICATION_ID, notificationHelper.buildNotification())
		}, 1000)
	}

	override fun onInterrupt() {
		stopForeground(Service.STOP_FOREGROUND_REMOVE)
	}

	override fun onDestroy() {
		super.onDestroy()
		stopForeground(Service.STOP_FOREGROUND_REMOVE)
	}
}