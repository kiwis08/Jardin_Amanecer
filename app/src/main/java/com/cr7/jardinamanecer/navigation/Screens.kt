package com.cr7.jardinamanecer.navigation

sealed class Screens(val route: String) {
    object Start : Screens("start_screen")
    object AdminSignIn : Screens("admin_sign_in")
    object StudentSignIn : Screens("student_sign_in")
    object Level1Game1 : Screens("level1_game1")

}