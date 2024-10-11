package com.asad.noteapp.app

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.asad.noteapp.core.service.notification.CustomNotificationManager
import com.asad.noteapp.core.service.services.ReminderService
import com.asad.noteapp.core.service.services.SetAlarmWorker
import javax.inject.Inject

class CustomWorkerFactory @Inject constructor(
    private val notificationManager: CustomNotificationManager
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker =
//        SetAlarmWorker(
//            context = appContext,
//            workerParams = workerParameters,
//        )
        ReminderService(
            context = appContext,
            params = workerParameters,
            notificationManager = notificationManager
        )
}