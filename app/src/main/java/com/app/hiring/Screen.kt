package com.app.hiring

sealed class Screen(val route: String) {
    object HiringListScreen : Screen("hiring_list_screen")
}