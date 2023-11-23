package com.cr7.jardinamanecer.ui.screens.level1.game1


import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cr7.jardinamanecer.R


@Composable
fun AnimalDisp(idImagen: Int, AnimalName : String, viewModel: AnimalViewModel) {
    var isZoomed by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {


        Image(
            painter = painterResource(id = idImagen),
            contentDescription = null,
            modifier = Modifier
                .width(if (isZoomed) 600.dp else 500.dp)
                .height(if (isZoomed) 800.dp else 500.dp)
                .padding(top = 120.dp)
                .clickable {
                    isZoomed = !isZoomed

                }
        )

        Text(
            text = AnimalName,
            style = MaterialTheme.typography.bodyLarge
                .copy(fontSize = 30.sp, fontFamily = FontFamily.Default),
            color = Color.White,
            modifier = Modifier
                .weight(40f)
                .padding(bottom = 40.dp, top = 20.dp)

        )

    }


}


