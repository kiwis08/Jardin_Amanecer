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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.navigation.Screens


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PartesCuerpoScreen( viewModel: PartesViewModel, navController : NavController) {
    Log.e("AnimalsScreen", "Entro")

    val images by viewModel.partesItemList.collectAsState<List<PartesItem>>()
    println("$images")

    val cant = images.size
    println("CANTIDAD $cant")



    val state = rememberPagerState(pageCount = { images.size})


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box {
            HorizontalPager(state = state) { page ->
                Log.e("HorizontalPager", "Entra al pager")

                val backgroundColors = listOf(

                    Color(185, 49, 252),
                    Color(234, 4, 126, 255),
                    Color(255, 109, 40),
                    Color(252, 231, 0),
                    Color(38, 171, 226),
                    Color(255, 23, 0),
                )
                val backgroundColorIndex = page % backgroundColors.size

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColors[backgroundColorIndex])
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
                            text = "Partes del Cuerpo",
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

                        Elementos(images)
                        //CONTENIDOoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Elementos(cards: List<PartesItem>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(cards.size) { index ->
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable {
                        // Agrega la lógica que deseas al hacer clic en un elemento
                    }
            ) {
                Row {
                    val painter = rememberAsyncImagePainter(
                        model = cards[index].imageUrl,
                        contentScale = ContentScale.Crop,
                        filterQuality = FilterQuality.High
                    )
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .width(500.dp)
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.background)
                    )

                    // Texto debajo de la imagen
                    /*Text(
                        text = cards[index].name.substringBeforeLast('.').toLowerCase(),
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                        modifier = Modifier.padding(top = 8.dp)
                    )*/
                }
                

            }
        }
    }
}