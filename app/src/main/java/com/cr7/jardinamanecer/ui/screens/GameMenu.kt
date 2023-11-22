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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.cr7.jardinamanecer.navigation.Screens

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameMenu(navController: NavController) {
    // Lista de juegos
    val gamelists = listOf(
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FJuegos%2FL1_Animales.png?alt=media&token=72240018-4a25-433f-ba55-644c70f841fd",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FJuegos%2FL1_PartesCuerpo.png?alt=media&token=26f7c3c2-9ff6-4782-bf6f-056ac04a3d0f",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FJuegos%2FL1_Instrumentos.png?alt=media&token=7cf2cfe2-c851-4628-9640-536f2a129e35",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FJuegos%2FL2_Colorea.png?alt=media&token=be5599a1-ba9c-4999-aca4-7583e9c2010c",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FJuegos%2FL3-OrdenaNumeros.png?alt=media&token=821b349a-36d8-4082-a026-5fa61c254bd9",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FJuegos%2FL4-Comunicador.png?alt=media&token=d3aba2fe-df3f-4290-a3a9-df2fa68d3460")
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
                                try {
                                    navController.navigate(Screens.Level1Game1.route)

                                }catch (e: Exception) {
                                    println("No funciono: ${e.message}")
                                }
                            }
                            1 -> {
                                navController.navigate(Screens.Level1Game2.route)
                                println("Juego 2")
                            }
                            2 -> {
                                navController.navigate(Screens.StudentSignIn.route)
                                println("Juego 3")
                            }
                            else -> {
                                println("Click en card de pagina $page")
                            }
                        }
                    }
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .scale(Scale.FILL)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

}
