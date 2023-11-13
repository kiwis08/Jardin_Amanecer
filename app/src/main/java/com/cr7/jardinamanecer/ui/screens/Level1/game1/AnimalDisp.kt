package com.cr7.jardinamanecer.ui.screens.Level1.game1


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.cr7.jardinamanecer.ui.screens.Level1.game1.AnimalsItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalDisp(item: AnimalsItem, onClick: () -> Unit) {
    var isZoomed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        //Imagen del animal
        Log.d("Image URL", "URL: ${item.imageUrl}")

        val painter = rememberAsyncImagePainter(
            model = item.imageUrl)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .width(if (isZoomed) 500.dp else 400.dp)
                .height(if (isZoomed) 800.dp else 500.dp)
                .padding(top = 60.dp)
                .clickable {
                    isZoomed = !isZoomed
                }
        )

        /*Text(
            text = item.title,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
            modifier = Modifier
                .weight(40f)
                .padding(bottom = 40.dp, top = 20.dp)
        )*/

    }

    @Composable
    fun AnimalNow(animal: AnimalsItem) {
        println("Entra a la funcion")

        animal.imageUrl?.let {
            val painter: Painter = rememberAsyncImagePainter(model = it)

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

        }

    }


}




@Preview
@Composable
fun AnimalDispPreview() {
    val animalsItem = AnimalsItem(
        title = "La vaca",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/jardinamanecer-ade5c.appspot.com/o/Karla%20Jacome%2FAnimales%2Flavaca.png?alt=media&token=7acacfdc-4cde-4f2e-b417-aa5e3e679d95"
    )

    AnimalDisp(item = animalsItem, onClick = {})
}
