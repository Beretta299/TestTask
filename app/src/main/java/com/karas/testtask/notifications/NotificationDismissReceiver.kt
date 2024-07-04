package com.karas.testtask.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.karas.testtask.data.PersonalDataManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

//@AndroidEntryPoint
class NotificationDismissReceiver: BroadcastReceiver() {
//    @Inject lateinit var notificationManager: NotificationManager
//    @Inject lateinit var personalDataManager: PersonalDataManager

    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)

    override fun onReceive(context: Context?, intent: Intent?) {
        // Handle notification dismissal
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
//               personalDataManager.onDismissed()
            }
            withContext(Dispatchers.Main) {
//                notificationManager.scheduleMessage(context)
            }
        }
    }

    private fun cleanup() {
        job.cancel()
    }

    @Throws(Throwable::class)
    protected fun finalize() {
        cleanup()
    }
}