package com.cr7.jardinamanecer.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.cr7.jardinamanecer.navigation.Screens

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameMenu(navController: NavController) {
    // Lista de juegos
    val gamelists = listOf(
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FJuegos%2FL1_Animales.png?alt=media&token=72240018-4a25-433f-ba55-644c70f841fd",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FJuegos%2FL1_PartesCuerpo.png?alt=media&token=26f7c3c2-9ff6-4782-bf6f-056ac04a3d0f",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FJuegos%2FL1_Instrumentos.png?alt=media&token=7cf2cfe2-c851-4628-9640-536f2a129e35",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FJuegos%2FL2_Figurines.png?alt=media&token=8756776e-ea56-4efc-afb3-a6c692b85234",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FJuegos%2FL2_Colorea.png?alt=media&token=be5599a1-ba9c-4999-aca4-7583e9c2010c",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FJuegos%2FL2_Alistate.png?alt=media&token=aa33a18c-a1ad-4088-b570-3de0650cf3be",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FJuegos%2FL3-OrdenaNumeros.png?alt=media&token=821b349a-36d8-4082-a026-5fa61c254bd9",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FJuegos%2FL3_Relaciona.png?alt=media&token=171ac591-9417-40a7-b1e0-1cdf47d2fe36",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FJuegos%2FL3_Vocales.png?alt=media&token=01348a40-a91a-4fd5-a10e-437eab8e68c9",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FJuegos%2FL4-Comunicador.png?alt=media&token=d3aba2fe-df3f-4290-a3a9-df2fa68d3460",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FJuegos%2FL4_Memorama.png?alt=media&token=d41de9be-d2dc-410e-9cbe-ac8d33655ead",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FJuegos%2FL4_Rompecabezas.png?alt=media&token=ca815e8e-d408-4693-85ae-d0f598b357ea",
    )
    println("Lista de juegos $gamelists")



    LazyRow(
        modifier = Modifier.fillMaxSize(),
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 5.dp, vertical = 0.dp)
    ) {
        items(gamelists.size) { page ->
            val imageUrl = gamelists[page]
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
                                }catch (e: Exception) {
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
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

}
