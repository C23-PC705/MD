package com.example.jimbro.ui.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Login : Screen("login")
    object Register : Screen("register")
    object Settings : Screen("settings")
    object Landing : Screen("landing")
    object Detail : Screen("detail/{index}") {
        fun createRoute(index: Int) = "detail/$index"
    }
    object  editBmi: Screen("EditBmi")
    object  editUser: Screen("EditUser")
    object  editPassword: Screen("editPassword")
}


