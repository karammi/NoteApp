package com.asad.noteapp.core.data

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

/**
 * Perform create and sending notification
 * */
@HiltWorker
class ReminderWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
    @Assisted private val notificationManager: NoteAppNotificationManager
) : Worker(context, params) {

    override fun doWork(): Result {
        val notificationId = inputData.getInt(NotificationConstants.ID_KEY, 0)
        val notificationTitle = inputData.getString(NotificationConstants.TITLE_KEY) ?: ""
        val notificationDescription = inputData.getString(NotificationConstants.NOTE_KEY) ?: ""

        notificationManager.createNotification(
            context = context,
            id = notificationId,
            title = notificationTitle,
            note = notificationDescription
        )
        return Result.success()
    }
}
