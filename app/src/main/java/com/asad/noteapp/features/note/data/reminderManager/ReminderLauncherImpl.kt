package com.asad.noteapp.features.note.data.reminderManager

import android.content.Context
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.asad.noteapp.core.service.notification.util.NotificationConstants
import com.asad.noteapp.core.service.services.ReminderService
import com.asad.noteapp.core.service.services.SetAlarmWorker
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReminderLauncherImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ReminderLauncher {
    override fun setReminder(noteId: Int, noteTitle: String, noteBody: String?, reminder: Long) {
        val data = workDataOf(
            NotificationConstants.ID_KEY to noteId,
            NotificationConstants.TITLE_KEY to noteTitle,
            NotificationConstants.NOTE_KEY to noteBody,
            NotificationConstants.REMINDER_KEY to reminder
        )

        /*      val workRequest = OneTimeWorkRequest.Builder(SetAlarmWorker::class.java)
                  .setInputData(data)
                  .setInitialDelay(10, TimeUnit.MILLISECONDS)
                  .build()

              WorkManager.getInstance(context).enqueue(workRequest)*/

        val workRequest = OneTimeWorkRequestBuilder<ReminderService>()
            .setInitialDelay(10, TimeUnit.SECONDS)
            .setInputData(data)
            .build()

        WorkManager.getInstance(context).enqueue(workRequest)
    }
}