package com.cr7.jardinamanecer.ui.screens.level3

//import com.example.drag_and_drop.ui.theme.Drag_and_dropTheme

//import androidx.compose.foundation.layout.ColumnScopeInstance.weight
//import androidx.compose.foundation.layout.RowScopeInstance.weight
import android.media.MediaPlayer
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.navigation.Screens
import java.util.Locale
import kotlin.math.roundToInt

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
                textToSpeech.value?.language = Locale("es", "MX")
            }
        }
    }


    val mediaPlayer = MediaPlayer.create(LocalContext.current, R.raw.fanfare)

    val imageSize = 150.dp
    val dropTargetSize = 150.dp

    // Rojo 1
    var imagePosition by remember { mutableStateOf(Offset(2170f, 300f)) }
    val dropTargetPosition = Offset(170f, 1000f)

    // Amarillo 1
    var imagePosition2 by remember { mutableStateOf(Offset(1670f, 300f)) }
    val dropTargetPosition2 = Offset(670f, 900f)

    // Verde 1
    var imagePosition3 by remember { mutableStateOf(Offset(1170f, 300f)) }
    val dropTargetPosition3 = Offset(1170f, 1000f)

    // Naranja 1
    var imagePosition4 by remember { mutableStateOf(Offset(170f, 300f)) }
    val dropTargetPosition4 = Offset(1670f, 900f)

    // Azul 1
    var imagePosition5 by remember { mutableStateOf(Offset(670f, 300f)) }
    val dropTargetPosition5 = Offset(2170f, 1000f)



    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(84, 0, 191))) {

        //Encabezado
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
                .background(Color.Red,
                    shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("Rojo", color = Color.White, fontSize = 30.sp)
        }

        Box(
            modifier = Modifier
                .offset { IntOffset(dropTargetPosition2.x.roundToInt(), dropTargetPosition2.y.roundToInt()) }
                .size(dropTargetSize)
                .background(Color(235, 198, 9),
                    shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("Amarillo", color = Color.White, fontSize = 30.sp)
        }

        Box(
            modifier = Modifier
                .offset { IntOffset(dropTargetPosition3.x.roundToInt(), dropTargetPosition3.y.roundToInt()) }
                .size(dropTargetSize)
                .background(Color(111, 176, 0),
                    shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("Verde", color = Color.White, fontSize = 30.sp)
        }

        Box(
            modifier = Modifier
                .offset { IntOffset(dropTargetPosition4.x.roundToInt(), dropTargetPosition4.y.roundToInt()) }
                .size(dropTargetSize)
                .background(Color(255, 61, 0),
                    shape = CircleShape),
            contentAlignment = Alignment.Center,

        ) {
            Text("Naranja", color = Color.White, fontSize = 30.sp)
        }

        Box(
            modifier = Modifier
                .offset { IntOffset(dropTargetPosition5.x.roundToInt(), dropTargetPosition5.y.roundToInt()) }
                .size(dropTargetSize)
                .background(Color.Blue,
                    shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("Azul", color = Color.White, fontSize = 30.sp)
        }

        // Draggable Image
        Image(
            painter = painterResource(id = R.drawable.rojo1),
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
                                mediaPlayer.start()
                                showSuccessMessage = true                            }
                        }
                    }
                }
        )

        Image(
            painter = painterResource(id = R.drawable.amarillo1),
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
                                mediaPlayer.start()
                                showSuccessMessage = true                            }
                        }
                    }
                }
        )

        Image(
            painter = painterResource(id = R.drawable.verde1),
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
                                mediaPlayer.start()
                                showSuccessMessage = true                            }
                        }
                    }
                }
        )

        Image(
            painter = painterResource(id = R.drawable.naranja1),
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
                            textToSpeech.value?.speak("naranja", TextToSpeech.QUEUE_FLUSH, null, null)
                            if(imagesInPlace[0] and imagesInPlace[1] and imagesInPlace[2] and imagesInPlace[3] and imagesInPlace[4]){
                                mediaPlayer.start()
                                showSuccessMessage = true                            }
                        }
                    }
                }
        )

        Image(
            painter = painterResource(id = R.drawable.azul1),
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
                                mediaPlayer.start()
                                showSuccessMessage = true
                            }
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
                    text = "¡Felicidades!",
                    fontSize = 64.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Black
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