package com.cr7.jardinamanecer.ui.screens.level3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
//import com.example.drag_and_drop.ui.theme.Drag_and_dropTheme

import androidx.activity.compose.setContent
import androidx.annotation.ReturnThis
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.RowScopeInstance.weight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import android.speech.tts.TextToSpeech
import androidx.compose.foundation.clickable
import java.util.Locale
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.navigation.Screens
import androidx.navigation.NavController

//@Preview(heightDp = 600, widthDp = 1000)
@Composable
//navController : NavController
fun ImageDragAndDropColores(navController : NavController) {
    val context = LocalContext.current
    val imagesInPlace = BooleanArray(5) { false }
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

    // numero 1
    var imagePosition by remember { mutableStateOf(Offset(170f, 300f)) }
    val dropTargetPosition = Offset(170f, 1100f)

// numero 2
    var imagePosition2 by remember { mutableStateOf(Offset(670f, 300f)) }
    val dropTargetPosition2 = Offset(670f, 1000f)

// numero 3
    var imagePosition3 by remember { mutableStateOf(Offset(1170f, 300f)) }
    val dropTargetPosition3 = Offset(1170f, 1200f)

// numero 4
    var imagePosition4 by remember { mutableStateOf(Offset(1670f, 300f)) }
    val dropTargetPosition4 = Offset(1670f, 1000f)

// numero 5
    var imagePosition5 by remember { mutableStateOf(Offset(2170f, 300f)) }
    val dropTargetPosition5 = Offset(2170f, 1100f)



    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(191, 128, 255, 255))) {

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
                    .padding(16.dp)
                    .clickable {
                        navController.navigate(Screens.GameMenu.route)
                    }
            )

            // Título
            Text(
                text = "Colores",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 15.dp)
            )
        }

        // Drop Target
        Box(
            modifier = Modifier
                .offset { IntOffset(dropTargetPosition.x.roundToInt(), dropTargetPosition.y.roundToInt()) }
                .size(dropTargetSize)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text("Rojo", color = Color.Black, fontSize = 30.sp)
        }

        Box(
            modifier = Modifier
                .offset { IntOffset(dropTargetPosition2.x.roundToInt(), dropTargetPosition2.y.roundToInt()) }
                .size(dropTargetSize)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text("Amarillo", color = Color.Black, fontSize = 30.sp)
        }

        Box(
            modifier = Modifier
                .offset { IntOffset(dropTargetPosition3.x.roundToInt(), dropTargetPosition3.y.roundToInt()) }
                .size(dropTargetSize)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text("Verde", color = Color.Black, fontSize = 30.sp)
        }

        Box(
            modifier = Modifier
                .offset { IntOffset(dropTargetPosition4.x.roundToInt(), dropTargetPosition4.y.roundToInt()) }
                .size(dropTargetSize)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text("Morado", color = Color.Black, fontSize = 30.sp)
        }

        Box(
            modifier = Modifier
                .offset { IntOffset(dropTargetPosition5.x.roundToInt(), dropTargetPosition5.y.roundToInt()) }
                .size(dropTargetSize)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text("Azul", color = Color.Black, fontSize = 30.sp)
        }

        // Draggable Image
        Image(
            painter = painterResource(id = R.drawable.rojo),
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
                            textToSpeech.value?.speak("Rojo", TextToSpeech.QUEUE_FLUSH, null, null)
                            if(imagesInPlace[0] and imagesInPlace[1] and imagesInPlace[2] and imagesInPlace[3] and imagesInPlace[4]){
                                Toast.makeText(context, "Ganaste el juego", Toast.LENGTH_SHORT).show()
                                showSuccessMessage = true                            }
                        }
                    }
                }
        )

        Image(
            painter = painterResource(id = R.drawable.amarillo),
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
                            textToSpeech.value?.speak("Amarillo", TextToSpeech.QUEUE_FLUSH, null, null)
                            if(imagesInPlace[0] and imagesInPlace[1] and imagesInPlace[2] and imagesInPlace[3] and imagesInPlace[4]){
                                Toast.makeText(context, "Ganaste el juego", Toast.LENGTH_SHORT).show()
                                showSuccessMessage = true                            }
                        }
                    }
                }
        )

        Image(
            painter = painterResource(id = R.drawable.verde),
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
                            textToSpeech.value?.speak("Verde", TextToSpeech.QUEUE_FLUSH, null, null)
                            if(imagesInPlace[0] and imagesInPlace[1] and imagesInPlace[2] and imagesInPlace[3] and imagesInPlace[4]){
                                Toast.makeText(context, "Ganaste el juego", Toast.LENGTH_SHORT).show()
                                showSuccessMessage = true                            }
                        }
                    }
                }
        )

        Image(
            painter = painterResource(id = R.drawable.morado),
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
                            textToSpeech.value?.speak("morado", TextToSpeech.QUEUE_FLUSH, null, null)
                            if(imagesInPlace[0] and imagesInPlace[1] and imagesInPlace[2] and imagesInPlace[3] and imagesInPlace[4]){
                                Toast.makeText(context, "Ganaste el juego", Toast.LENGTH_SHORT).show()
                                showSuccessMessage = true                            }
                        }
                    }
                }
        )

        Image(
            painter = painterResource(id = R.drawable.azul),
            contentDescription = "Draggable image",
            modifier = Modifier
                .offset { IntOffset(imagePosition5.x.roundToInt(), imagePosition5.y.roundToInt()) }
                .size(imageSize)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        imagePosition5 = imagePosition5.plus(Offset(dragAmount.x, dragAmount.y))

                        if (isImageInDropTarget(
                                imagePosition5,
                                dropTargetPosition5,
                                imageSize,
                                dropTargetSize
                            )
                        ) {
                            imagePosition5 = dropTargetPosition5
                            imagesInPlace[4] = true
                            textToSpeech.value?.speak("Azul", TextToSpeech.QUEUE_FLUSH, null, null)
                            if(imagesInPlace[0] and imagesInPlace[1] and imagesInPlace[2] and imagesInPlace[3] and imagesInPlace[4]){
                                Toast.makeText(context, "Ganaste el juego", Toast.LENGTH_SHORT).show()
                                showSuccessMessage = true
                            }
                        }
                    }
                }
        )
        if (showSuccessMessage) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x66000000)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "¡Ganaste!",
                    color = Color.White,
                    fontSize = 100.sp,
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