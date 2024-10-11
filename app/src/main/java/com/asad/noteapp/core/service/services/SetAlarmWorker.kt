package com.asad.noteapp.core.service.services

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.asad.noteapp.core.service.notification.util.NotificationConstants
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class SetAlarmWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {
    @SuppressLint("ScheduleExactAlarm")
    override suspend fun doWork(): Result {
        val noteId = inputData.getInt(NotificationConstants.ID_KEY, 0)
        val noteTitle = inputData.getString(NotificationConstants.TITLE_KEY)
        val noteDescription = inputData.getString(NotificationConstants.NOTE_KEY)
        val noteReminder = inputData.getLong(NotificationConstants.REMINDER_KEY, 0L)

        if (noteTitle != null && noteDescription != null) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, AlarmReceiver::class.java)
            intent.putExtra(NotificationConstants.ID_KEY, noteId)
            intent.putExtra(NotificationConstants.TITLE_KEY, noteTitle)
            intent.putExtra(NotificationConstants.NOTE_KEY, noteDescription)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                noteId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                noteReminder,
                pendingIntent
            )
        }

        return Result.success()
    }
}
