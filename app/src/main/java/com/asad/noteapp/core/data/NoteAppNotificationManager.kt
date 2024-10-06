package com.asad.noteapp.core.data

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.asad.noteapp.R
import com.asad.noteapp.app.DEEP_LINK_BASE
import com.asad.noteapp.app.MainActivity
import javax.inject.Inject

class NoteAppNotificationManager @Inject constructor() {

    @SuppressLint("MissingPermission", "LaunchActivityFromNotification")
    fun createNotification(
        context: Context,
        id: Int,
        title: String,
        note: String
    ) {
        createNotificationChannel(context)

        val intentUri = "${DEEP_LINK_BASE}${id}/true".toUri()

        val intent = Intent(
            Intent.ACTION_VIEW,
            intentUri,
            context,
            MainActivity::class.java
        )

        val pendingIntentFlag = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S) {
            PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }

        val pendingIntent: PendingIntent = PendingIntent.getService(
            context,
            NotificationConstants.REQUEST_CODE,
            intent,
            pendingIntentFlag
        )

        val notification = NotificationCompat.Builder(context, NotificationConstants.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(note)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .build()

        if (checkPermissionIsGranted(context)) {
            NotificationManagerCompat.from(context).notify(1001, notification)
        }
    }

    private fun createNotificationChannel(
        context: Context,
        channelId: String = NotificationConstants.CHANNEL_ID,
        channelName: String = NotificationConstants.CHANNEL_NAME,
        channelDescription: String = NotificationConstants.CHANNEL_DESCRIPTION,
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = channelDescription
            }

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun checkPermissionIsGranted(context: Context): Boolean =
        if (Build.VERSION_CODES.TIRAMISU <= Build.VERSION.SDK_INT) {
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
}


