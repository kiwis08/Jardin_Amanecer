package com.cr7.jardinamanecer.ui.screens.level1.game1

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.navigation.Screens
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimalsScreen(viewModel: AnimalViewModel, navController : NavController) {
    Log.e("AnimalsScreen", "Entro")

    val images = viewModel.animales
    println("IMAGENES MAIN$images")

    val state = rememberPagerState(pageCount = { images.size })

    val scope = rememberCoroutineScope()


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box {
            HorizontalPager(state = state) { page ->
                Log.e("HorizontalPager", "Entra al pager")

                //val backgroundColors = listOf(
                //Color(234, 4, 126, 255),
                //Color(255, 109, 40),
                //Color(252, 231, 0),
                //Color(38, 171, 226),
                //Color(185, 49, 252),
                //Color(255, 23, 0),
                //)
                //val backgroundColorIndex = page % backgroundColors.size

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        //.background(backgroundColors[backgroundColorIndex])
                        .paint(
                            painterResource(id = R.drawable.granja),
                            contentScale = ContentScale.FillBounds
                        )
                        .background(
                            color = Color(0xFF000000).copy(alpha = 0.5f),

                        )
                ) {


                    //Encabezado(Blanco)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .weight(1f)
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
                            text = "Animales",
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp, start = 15.dp)
                        )

                    }

                    // Flechas y Animales
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .weight(6f)
                            .fillMaxWidth()
                            .fillMaxHeight(3f),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        // Flecha-boton-previews
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f),
                            //.background(backgroundColors[backgroundColorIndex]),
                            contentAlignment = Alignment.CenterStart

                        ) {
                            if (state.currentPage != 0) {
                                Image(
                                    painter = painterResource(id = R.drawable.row_back),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(160.dp)
                                        .height(160.dp)
                                        .padding(start = 40.dp)
                                        .clickable {
                                            scope.launch {
                                                state.scrollToPage(state.currentPage - 1)
                                            }
                                        }
                                )
                            }
                        }

                        // Animales
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(3f),
                            //.background(backgroundColors[backgroundColorIndex]),
                            contentAlignment = Alignment.Center

                        ) {
                            val currentAnimalResourceId =
                                images.getOrNull(state.currentPage.toInt())
                            currentAnimalResourceId?.let { imageId ->
                                val currentAnimalName =
                                    viewModel.nombresAnimales.getOrNull(state.currentPage.toInt())
                                        ?: ""
                                AnimalDisp(imageId, currentAnimalName, viewModel)
                            }
                        }

                        // Felcha-boton-next
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f),
                            //.background(backgroundColors[backgroundColorIndex]),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            if (state.currentPage != images.size - 1) {
                                Image(
                                    painter = painterResource(id = R.drawable.row_next),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(160.dp)
                                        .height(160.dp)
                                        .padding(end = 40.dp)
                                        .clickable {
                                            scope.launch {
                                                state.scrollToPage(state.currentPage + 1)
                                            }
                                            println("Pagina:${state.currentPage}")


                                        }
                                )
                            }

                        }
                    }
                }

                //Puntos de progreso de paginas
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        //.weight(1f)
                        .padding(top = 20.dp, bottom = 60.dp),
                    horizontalArrangement = Arrangement.Center

                ) {

                    repeat(images.size) {
                        val color =
                            if (state.currentPage == it) Color.White else Color.White.copy(
                                alpha = 0.3f
                            )
                        Box(
                            modifier = Modifier
                                .size(15.dp)
                                .clip(CircleShape)
                                .background(color)
                                .padding(8.dp)
                        )
                    }
                }

            }
        }

    }
}


