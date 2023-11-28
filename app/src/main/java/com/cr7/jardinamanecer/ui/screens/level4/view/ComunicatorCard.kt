package com.cr7.jardinamanecer.ui.screens.level4.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cr7.jardinamanecer.ui.screens.level4.model.ComunicatorItem

@Composable
fun ComunicatorCard(modifier: Modifier = Modifier, item: ComunicatorItem, internetConnection: Boolean, onClick: () -> Unit) {
    Box(
        modifier = modifier.clickable(interactionSource = MutableInteractionSource(), indication = null, onClick = onClick).background(Color.White, RoundedCornerShape(30.dp)).width(300.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = item.title)
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(if (internetConnection) item.imageUrl else item.imageLocalPath).build(),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }
}