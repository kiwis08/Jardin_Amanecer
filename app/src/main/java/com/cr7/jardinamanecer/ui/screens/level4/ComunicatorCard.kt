package com.cr7.jardinamanecer.ui.screens.level4

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.cr7.jardinamanecer.model.level4.ComunicatorItem
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComunicatorCard(modifier: Modifier = Modifier, item: ComunicatorItem, onClick: () -> Unit) {
    val imageFile = File(item.imageUrl)
    var imgBitmap: Bitmap? = null
    if (imageFile.exists()) {
        // on below line we are creating an image bitmap variable
        // and adding a bitmap to it from image file.
        imgBitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
    }
    Card(
        onClick = onClick,
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = item.title)
            //            Image(painter = rememberAsyncImagePainter(model = imageFile), contentDescription = item.title)
            AsyncImage(
                model = item.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }
}


@Preview
@Composable
fun ComunicatorCardPreview() {
    ComunicatorCard(item = ComunicatorItem("Lápiz", "https://chedrauimx.vtexassets.com/arquivos/ids/20944211-800-auto?v=638337192895700000&width=800&height=auto&aspect=true")) {

    }
}