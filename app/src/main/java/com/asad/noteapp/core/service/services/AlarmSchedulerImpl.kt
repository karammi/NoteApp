package com.asad.noteapp.core.service.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.asad.noteapp.core.service.model.AlarmModel
import com.asad.noteapp.core.service.notification.util.NotificationConstants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AlarmSchedulerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AlarmScheduler {

    override fun schedule(alarmModel: AlarmModel): Boolean {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!alarmManager.canScheduleExactAlarms()) {
                return false
            }
        }

        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(NotificationConstants.ID_KEY, alarmModel.id)
            putExtra(NotificationConstants.TITLE_KEY, alarmModel.title)
            putExtra(NotificationConstants.NOTE_KEY, alarmModel.note)
        }

        val pendingIntentFlag = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S) {
            PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context, alarmModel.id, intent, pendingIntentFlag
        )
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            alarmModel.alarmTime,
            pendingIntent
        )
        return true
    }

    override fun cancel(alarmModel: AlarmModel) {
    }
}


