package com.asad.noteapp.theme

import android.Manifest
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
private const val POST_NOTIFICATIONS_PERMISSION = Manifest.permission.POST_NOTIFICATIONS

@Composable
fun RequestPermission(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    val context = LocalContext.current

    // Permission launcher for POST_NOTIFICATIONS permission
    val requestPermissionLauncher: ManagedActivityResultLauncher<String, Boolean> =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                onPermissionGranted()
            } else {
                onPermissionDenied()
            }
        }

    // SideEffect to check and request permissions
    SideEffect {
        val permissionsToRequest = mutableListOf<String>()

        // Check if POST_NOTIFICATIONS permission is granted
        if (ContextCompat.checkSelfPermission(
                context, POST_NOTIFICATIONS_PERMISSION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionsToRequest.add(POST_NOTIFICATIONS_PERMISSION)
        }

        // Handle exact alarm permission for Android 12 (API level 31) and higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            if (!alarmManager.canScheduleExactAlarms()) {
                // Open settings screen to ask the user to allow exact alarms
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                ContextCompat.startActivity(context, intent, null)
            }
        }

        // Launch permission request if required
        permissionsToRequest.forEach { permission ->
            requestPermissionLauncher.launch(permission)
        }
    }
}


//@RequiresApi(Build.VERSION_CODES.TIRAMISU)
//private const val POST_NOTIFICATIONS_PERMISSION = Manifest.permission.POST_NOTIFICATIONS
//private const val ALARM_MANAGER_PERMISSION = Manifest.permission.SET_ALARM
//
//@RequiresApi(Build.VERSION_CODES.TIRAMISU)
//@Composable
//fun RequestPermission() {
//    val context = LocalContext.current
//    val requestPermissionLauncher: ManagedActivityResultLauncher<String, Boolean> =
//        rememberLauncherForActivityResult(
//            ActivityResultContracts.RequestPermission()
//        ) { isGranted: Boolean ->
//            if (isGranted) {
//                // Permission granted: Do something
//            } else {
//                // Permission denied:
//            }
//        }
//
//    SideEffect {
//        val permissionsToRequest = mutableListOf<String>()
//        if (ContextCompat.checkSelfPermission(
//                context, POST_NOTIFICATIONS_PERMISSION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            permissionsToRequest.add(POST_NOTIFICATIONS_PERMISSION)
//        }
//        if (ContextCompat.checkSelfPermission(
//                context, ALARM_MANAGER_PERMISSION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            permissionsToRequest.add(ALARM_MANAGER_PERMISSION)
//        }
//
//
//        permissionsToRequest.forEach { permission ->
//            requestPermissionLauncher.launch(permission)
//        }
//    }
//}