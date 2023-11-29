package com.cr7.jardinamanecer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cr7.jardinamanecer.ui.screens.AdminSignIn
import com.cr7.jardinamanecer.ui.screens.menu.GameMenu
import com.cr7.jardinamanecer.ui.screens.StartScreen
import com.cr7.jardinamanecer.ui.screens.admin.AdminScreen
import com.cr7.jardinamanecer.ui.screens.admin.Administrator
import com.cr7.jardinamanecer.ui.screens.admin.Alumno
import com.cr7.jardinamanecer.ui.screens.admin.AlumnosScreen
import com.cr7.jardinamanecer.ui.screens.admin.ConfigScreen
import com.cr7.jardinamanecer.ui.screens.admin.HomeScreen
import com.cr7.jardinamanecer.ui.screens.admin.PerfilAlumno
import com.cr7.jardinamanecer.ui.screens.admin.Student
import com.cr7.jardinamanecer.ui.screens.level1.game1.AnimalViewModel
import com.cr7.jardinamanecer.ui.screens.level1.game1.AnimalsScreen
import com.cr7.jardinamanecer.ui.screens.level1.game2.PartesCuerpoScreen
import com.cr7.jardinamanecer.ui.screens.level1.game2.PartesCuerpoScreen2
import com.cr7.jardinamanecer.ui.screens.level1.game2.PartesViewModel
import com.cr7.jardinamanecer.ui.screens.level1.game3.InstrumntosScreen
import com.cr7.jardinamanecer.ui.screens.level2.game1.Figurines
import com.cr7.jardinamanecer.ui.screens.level2.game2.Color
import com.cr7.jardinamanecer.ui.screens.level2.game3.Clothing
import com.cr7.jardinamanecer.ui.screens.level3.ImageDragAndDropAnimales
import com.cr7.jardinamanecer.ui.screens.level3.ImageDragAndDropColores
import com.cr7.jardinamanecer.ui.screens.level3.ImageDragAndDropNumeros
import com.cr7.jardinamanecer.ui.screens.level4.view.ComunicatorScreen
import com.cr7.jardinamanecer.ui.screens.level4.view.MemoryScreen
import com.cr7.jardinamanecer.ui.screens.level4.view.PuzzleScreen
import com.google.firebase.firestore.auth.User
import kotlinx.serialization.json.Json


@Composable
fun NavGraph (navController: NavHostController, sessionSaved: Boolean) {
    val coroutineScope = rememberCoroutineScope()
    val viewModelPartes: PartesViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = if (sessionSaved) Screens.GameMenu.route else Screens.Start.route)
    {
        composable(route = Screens.Start.route){
            StartScreen(navController)
        }
        composable(route = Screens.AdminSignIn.route){
            AdminSignIn(navController)
        }
        composable(route = Screens.Level4Game1.route){
            ComunicatorScreen(navController = navController)
        }
        composable(route = Screens.Level4Game2.route){
            MemoryScreen(navController = navController)
        }
        composable(route = Screens.GameMenu.route){
            GameMenu(navController)
        }
        composable(route = Screens.Level1Game1.route) {
            val viewModel = AnimalViewModel( scope = coroutineScope)
            AnimalsScreen(viewModel = viewModel, navController = navController)
        }
        composable(route = Screens.Level1Game2.route){
            PartesCuerpoScreen(viewModelPartes,navController)
        }
        composable(route = Screens.Level1Game3.route){
            InstrumntosScreen(navController = navController)
        }
        composable(route = Screens.Level1Game22.route) {
            PartesCuerpoScreen2(navController, viewModelPartes)
        }
        composable(route = Screens.Level3Game1.route) {
            ImageDragAndDropNumeros(navController = navController)
        }
        composable(route = Screens.Level3Game3.route) {
            ImageDragAndDropAnimales(navController = navController)
        }
        composable(route = Screens.Level3Game2.route) {
            ImageDragAndDropColores(navController = navController)
        }
        composable(route = Screens.Level2Game1.route){
            Figurines(navController = navController)
        }
        composable(route = Screens.Level2Game2.route){
            Color(navController = navController)
        }
        composable(route = Screens.Level2Game3.route){
            Clothing(navController = navController)
        }
        composable(route = Screens.Level4Game3.route){
            PuzzleScreen(navController = navController)
        }

        //Admin
        composable(route = Screens.AdminHome.route + "/{admin}"){
            val adminJson = it.arguments?.getString("admin")
            val admin = Json.decodeFromString(Administrator.serializer(), adminJson!!)
            HomeScreen(navController = navController, admin = admin)
        }
        composable(route = Screens.AdminAlumnos.route){
            AlumnosScreen(navController = navController)
        }
        composable(route = Screens.AdminAdmins.route){
            AdminScreen()
        }
        composable(route = Screens.AdminConfig.route){
            ConfigScreen()
        }
        composable(route = Screens.AdminPerfilAlumno.route + "/{student}"){
            val userJson = it.arguments?.getString("student")
            // Convert json string to the User data class object
            val student = Json.decodeFromString(Alumno.serializer(), userJson!!)
            PerfilAlumno(navController, student)
        }

    }
}