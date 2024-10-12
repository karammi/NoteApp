package com.asad.noteapp.core.service.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.asad.noteapp.core.service.model.AlarmModel
import com.asad.noteapp.core.service.notification.util.NotificationConstants
import com.asad.noteapp.core.service.receiver.AlarmReceiver
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AlarmSchedulerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AlarmScheduler {

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    override fun schedule(alarmModel: AlarmModel) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!alarmManager.canScheduleExactAlarms()) {
                return
            }
        }

        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(NotificationConstants.ID_KEY, alarmModel.id)
            putExtra(NotificationConstants.TITLE_KEY, alarmModel.title)
            putExtra(NotificationConstants.NOTE_KEY, alarmModel.note)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            alarmModel.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            alarmModel.alarmTime,
            pendingIntent
        )
    }

    override fun cancel(alarmModel: AlarmModel) {
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            alarmModel.hashCode(),
            Intent(context, AlarmReceiver::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }
}


