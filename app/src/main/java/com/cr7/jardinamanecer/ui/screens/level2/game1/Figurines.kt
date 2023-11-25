package com.cr7.jardinamanecer.ui.screens.level2.game1

import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cr7.jardinamanecer.R
import java.util.Locale
import kotlin.math.roundToInt

@Preview(widthDp = 900, heightDp = 700)
@Composable
fun FigurinesDragAndDrop() {
    val context = LocalContext.current
    val imagesInPlace = BooleanArray(4) { false }
    var showSuccessMessage by remember { mutableStateOf(false) }
    val textToSpeech = remember { mutableStateOf<TextToSpeech?>(null) }
    LaunchedEffect(Unit) {
        textToSpeech.value = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.value?.language = Locale.US
            }
        }
    }

    val imageSize = 150.dp
    val dropTargetSize = 150.dp

    var imagePosition by remember { mutableStateOf(Offset(300f, 300f)) }
    val dropTargetPosition = Offset(300f, 1200f)
    var imagePosition2 by remember { mutableStateOf(Offset(900f, 300f)) }
    val dropTargetPosition2 = Offset(900f, 1200f)
    var imagePosition3 by remember { mutableStateOf(Offset(1500f, 300f)) }
    val dropTargetPosition3 = Offset(1500f, 1200f)
    var imagePosition4 by remember { mutableStateOf(Offset(2000f, 300f)) }
    val dropTargetPosition4 = Offset(2000f, 1200f)


    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(android.graphics.Color.parseColor("#26ABE2")))
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .height(100.dp),
        ){
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .padding(1.dp)
            )

            Text(
                text = "Figurines",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 15.dp)
            )
        }

        Box(
            modifier = Modifier
                .offset { IntOffset(dropTargetPosition.x.roundToInt(), dropTargetPosition.y.roundToInt()) }
                .size(dropTargetSize)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.hueco_t),
                contentDescription = "Drop Target",
                modifier = Modifier.size(150.dp)
            )
        }

        Box(
            modifier = Modifier
                .offset { IntOffset(dropTargetPosition2.x.roundToInt(), dropTargetPosition2.y.roundToInt()) }
                .size(dropTargetSize),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.hueco_s),
                contentDescription = "Drop Target",
                modifier = Modifier.size(150.dp)
            )
        }

        Box(
            modifier = Modifier
                .offset { IntOffset(dropTargetPosition3.x.roundToInt(), dropTargetPosition3.y.roundToInt()) }
                .size(dropTargetSize)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.hueco_e),
                contentDescription = "Drop Target",
                modifier = Modifier.size(150.dp)
            )
        }

        Box(
            modifier = Modifier
                .offset { IntOffset(dropTargetPosition4.x.roundToInt(), dropTargetPosition4.y.roundToInt()) }
                .size(dropTargetSize)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.hueco_c),
                contentDescription = "Drop Target",
                modifier = Modifier.size(150.dp)
            )

        }

        Image(
            painter = painterResource(id = R.drawable.triangulo),
            contentDescription = "Draggable image",
            modifier = Modifier
                .offset { IntOffset(imagePosition.x.roundToInt(), imagePosition.y.roundToInt()) }
                .size(imageSize)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        imagePosition = imagePosition.plus(Offset(dragAmount.x, dragAmount.y))
                        if (isImageInDropTarget(
                                imagePosition,
                                dropTargetPosition,
                                imageSize,
                                dropTargetSize
                            )
                        ) {
                            imagePosition = dropTargetPosition
                            imagesInPlace[0] = true
                            textToSpeech.value?.speak("Triangulo", TextToSpeech.QUEUE_FLUSH, null, null)
                            if(imagesInPlace[0] and imagesInPlace[1] and imagesInPlace[2] and imagesInPlace[3]){
                                Toast.makeText(context, "Ganaste", Toast.LENGTH_SHORT).show()
                                showSuccessMessage = true                            }
                        }
                    }
                }
        )

        Image(
            painter = painterResource(id = R.drawable.cuadrado),
            contentDescription = "Draggable image",
            modifier = Modifier
                .offset { IntOffset(imagePosition2.x.roundToInt(), imagePosition2.y.roundToInt()) }
                .size(imageSize)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        imagePosition2 = imagePosition2.plus(Offset(dragAmount.x, dragAmount.y))
                        if (isImageInDropTarget(
                                imagePosition2,
                                dropTargetPosition2,
                                imageSize,
                                dropTargetSize
                            )
                        ) {
                            imagePosition2 = dropTargetPosition2
                            imagesInPlace[1] = true
                            textToSpeech.value?.speak("cuadrado", TextToSpeech.QUEUE_FLUSH, null, null)
                            if(imagesInPlace[0] and imagesInPlace[1] and imagesInPlace[2] and imagesInPlace[3] ){
                                Toast.makeText(context, "Ganaste", Toast.LENGTH_SHORT).show()
                                showSuccessMessage = true                            }
                        }
                    }
                }
        )

        Image(
            painter = painterResource(id = R.drawable.estrella),
            contentDescription = "Draggable image",
            modifier = Modifier
                .offset { IntOffset(imagePosition3.x.roundToInt(), imagePosition3.y.roundToInt()) }
                .size(imageSize)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        imagePosition3 = imagePosition3.plus(Offset(dragAmount.x, dragAmount.y))
                        if (isImageInDropTarget(
                                imagePosition3,
                                dropTargetPosition3,
                                imageSize,
                                dropTargetSize
                            )
                        ) {
                            imagePosition3 = dropTargetPosition3
                            imagesInPlace[2] = true
                            textToSpeech.value?.speak("estrella", TextToSpeech.QUEUE_FLUSH, null, null)
                            if(imagesInPlace[0] and imagesInPlace[1] and imagesInPlace[2] and imagesInPlace[3] ){
                                Toast.makeText(context, "Ganaste el juego", Toast.LENGTH_SHORT).show()
                                showSuccessMessage = true                            }
                        }
                    }
                }
        )

        Image(
            painter = painterResource(id = R.drawable.circulo),
            contentDescription = "Draggable image",
            modifier = Modifier
                .offset { IntOffset(imagePosition4.x.roundToInt(), imagePosition4.y.roundToInt()) }
                .size(imageSize)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        imagePosition4 = imagePosition4.plus(Offset(dragAmount.x, dragAmount.y))
                        if (isImageInDropTarget(
                                imagePosition4,
                                dropTargetPosition4,
                                imageSize,
                                dropTargetSize
                            )
                        ) {
                            imagePosition4 = dropTargetPosition4
                            imagesInPlace[3] = true
                            textToSpeech.value?.speak("circulo", TextToSpeech.QUEUE_FLUSH, null, null)
                            if(imagesInPlace[0] and imagesInPlace[1] and imagesInPlace[2] and imagesInPlace[3] ){
                                Toast.makeText(context, "Ganaste el juego", Toast.LENGTH_SHORT).show()
                                showSuccessMessage = true                            }
                        }
                    }
                }
        )


        if (showSuccessMessage) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "¡Felicidades, Ganaste!",
                    color = Color.White,
                    fontSize = 120.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
    DisposableEffect(Unit) {
        onDispose {
            textToSpeech.value?.stop()
            textToSpeech.value?.shutdown()
        }
    }

}

private fun isImageInDropTarget(
    imagePosition: Offset,
    dropTargetPosition: Offset,
    imageSize: Dp,
    dropTargetSize: Dp
): Boolean {
    val imageBounds = Rect(imagePosition, imagePosition + Offset(imageSize.value, imageSize.value))
    val dropTargetBounds = Rect(dropTargetPosition, dropTargetPosition + Offset(dropTargetSize.value, dropTargetSize.value))
    return imageBounds.overlaps(dropTargetBounds)
}