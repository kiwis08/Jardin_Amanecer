package com.cr7.jardinamanecer.ui.screens.level1.game3



import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cr7.jardinamanecer.R


@Composable
fun InstrumentosDisp() {

    var isPlaying by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box (modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.bateria),
            contentDescription = null,
            modifier = Modifier
                .width(500.dp)
                .height(500.dp)
                .offset(x = 100.dp, y = 300.dp)
                .clickable {
                    //Sonido
                    playAudio(context, R.raw.bateria)
                    isPlaying = !isPlaying
                }
        )
        Image(
            painter = painterResource(id = R.drawable.guitarra),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 60.dp)
                .width(350.dp)
                .height(350.dp)
                .offset(x = 450.dp, y = -30.dp)
                .clickable {
                    //Sonido
                    playAudio(context, R.raw.guitarra)
                    isPlaying = !isPlaying
                }
        )
        Image(
            painter = painterResource(id = R.drawable.xilofono),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 60.dp)
                .width(250.dp)
                .height(250.dp)
                .offset(x = 80.dp, y = 0.dp)
                .clickable {
                    //Sonido
                    playAudio(context, R.raw.xilofono)
                    isPlaying = !isPlaying
                }
        )
        Image(
            painter = painterResource(id = R.drawable.maracas),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 60.dp)
                .width(200.dp)
                .height(200.dp)
                .offset(x = 1480.dp, y = 0.dp)
                .clickable {
                    //Sonido
                    playAudio(context, R.raw.maracas)
                    isPlaying = !isPlaying
                }
        )
        Image(
            painter = painterResource(id = R.drawable.piano),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 60.dp)
                .width(350.dp)
                .height(350.dp)
                .offset(x = 900.dp, y = -20.dp)
                .clickable {
                    //Sonido
                    playAudio(context, R.raw.piano)
                    isPlaying = !isPlaying
                }
        )
        Image(
            painter = painterResource(id = R.drawable.pandero),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 60.dp)
                .width(230.dp)
                .height(230.dp)
                .offset(x = 1450.dp, y = 450.dp)
                .clickable {
                    //Sonido
                    playAudio(context, R.raw.pandereta)
                    isPlaying = !isPlaying
                }
        )

        Image(
            painter = painterResource(id = R.drawable.acordeon),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 60.dp)
                .width(300.dp)
                .height(300.dp)
                .offset(x = 800.dp, y = 400.dp)
                .clickable {
                    //Sonido
                    playAudio(context, R.raw.acordeon)
                    isPlaying = !isPlaying
                }
        )

        Image(
            painter = painterResource(id = R.drawable.tambor),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 60.dp)
                .width(250.dp)
                .height(250.dp)
                .offset(x = 1200.dp, y = 250.dp)
                .clickable {
                    //Sonido
                    playAudio(context, R.raw.tambor)
                    isPlaying = !isPlaying
                }
        )
    }



}

fun playAudio(context: Context, audioResourceId: Int) {
    val mediaPlayer = MediaPlayer.create(context, audioResourceId)
    mediaPlayer.start()
}

@Preview(
    widthDp = 1707,
    heightDp = 960,
)
@Composable
fun InstrumentosDispPreview() {
    InstrumentosDisp()
}
