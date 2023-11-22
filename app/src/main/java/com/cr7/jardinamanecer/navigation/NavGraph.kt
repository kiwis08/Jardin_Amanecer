package com.cr7.jardinamanecer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cr7.jardinamanecer.ui.screens.AdminSignIn
import com.cr7.jardinamanecer.ui.screens.GameMenu
import com.cr7.jardinamanecer.ui.screens.StartScreen
import com.cr7.jardinamanecer.ui.screens.StudentSignIn
import com.cr7.jardinamanecer.ui.screens.level4.view.ComunicatorScreen
import com.cr7.jardinamanecer.ui.screens.level1.game1.AnimalViewModel
import com.cr7.jardinamanecer.ui.screens.level1.game1.AnimalsScreen
import com.cr7.jardinamanecer.ui.screens.level1.game2.PartesCuerpoScreen
import com.cr7.jardinamanecer.ui.screens.level1.game2.PartesViewModel
import com.cr7.jardinamanecer.ui.screens.level3.ImageDragAndDropAnimales
import com.cr7.jardinamanecer.ui.screens.level3.ImageDragAndDropColores
import com.cr7.jardinamanecer.ui.screens.level3.ImageDragAndDropNumeros

@Composable
fun NavGraph (navController: NavHostController){
    val coroutineScope = rememberCoroutineScope()

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
            ComunicatorScreen(navController = navController)
        }
        composable(route = Screens.GameMenu.route){
            GameMenu(navController)
        }
        composable(route = Screens.Level1Game1.route) {
            val viewModel = AnimalViewModel( scope = coroutineScope)
            AnimalsScreen(viewModel = viewModel, navController = navController)
        }
        composable(route = Screens.Level1Game2.route){
            val viewModelPartes = PartesViewModel(scope = coroutineScope)
            PartesCuerpoScreen(viewModelPartes,navController)
        }
        composable(route = Screens.Level3Game1.route){
            ImageDragAndDropNumeros(navController = navController)
        }
        composable(route = Screens.Level3Game2.route){
            ImageDragAndDropAnimales(navController = navController)
        }
        composable(route = Screens.Level3Game3.route){
            ImageDragAndDropColores(navController = navController)
        }
    }
}