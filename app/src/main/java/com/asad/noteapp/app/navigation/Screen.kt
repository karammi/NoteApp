package com.asad.noteapp.app.navigation

sealed class Screen(val route: String) {
    open fun onCreateRoute(vararg args: Int): String {
        return route
    }

    data object HomeScreen : Screen(route = NavigationConstants.HOME_SCREEN)

    data object NoteScreen : Screen(route = "${NavigationConstants.NOTE_SCREEN}/{${NavigationConstants.NOTE_ID}}") {
        override fun onCreateRoute(vararg args: Int): String {
            var route = NavigationConstants.NOTE_SCREEN
            if (args.isNotEmpty()) {
                route += "/${args[0]}"
            }
            return route
        }
    }
}