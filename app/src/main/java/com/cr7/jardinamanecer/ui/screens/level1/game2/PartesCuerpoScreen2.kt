package com.cr7.jardinamanecer.ui.screens.level1.game2

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.navigation.Screens

@Composable
fun PartesCuerpoScreen2(navController: NavController, viewModel: PartesViewModel) {

    val index_actual = viewModel.index_glo
    println("SCREEN2 - INDEX $index_actual")

    val imagenes = viewModel.getImagesForIndex(viewModel.index_glo)
    println("$imagenes")



    Column (
        modifier = Modifier.fillMaxSize()
    ){
        //Encabezado(Blanco)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .height(120.dp),
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
                        navController.navigate(Screens.Level1Game2.route)
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

        //Tarjetas individuales
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .background(Color(234, 4, 126, 255))
            ) {


            val ind = viewModel.imagenes_ind

            items(2) { imagen ->
                println("INDEX en IMAGENES  $imagen")

                DisImages(image = imagenes[imagen],index = imagen, indexgen = index_actual, viewModel )
            }
        }

    }


}

@Composable
fun DisImages(image: Int, index: Int, indexgen :Int, viewModel: PartesViewModel) {

    var isZoomed by remember { mutableStateOf(false) }
    val context = LocalContext.current


    val backgroundColors = listOf(
        Color(234, 4, 126, 255),
        Color(255, 109, 40),
        Color(252, 231, 0),
        Color(38, 171, 226),
        Color(185, 49, 252),
        Color(255, 23, 0),
    )

    Column(
        modifier = Modifier
            .fillMaxHeight()
        ) {

        // Imagenes de las partes del cuerpo
        Row(
            verticalAlignment = Alignment.Top
        ) {
            val painter = rememberAsyncImagePainter(
                model = image,
                contentScale = ContentScale.Crop,
                filterQuality = FilterQuality.High
            )
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .width(if (isZoomed) 3000.dp else 800.dp)
                    .height(1800.dp) // Set a fixed height
                    .padding(horizontal = 100.dp, vertical = if (isZoomed) 60.dp else 0.dp) // Adjust padding
                    .clip(RoundedCornerShape(8.dp))
                    .background(backgroundColors[0])
                    .clickable {
                        println("IMAGEN CLICK $index")
                        isZoomed = !isZoomed
                        try {
                            viewModel.textToSpeech(context, indexgen,index)
                        }catch (e: Exception) {
                            println("No funciono: ${e.message}")

                        }
                    },
            )
        }
    }
}

