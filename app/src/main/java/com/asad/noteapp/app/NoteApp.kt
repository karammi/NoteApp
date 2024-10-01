package com.asad.noteapp.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.asad.noteapp.app.navigation.NavigationConstant
import com.asad.noteapp.app.navigation.Screen
import com.asad.noteapp.features.home.presentation.screen.HomeScreen

@Composable
fun NoteApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {

        composable(route = Screen.HomeScreen.route) {
            HomeScreen()
        }

        composable(route = Screen.AddNoteScreen.route) {

        }
    }
}