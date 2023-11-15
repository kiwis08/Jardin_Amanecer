package com.cr7.jardinamanecer.navigation

sealed class Screens(val route: String) {
    object Start : Screens("start_screen")
    object AdminSignIn : Screens("admin_sign_in")
    object StudentSignIn : Screens("student_sign_in")
    object GameMenu : Screens("GameMenu")
    object Level1Game1 : Screens("level1_game1")
    object Level1Game2 : Screens("level1_game2")
    object Level1Game2_2 : Screens("level1_game2_2")

}