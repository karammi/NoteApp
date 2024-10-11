package com.asad.noteapp.core.service.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.asad.noteapp.core.service.notification.CustomNotificationManager
import com.asad.noteapp.core.service.notification.util.NotificationConstants
import javax.inject.Inject

class AlarmReceiver @Inject constructor(
    private val notificationManager: CustomNotificationManager
) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val noteId = intent.getIntExtra(NotificationConstants.ID_KEY, 0)
        val noteTitle = intent.getStringExtra(NotificationConstants.TITLE_KEY)
        val noteDescription = intent.getStringExtra(NotificationConstants.NOTE_KEY)

        notificationManager.showNotification(
            context = context,
            id = noteId,
            title = noteTitle ?: "",
            note = noteDescription ?: ""
        )
    }
}