package com.cr7.jardinamanecer.ui.screens.level1.game2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun PartesCuerpoScreen2(navController: NavController, viewModel: PartesViewModel) {

    val index_actual = viewModel.index_glo
    println("SCREEN2 - INDEX $index_actual")

    val imagenes = viewModel.getImagesForIndex(viewModel.index_glo)
    println("$imagenes")


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val ind = viewModel.imagenes_ind
        items(2) { imagen ->
            println("INDEX en IMAGENES  $imagen")
            DisImages(image = imagenes[imagen])
        }
    }
}

@Composable
fun DisImages(image: Int) {

    var isZoomed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row {
            val painter = rememberAsyncImagePainter(
                model = image,
                contentScale = ContentScale.Crop,
                filterQuality = FilterQuality.High
            )
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .width(if (isZoomed) 1000.dp else 800.dp)
                    .height(if (isZoomed) 1000.dp else 900.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .clickable {
                        isZoomed = !isZoomed
                    }
            )
        }
    }
}
