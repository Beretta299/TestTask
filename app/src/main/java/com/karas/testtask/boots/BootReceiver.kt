package com.karas.testtask.boots

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.karas.testtask.db.dao.BootsDao
import com.karas.testtask.db.data.BootsEntity
import com.karas.testtask.notifications.NotificationManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

//@AndroidEntryPoint
class BootReceiver : BroadcastReceiver() {

    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)
//
//    @Inject lateinit var notificationManager: NotificationManager
//    @Inject lateinit var bootsDao: BootsDao


    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            coroutineScope.launch {
                withContext(Dispatchers.IO) {
//                    bootsDao.insert(BootsEntity(null, System.currentTimeMillis()))
                }
                withContext(Dispatchers.Main) {
//                    notificationManager.scheduleMessage(context)
                }
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