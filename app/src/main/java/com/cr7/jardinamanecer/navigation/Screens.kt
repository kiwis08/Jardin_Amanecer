package com.cr7.jardinamanecer.navigation

sealed class Screens(val route: String) {
    object Start : Screens("start_screen")
    object AdminSignIn : Screens("admin_sign_in")
    object StudentSignIn : Screens("student_sign_in")
    object GameMenu : Screens("GameMenu")
    object Level1Game1 : Screens("level1_game1")
    object Level1Game2 : Screens("level1_game2")
    object Level1Game3 : Screens("level1_game3")
    object Level4Game1 : Screens("level4_game1")
    object Level1Game22 : Screens("partesCuerpoScreen2")
    object Level3Game1 : Screens("level3_game1")
    object Level3Game2 : Screens("level3_game2")
    object Level3Game3 : Screens("level3_game3")
    object Level2Game1 : Screens("level2_game1")
    object Level2Game3 : Screens("level2_game3")
    object Level4Game2 : Screens("level4_game2")
    object Level4Game3 : Screens("level4_game3")
    object AdminStudentList : Screens("admin_student_list")
}
