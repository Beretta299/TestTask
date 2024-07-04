package com.karas.testtask.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.karas.testtask.data.PersonalDataManager
import com.karas.testtask.notifications.work.NotificationWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

interface NotificationManager {
    suspend fun scheduleMessage(context: Context?)
}

class NotificationManagerImplementation(private val personalDataManager: PersonalDataManager): NotificationManager {
    override suspend fun scheduleMessage(context: Context?) {
        var intervalData = 15L
        withContext(Dispatchers.IO) {
            intervalData = personalDataManager.getIntervalData()
        }
        val periodicWorkRequest = PeriodicWorkRequestBuilder<NotificationWorker>(intervalData, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(context!!).enqueueUniquePeriodicWork(
            "NotificationWork",
            ExistingPeriodicWorkPolicy.UPDATE,
            periodicWorkRequest
        )
    }

}