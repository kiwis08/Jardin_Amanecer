package com.cr7.jardinamanecer.ui.screens.level1.game2

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cr7.jardinamanecer.ui.screens.level1.DataBaseItem

@Composable
fun PartesCuerpoScreen2(
    viewModel: PartesViewModel,
    navController: NavController,
    itemId: String){

    Log.e("AnimalsScreen", "Entro")

    val images by viewModel.partesItemList.collectAsState<List<DataBaseItem>>()
    println("$images")

    val cant = images.size
    println("CANTIDAD $cant")

    val selectedItem = images.find { it.title == itemId }


    Column (modifier = Modifier.fillMaxSize()) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(images.size) { index ->
                Column(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 8.dp)
                        .clickable {
                            // Agrega la lógica que deseas al hacer clic en un elemento
                        }
                ) {
                    Row() {
                        Text(text = "Hola")

                    }
                }
            }
        }
    }
}
