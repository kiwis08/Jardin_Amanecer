package com.cr7.jardinamanecer.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.navigation.Screens

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameMenu(navController: NavController) {
    // Lista de juegos
    val gamelists = listOf(
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FPrueba%2FL1_Animales.png?alt=media&token=01b6c0cd-835a-46f9-a747-5a857f8df1d3",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FPrueba%2FL1_Instrumentos.png?alt=media&token=c525ded9-fd92-4a10-8f23-e343f939bbfd",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FPrueba%2FL1_PartesCuerpo.png?alt=media&token=22ff52a1-9847-4b6e-9ee6-16aed14bc7df",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FPrueba%2FL2_Alistate.png?alt=media&token=0a5e3cf5-62c0-4f32-bfd1-14f287541b39",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FPrueba%2FL2_Colorea.png?alt=media&token=95f413c5-fa5a-4134-be68-e4a5f5e5b0be",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FPrueba%2FL2_Figurines.png?alt=media&token=6f71e215-b189-463b-b3e9-95f958d917e6",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FPrueba%2FL3-OrdenaNumeros.png?alt=media&token=4d6b3a46-6c6d-485c-8f0a-9c047c59dabc",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FPrueba%2FL3_Relaciona.png?alt=media&token=8e7eab7d-6935-4ef5-8bcd-b8219dc86229",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FPrueba%2FL3_Vocales.png?alt=media&token=2e6bfb5e-9dd0-49ca-a7df-3d3f946d8de0",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FPrueba%2FL4-Comunicador.png?alt=media&token=8efdc18f-3024-410f-9471-0231566ce112",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FPrueba%2FL4_Memorama.png?alt=media&token=0e177aef-6f95-4db9-bcf9-66441cc2aa1f",
        "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%2FPrueba%2FL4_Rompecabezas.png?alt=media&token=8bd43fac-ae58-46e1-b7b3-4201056f625d"
    )
    println("Lista de juegos $gamelists")



    Box(modifier = Modifier
        .paint(painterResource(id = R.drawable.menuscreen),
            contentScale = ContentScale.FillBounds)
    ) {



            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                state = rememberLazyListState(),
                contentPadding = PaddingValues(horizontal = 5.dp, vertical = 0.dp),

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
                                        // TODO: Navigate to Level 1 Game 3
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

                                    else -> {
                                        println("Click en card de pagina $page")
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

        Button(
            onClick = {
                navController.navigate(Screens.Start.route)
            },
            modifier = Modifier
                .offset(x = 103.dp, y = 50.dp)
                .wrapContentSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.regresar),
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp)
            )
        }
    }

}
