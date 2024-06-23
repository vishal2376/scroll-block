package com.vishal2376.scrollblock.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import androidx.core.app.NotificationCompat
import com.vishal2376.scrollblock.R

const val CHANNEL_ID = "scrollblock-notification"
const val NOTIFICATION_ID = 200
const val CHANNEL_NAME = "Accessibility Service"

class NotificationHelper(private val context: Context) {

	private val notificationManager =
		context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

	fun buildNotification(): Notification {

		return NotificationCompat.Builder(context, CHANNEL_ID)
			.setSmallIcon(R.drawable.ic_notification)
			.setContentTitle("Scroll Block is Active")
			.setContentText("Accessibility Service is running in background")
			.setPriority(NotificationCompat.PRIORITY_DEFAULT)
			.setAutoCancel(true)
			.build()
	}

	fun createNotificationChannel() {
		val mChannel =
			NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
		notificationManager.createNotificationChannel(mChannel)
	}
}
