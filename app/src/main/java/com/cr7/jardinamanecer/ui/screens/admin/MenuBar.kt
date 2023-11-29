package com.cr7.jardinamanecer.ui.screens.admin

import android.annotation.SuppressLint
import android.icu.text.CaseMap.Title
import android.telephony.TelephonyCallback
import android.view.Menu
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.navigation.Screens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Scope

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuBar(toolbarTitle: String, navController: NavController,
            navigationIconClicked : () -> Unit){

    TopAppBar(
        title = {
                Text(text = "MENU")
        },
        navigationIcon = {
            IconButton(onClick = {
                navigationIconClicked.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu ,
                    contentDescription = "Home"
                )
            }

        },
        actions = {
            IconButton(onClick = { navController.navigate(Screens.AdminHome.route) }) {
                Icon(imageVector = Icons.Filled.Home,
                    contentDescription = "Home")
            }

            IconButton(onClick = { navController.navigate(Screens.AdminAlumnos.route) }) {
                Icon(imageVector = Icons.Filled.Face,
                    contentDescription = "Alumnos")
            }

            IconButton(onClick = { navController.navigate(Screens.AdminAdmins.route) }) {
                Icon(imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Admins")
            }

            IconButton(onClick = { navController.navigate(Screens.AdminConfig.route) }) {
                Icon(imageVector = Icons.Filled.Settings,
                    contentDescription = "Configuracion")
            }

        }
    )



}

@Composable
fun NavigationDrawerHeader(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(32.dp)){
        Text(text = "Home")
    }
        
}

@Composable
fun NavigationDrawerBody(navigationDrawerItems: List<AdminNavItem>){
    LazyColumn(modifier = Modifier.fillMaxWidth()){

        items(navigationDrawerItems){

            NavigationItemRow(item = it)
        }
    }
}

@Composable
fun NavigationItemRow(item: AdminNavItem){
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(all = 16.dp)){

        Icon(
            imageVector = item.icon,
            contentDescription = item.description
        )
        
        Spacer(modifier = Modifier.width(18.dp))
        
        Text(text = item.title)



    }
}