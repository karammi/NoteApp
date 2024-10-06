package com.asad.noteapp.app

import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.asad.noteapp.app.navigation.Screen
import com.asad.noteapp.features.note.presentatoin.screen.NoteScreen
import com.asad.noteapp.features.home.presentation.screen.HomeScreen

const val DEEP_LINK_BASE = "open://noteapp/"

@Composable
fun NoteApp() {
    val navController = rememberNavController()

    /**
     * this function type is used to navigate to [Screen.AddNoteScreen]
     * */

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        RequestPermission()
    }

    val onAddNoteClicked: () -> Unit = {
        navController.navigate(Screen.AddNoteScreen.route)
    }

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(onAddNoteClicked = onAddNoteClicked)
        }

        composable(route = Screen.AddNoteScreen.route) {
            NoteScreen(onBackClicked = {})
        }
    }
}

@Composable
private fun RequestPermission() {
    val launcher: ManagedActivityResultLauncher<String, Boolean> =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
            } else {
            }
        }

    when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(
            LocalContext.current,
            android.Manifest.permission.POST_NOTIFICATIONS
        ),
        -> {
            // Some works that require permission
        }

        else -> {
            // Asking for permission
            SideEffect {
                launcher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

}
