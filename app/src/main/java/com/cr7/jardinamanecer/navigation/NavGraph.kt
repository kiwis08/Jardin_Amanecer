package com.cr7.jardinamanecer.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cr7.jardinamanecer.ui.screens.AdminSignIn
import com.cr7.jardinamanecer.ui.screens.StartScreen
import com.cr7.jardinamanecer.ui.screens.StudentSignIn
import com.cr7.jardinamanecer.ui.screens.level4.ComunicatorScreen

@Composable
fun NavGraph (navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.Start.route)
    {
        composable(route = Screens.Start.route){
            StartScreen(navController)
        }
        composable(route = Screens.AdminSignIn.route){
            AdminSignIn(navController)
        }
        composable(route = Screens.StudentSignIn.route){
            StudentSignIn()
        }
        composable(route = Screens.Level4Game1.route){
            ComunicatorScreen()
        }

    }
}