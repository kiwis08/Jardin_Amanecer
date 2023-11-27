package com.cr7.jardinamanecer.ui.screens.level2.game3

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.navigation.Screens
import java.util.Locale
import kotlin.math.roundToInt


@Composable
fun ClothingDragAndDrop(navController : NavController) {
    val context = LocalContext.current
    val imagesInPlace = BooleanArray(4) { false }
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
    val dropTargetSize = 400.dp

    //Vestido
    var imagePosition by remember { mutableStateOf(Offset(150f, 200f)) }
    //Camiseta
    var imagePosition2 by remember { mutableStateOf(Offset(800f, 350f)) }
    //Falda
    var imagePosition3 by remember { mutableStateOf(Offset(200f, 800f)) }
    //Corbata
    var imagePosition4 by remember { mutableStateOf(Offset(1300f, 200f)) }
    //Chamarra
    var imagePosition5 by remember { mutableStateOf(Offset(1800f, 300f)) }
    //Gorro
    var imagePosition7 by remember { mutableStateOf(Offset(800f, 900f)) }
    //Guantes
    var imagePosition8 by remember { mutableStateOf(Offset(1400f, 900f)) }


    //TARGET - Cesto de ropa
    val dropTargetPosition = Offset(2100f, 800f)


    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(android.graphics.Color.parseColor("#EA047E")))
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
                    .clickable {
                        navController.navigate(Screens.GameMenu.route)
                    }
            )

            Text(
                text = "Alístate",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 15.dp)
            )
        }


        Box(
            modifier = Modifier
                .offset { IntOffset(dropTargetPosition.x.roundToInt(), dropTargetPosition.y.roundToInt()) }
                .size(dropTargetSize),
            contentAlignment = Alignment.Center
        ) {

            //Target - Cesto de ropa
            Image(
                painter = painterResource(id = R.drawable.bote),
                contentDescription = "Drop Target",
                modifier = Modifier
                    .size(imageSize)
            )
        }



        //Vestido
        Image(
            painter = painterResource(id = R.drawable.vestido),
            contentDescription = "Draggable image",
            modifier = Modifier
                .offset { IntOffset(imagePosition.x.roundToInt(), imagePosition.y.roundToInt()) }
                .size(300.dp)
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
                            textToSpeech.value?.speak("vestido", TextToSpeech.QUEUE_FLUSH, null, null)
                            if(imagesInPlace[0] and imagesInPlace[1] and imagesInPlace[2] and imagesInPlace[3]){
                                mediaPlayer.start()
                                showSuccessMessage = true                            }
                        }
                    }
                }
        )

        //Camiseta
        Image(
            painter = painterResource(id = R.drawable.camiseta),
            contentDescription = "Draggable image",
            modifier = Modifier
                .offset { IntOffset(imagePosition2.x.roundToInt(), imagePosition2.y.roundToInt()) }
                .size(300.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        imagePosition2 = imagePosition2.plus(Offset(dragAmount.x, dragAmount.y))
                        if (isImageInDropTarget(
                                imagePosition2,
                                dropTargetPosition,
                                imageSize,
                                dropTargetSize
                            )
                        ) {
                            imagePosition2 = dropTargetPosition
                            imagesInPlace[1] = true
                            textToSpeech.value?.speak("camiseta", TextToSpeech.QUEUE_FLUSH, null, null)
                            if(imagesInPlace[0] and imagesInPlace[1] and imagesInPlace[2] and imagesInPlace[3] ){
                                mediaPlayer.start()
                                showSuccessMessage = true                            }
                        }
                    }
                }
        )

        //Falda
        Image(
            painter = painterResource(id = R.drawable.falda),
            contentDescription = "Draggable image",
            modifier = Modifier
                .offset { IntOffset(imagePosition3.x.roundToInt(), imagePosition3.y.roundToInt()) }
                .size(300.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        imagePosition3 = imagePosition3.plus(Offset(dragAmount.x, dragAmount.y))
                        if (isImageInDropTarget(
                                imagePosition3,
                                dropTargetPosition,
                                imageSize,
                                dropTargetSize
                            )
                        ) {
                            imagePosition3 = dropTargetPosition
                            imagesInPlace[2] = true
                            textToSpeech.value?.speak("falda", TextToSpeech.QUEUE_FLUSH, null, null)
                            if(imagesInPlace[0] and imagesInPlace[1] and imagesInPlace[2] and imagesInPlace[3] ){
                                mediaPlayer.start()
                                showSuccessMessage = true                            }
                        }
                    }
                }
        )

        //Corbata
        Image(
            painter = painterResource(id = R.drawable.corbata),
            contentDescription = "Draggable image",
            modifier = Modifier
                .offset { IntOffset(imagePosition4.x.roundToInt(), imagePosition4.y.roundToInt()) }
                .size(250.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        imagePosition4 = imagePosition4.plus(Offset(dragAmount.x, dragAmount.y))
                        if (isImageInDropTarget(
                                imagePosition4,
                                dropTargetPosition,
                                imageSize,
                                dropTargetSize
                            )
                        ) {
                            imagePosition4 = dropTargetPosition
                            imagesInPlace[3] = true
                            textToSpeech.value?.speak("corbata", TextToSpeech.QUEUE_FLUSH, null, null)
                            if(imagesInPlace[0] and imagesInPlace[1] and imagesInPlace[2] and imagesInPlace[3] ){
                                mediaPlayer.start()
                                showSuccessMessage = true                            }
                        }
                    }
                }
        )

        //Chamarra
        Image(
            painter = painterResource(id = R.drawable.chamarra),
            contentDescription = "Draggable image",
            modifier = Modifier
                .offset { IntOffset(imagePosition5.x.roundToInt(), imagePosition5.y.roundToInt()) }
                .size(300.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        imagePosition5 = imagePosition5.plus(Offset(dragAmount.x, dragAmount.y))
                        if (isImageInDropTarget(
                                imagePosition5,
                                dropTargetPosition,
                                imageSize,
                                dropTargetSize
                            )
                        ) {
                            imagePosition5 = dropTargetPosition
                            imagesInPlace[3] = true
                            textToSpeech.value?.speak("chamarra", TextToSpeech.QUEUE_FLUSH, null, null)
                            if(imagesInPlace[0] and imagesInPlace[1] and imagesInPlace[2] and imagesInPlace[3] ){
                                mediaPlayer.start()
                                showSuccessMessage = true                            }
                        }
                    }
                }
        )



        //Gorro
        Image(
            painter = painterResource(id = R.drawable.gorro),
            contentDescription = "Draggable image",
            modifier = Modifier
                .offset { IntOffset(imagePosition7.x.roundToInt(), imagePosition7.y.roundToInt()) }
                .size(250.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        imagePosition7 = imagePosition7.plus(Offset(dragAmount.x, dragAmount.y))
                        if (isImageInDropTarget(
                                imagePosition7,
                                dropTargetPosition,
                                imageSize,
                                dropTargetSize
                            )
                        ) {
                            imagePosition7 = dropTargetPosition
                            imagesInPlace[3] = true
                            textToSpeech.value?.speak("gorro", TextToSpeech.QUEUE_FLUSH, null, null)
                            if(imagesInPlace[0] and imagesInPlace[1] and imagesInPlace[2] and imagesInPlace[3] ){
                                mediaPlayer.start()
                                showSuccessMessage = true                            }
                        }
                    }
                }
        )

        //Guantes
        Image(
            painter = painterResource(id = R.drawable.guantes),
            contentDescription = "Draggable image",
            modifier = Modifier
                .offset { IntOffset(imagePosition8.x.roundToInt(), imagePosition8.y.roundToInt()) }
                .size(250.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        imagePosition8 = imagePosition8.plus(Offset(dragAmount.x, dragAmount.y))
                        if (isImageInDropTarget(
                                imagePosition8,
                                dropTargetPosition,
                                imageSize,
                                dropTargetSize
                            )
                        ) {
                            imagePosition8 = dropTargetPosition
                            imagesInPlace[3] = true
                            textToSpeech.value?.speak("guantes", TextToSpeech.QUEUE_FLUSH, null, null)
                            if(imagesInPlace[0] and imagesInPlace[1] and imagesInPlace[2] and imagesInPlace[3] ){
                                mediaPlayer.start()
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