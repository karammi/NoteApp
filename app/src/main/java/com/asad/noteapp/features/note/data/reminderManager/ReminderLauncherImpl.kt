package com.asad.noteapp.features.note.data.reminderManager

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.asad.noteapp.core.service.notification.util.NotificationConstants
import com.asad.noteapp.core.service.services.ReminderService
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReminderLauncherImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ReminderLauncher {
    override fun setReminder(noteId: Int, noteTitle: String, noteBody: String?) {
        val data = workDataOf(
            NotificationConstants.ID_KEY to noteId,
            NotificationConstants.TITLE_KEY to noteTitle,
            NotificationConstants.NOTE_KEY to noteBody
        )

        val workRequest = OneTimeWorkRequestBuilder<ReminderService>()
            .setInitialDelay(10, TimeUnit.SECONDS)
            .setInputData(data)
            .build()

        WorkManager.getInstance(context).enqueue(workRequest)
    }
}