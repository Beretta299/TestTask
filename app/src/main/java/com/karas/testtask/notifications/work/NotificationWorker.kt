package com.karas.testtask.notifications.work

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.karas.testtask.R
import com.karas.testtask.db.dao.BootsDao
import com.karas.testtask.db.data.BootsEntity
import com.karas.testtask.notifications.NotificationDismissReceiver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NotificationWorker(val dao: BootsDao,context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        return try {
            val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val dismissIntent = Intent(applicationContext, NotificationDismissReceiver::class.java)
            val dismissPendingIntent = PendingIntent.getBroadcast(applicationContext, 0, dismissIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            var notification: Notification
            withContext(Dispatchers.IO) {
                notification = NotificationCompat.Builder(applicationContext, "boot_counter_channel")
                    .setContentTitle("Boot Counter")
                    .setContentText(getNotificationContent())
                    .setSmallIcon(R.drawable.ic_notification)
                    .setDeleteIntent(dismissPendingIntent)
                    .build()
            }

            notificationManager.notify(1, notification)
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    private suspend fun getNotificationContent(): String {
        // Retrieve boot events from the database and generate content
        val bootEvents = dao.getAll()
        return when (bootEvents.size) {
            0 -> "No boots detected"
            1 -> "The boot was detected = ${formatDate(bootEvents[0].timeStamp)}"
            else -> "Last boots time delta = ${getTimeDelta(bootEvents)}"
        }
    }

    private fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }

    private fun getTimeDelta(bootEvents: List<BootsEntity>): String {
        if (bootEvents.size < 2) return "N/A"
        val last = bootEvents[0].timeStamp
        val preLast = bootEvents[1].timeStamp
        val delta = last - preLast
        val seconds = delta / 1000
        return "$seconds seconds"
    }
}