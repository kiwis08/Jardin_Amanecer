package com.cr7.jardinamanecer.ui.screens.level1.game2

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.navigation.Screens


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PartesCuerpoScreen(viewModel: PartesViewModel = viewModel(), navController : NavController) {
    Log.e("AnimalsScreen", "Entro")

    val images = viewModel.imagenes
    println("$images")

    val state = rememberPagerState(pageCount = { images.size })


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box {
            HorizontalPager(state = state) { page ->
                Log.e("HorizontalPager", "Entra al pager")


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(185, 49, 252))
                ) {


                    //Encabezado(Blanco)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .height(100.dp),
                    ) {
                        //Boton hace pantalla anterior
                        Image(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = null,
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp)
                                .padding(16.dp)
                                .clickable {
                                    navController.navigate(Screens.GameMenu.route)
                                }
                        )

                        // Título
                        Text(
                            text = "Partes del Cuerpo",
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp, start = 15.dp)
                        )

                    }

                    // Cards
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //Dispaly de las tarjetas de los animales
                        Elementos(images, navController, viewModel)

                        }
                    }
                }
            
        }
    }
}

    @Composable
    fun Elementos(images: List<Int>, navController: NavController, viewModel: PartesViewModel) {
        println("Entro a ELEMENTOS")


        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(images.size) { index ->
                println("IMAGENS $images")
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 2.dp)
                        .fillMaxSize()

                ) {

                    Row {
                        val painter = rememberAsyncImagePainter(
                            model = images[index],
                            contentScale = ContentScale.Crop,
                            filterQuality = FilterQuality.High
                        )
                        //Tarjetas generales
                        Image(
                            painter = painter,
                            contentDescription = null,
                            modifier = Modifier
                                .width(800.dp)
                                .height(300.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.background)
                                .clickable {
                                    viewModel.setIndex(index)
                                    val prueba = viewModel.index_glo
                                    try {
                                        println("PRUEBA INDEX $prueba")
                                        navController.navigate(Screens.Level1Game22.route)
                                    } catch (e: Exception) {
                                        println("No funciono: ${e.message}")
                                        println("INDEX: $index")

                                    }


                                }
                        )

                    }
                }
            }
        }
    }

