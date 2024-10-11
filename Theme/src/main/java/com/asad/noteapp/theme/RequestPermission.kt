package com.asad.noteapp.theme

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
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
private const val ALARM_MANAGER_PERMISSION = Manifest.permission.SET_ALARM

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun RequestPermission() {
    val context = LocalContext.current
    val requestPermissionLauncher: ManagedActivityResultLauncher<String, Boolean> =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission granted: Do something
            } else {
                // Permission denied:
            }
        }

    SideEffect {
        val permissionsToRequest = mutableListOf<String>()
        if (ContextCompat.checkSelfPermission(
                context, POST_NOTIFICATIONS_PERMISSION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionsToRequest.add(POST_NOTIFICATIONS_PERMISSION)
        }
        if (ContextCompat.checkSelfPermission(
                context, ALARM_MANAGER_PERMISSION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionsToRequest.add(ALARM_MANAGER_PERMISSION)
        }

        permissionsToRequest.forEach { permission ->
            requestPermissionLauncher.launch(permission)
        }
        /*  if (ContextCompat.checkSelfPermission(
                  context, POST_NOTIFICATIONS_PERMISSION
              ) != PackageManager.PERMISSION_GRANTED
          ) {
              requestPermissionLauncher.launch(POST_NOTIFICATIONS_PERMISSION)
          }*/
    }
}