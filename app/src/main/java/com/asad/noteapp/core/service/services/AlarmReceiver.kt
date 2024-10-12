package com.asad.noteapp.core.service.services

import android.content.Context
import android.content.Intent
import com.asad.noteapp.core.service.notification.CustomNotificationManager
import com.asad.noteapp.core.service.notification.util.NotificationConstants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint()
class AlarmReceiver : HiltBroadcastReceiver() {

    @Inject
    lateinit var notificationManager: CustomNotificationManager

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
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
