package com.cr7.jardinamanecer.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.NavController
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cr7.jardinamanecer.ui.screens.AdminSignIn
import com.cr7.jardinamanecer.ui.screens.GameMenu
import com.cr7.jardinamanecer.ui.screens.Level1.game1.AnimalsScreen
import com.cr7.jardinamanecer.ui.screens.Level1.game1.AnimalsViewModel
import com.cr7.jardinamanecer.ui.screens.StartScreen
import com.cr7.jardinamanecer.ui.screens.StudentSignIn
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalFoundationApi::class)

@Composable
fun NavGraph (navController: NavHostController){
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 1 })
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
        composable(route = Screens.GameMenu.route){
            GameMenu(navController)
        }
        composable(route = Screens.Level1Game1.route) {
            val viewModel = AnimalsViewModel(state = pagerState, scope = coroutineScope)
            AnimalsScreen(viewModel = viewModel, navController = navController)
        }
    }
}