package com.cr7.jardinamanecer.ui.screens.admin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.navigation.Screens
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val screenlists = listOf(
        R.drawable.admins,
        R.drawable.estudiantes,
    )

    val navigationItemList = listOf<AdminNavItem>(
        AdminNavItem(
            title = "Home",
            icon = Icons.Default.Home,
            description = "Home",
            itemId = "AdminHome"
        ),
        AdminNavItem(
            title = "Alumnos",
            icon = Icons.Default.Face,
            description = "Alumnos List",
            itemId = "AdminAlumnos"
        ),
        AdminNavItem(
            title = "Admin",
            icon = Icons.Default.AccountBox,
            description = "Admin",
            itemId = "AdminAdmins"
        ),
        AdminNavItem(
            title = "Configuracion",
            icon = Icons.Default.Settings,
            description = "Config",
            itemId = "AdminConfig"
        ),
    )

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Column (modifier = Modifier
        .paint(
            painterResource(id = R.drawable.menuscreen),
            contentScale = ContentScale.FillBounds

    )){


        //Scaffold
        androidx.compose.material.Scaffold(
            topBar = {
                MenuBar(toolbarTitle = "MENU", navController,
                    navigationIconClicked = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()

                        }

                    })

            },
            drawerContent = {
                NavigationDrawerHeader()
                NavigationDrawerBody(navigationDrawerItems = navigationItemList)
            }

        ) { paddingValues ->
            Surface(
                modifier = Modifier

                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues)
            ) {

                Text(text = "CONTENIDO PANTALLA PRINCIPAL", textAlign = TextAlign.Center)


            }
        }
    }
}

