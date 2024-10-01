package com.asad.noteapp.app.navigation

sealed class Screen(val route: String) {
    open fun onCreate(vararg args: String): String {
        return route
    }

    data object HomeScreen : Screen(route = NavigationConstant.HOME_SCREEN)

    data object AddNoteScreen : Screen(route = NavigationConstant.ADD_NOTE_SCREEN)
}