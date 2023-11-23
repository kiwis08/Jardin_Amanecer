package com.cr7.jardinamanecer.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.navigation.Screens

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameMenu(navController: NavController) {
    // Lista de juegos
    val gamelists = listOf(
        R.drawable.l1_animales,
        R.drawable.l1_partes_cuerpo,
        R.drawable.l1_instrumentos,
        R.drawable.l2_figurines,
        R.drawable.l2_colorea,
        R.drawable.l2_alistate,
        R.drawable.l3_ordena_numeros,
        R.drawable.l3_relaciona,
        R.drawable.l3_vocales,
        R.drawable.l4_comunicador,
        R.drawable.l4_memorama,
        R.drawable.l4_rompecabezas
    )
    println("Lista de juegos $gamelists")



    LazyRow(
        modifier = Modifier.fillMaxSize(),
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 5.dp, vertical = 0.dp)
    ) {
        items(gamelists.size) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        when (page) {
                            0 -> {
                                navController.navigate(Screens.Level1Game1.route)
                            }

                            1 -> {
                                navController.navigate(Screens.Level1Game2.route)
                            }

                            2 -> {
                                try {
                                    navController.navigate(Screens.Level1Game3.route)
                                } catch (e: Exception) {
                                    println("No funciono: ${e.message}")

                                }
                            }

                            3 -> {
                                navController.navigate(Screens.Level2Game1.route)
                            }

                            4 -> {
                                // TODO: Navigate to Level 2 Game 2
                            }

                            5 -> {
                                navController.navigate(Screens.Level2Game3.route)
                            }

                            6 -> {
                                navController.navigate(Screens.Level3Game1.route)
                            }

                            7 -> {
                                navController.navigate(Screens.Level3Game2.route)
                            }

                            8 -> {
                                navController.navigate(Screens.Level3Game3.route)
                            }

                            9 -> {
                                navController.navigate(Screens.Level4Game1.route)
                            }

                            10 -> {
                                navController.navigate(Screens.Level4Game2.route)
                            }

                            11 -> {
                                // TODO: Navigate to Level 4 Game 3
                            }

                            3 -> {
                                println("Click en card de pagina $page")
                            }

                            4 -> {
                                println("Juego 4")
                            }

                            5 -> {
                                navController.navigate(Screens.Level4Game1.route)
                                println("Juego 4")
                            }


                        }
                    }
            ) {
                Image(
                    painter = painterResource(id = gamelists[page]),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                    )
            }
        }
    }

}
