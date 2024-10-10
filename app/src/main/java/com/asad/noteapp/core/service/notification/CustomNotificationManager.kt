package com.asad.noteapp.core.service.notification

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.asad.noteapp.R
import com.asad.noteapp.app.navigation.DEEP_LINK_BASE
import com.asad.noteapp.app.MainActivity
import com.asad.noteapp.app.navigation.NavigationConstants
import com.asad.noteapp.core.service.notification.util.NotificationConstants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomNotificationManager @Inject constructor() {

    @SuppressLint("MissingPermission", "LaunchActivityFromNotification")
    fun showNotification(
        context: Context,
        id: Int,
        title: String,
        note: String
    ) {
        createNotificationChannel(context)

        val intentUri = Uri.parse(DEEP_LINK_BASE).buildUpon()
            .appendPath(NavigationConstants.NOTE_ID)
            .build()

        val intent = Intent(Intent.ACTION_VIEW, intentUri, context, MainActivity::class.java)

        val pendingIntentFlag = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S) {
            PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }

        val deepLinkPendingIntent: PendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(NotificationConstants.REQUEST_CODE, pendingIntentFlag)
        }

        val notification = NotificationCompat.Builder(context, NotificationConstants.CHANNEL_ID)
            .setSmallIcon(R.mipmap.note_app_launcher)
            .setContentTitle(title)
            .setContentText(note)
            .setAutoCancel(true)
            .setContentIntent(deepLinkPendingIntent)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setLights(Color.WHITE, 200, 200)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        if (checkPermissionIsGranted(context)) {
            NotificationManagerCompat.from(context).notify(id, notification)
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


