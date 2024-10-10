package com.asad.noteapp.app.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.asad.noteapp.features.note.presentatoin.screen.NoteScreen
import com.asad.noteapp.features.home.presentation.screen.HomeScreen
import com.asad.noteapp.theme.RequestPermission

const val DEEP_LINK_BASE = "open://noteapp"

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NoteApp() {
    val navController = rememberNavController()

    RequestPermission()

    /**
     * this function type is used to navigate to [Screen.NoteScreen]
     * */
    val onNoteClicked: (Int?) -> Unit = { noteId ->
        navController.navigate(Screen.NoteScreen.onCreateRoute(noteId ?: 0))
    }

    val onBackClicked: () -> Unit = {
        navController.navigateUp()
    }

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                onAddNoteClicked = { onNoteClicked(0) },
                onNoteClicked = onNoteClicked
            )
        }
        composable(
            route = Screen.NoteScreen.route,
            arguments = listOf(
                navArgument(
                    name = NavigationConstants.NOTE_ID,
                    builder = {
                        type = NavType.IntType
                        defaultValue = 0
                        nullable = false
                    }
                )
            ),
            deepLinks = listOf(navDeepLink {
                uriPattern = "$DEEP_LINK_BASE/{${NavigationConstants.NOTE_ID}}"
            })
        ) { navBackStateEntry ->
            val noteId = navBackStateEntry.arguments?.getInt(NavigationConstants.NOTE_ID) ?: 0
            NoteScreen(
                id = noteId,
                onBackClicked = onBackClicked
            )
        }
    }
}

