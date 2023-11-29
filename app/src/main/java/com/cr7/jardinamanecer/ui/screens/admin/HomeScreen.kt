package com.cr7.jardinamanecer.ui.screens.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.navigation.Screens
import com.google.firebase.auth.FirebaseAuth


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, admin: Administrator) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val items = listOf(
        AdminNavItem(title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        AdminNavItem(title = "Alumnos",
            selectedIcon = Icons.Filled.Face,
            unselectedIcon = Icons.Outlined.Face,
        ),
        AdminNavItem(title = "Administradores",
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle,
        ),
        AdminNavItem(title = "Configuracion",
            selectedIcon = Icons.Filled.ExitToApp,
            unselectedIcon = Icons.Outlined.ExitToApp,
        ),
    )

    var topNavState by rememberSaveable {
        mutableStateOf(0)
    }

    Scaffold(
        topBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = topNavState == index,
                        onClick = { topNavState = index },
                        icon = {
                            Icon(
                                imageVector = if (topNavState == index) item.selectedIcon
                                else item.unselectedIcon
                                , contentDescription = item.title
                            )
                        }
                    )
                }
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val Pedro = Alumno(id = "1", nombre = "Pedro Suarez", edad = 10, Nivel1 = false, Nivel2 = false, Nivel3 = false, Nivel4 = true, imagen = R.drawable.nino)
            if (items[topNavState].title == "Configuracion") {
                FirebaseAuth.getInstance().signOut()
                navController.navigate(Screens.AdminSignIn.route)
            }
            when (items[topNavState].title) {
                "Alumnos" -> AlumnosScreen(navController = navController)
                "Administradores" -> AdminScreen()
                "Configuracion" -> AdminScreen()
                else -> DefaultScreen(admin)
            }
        }
    }
}


