package com.asad.noteapp.core.service.services

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.asad.noteapp.core.service.notification.util.NotificationConstants
import com.asad.noteapp.core.service.notification.CustomNotificationManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class ReminderService @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
    @Assisted private val notificationManager: CustomNotificationManager
) : Worker(context, params) {

    override fun doWork(): Result {
        val notificationId = inputData.getInt(NotificationConstants.ID_KEY, 0)
        val notificationTitle = inputData.getString(NotificationConstants.TITLE_KEY) ?: ""
        val notificationDescription = inputData.getString(NotificationConstants.NOTE_KEY) ?: ""

        notificationManager.showNotification(
            context = context,
            id = notificationId,
            title = notificationTitle,
            note = notificationDescription
        )
        return Result.success()
    }
}
