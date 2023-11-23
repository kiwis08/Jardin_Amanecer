package com.cr7.jardinamanecer.ui.screens.level4.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.ui.screens.level4.model.MemoryItem

@Composable
fun MemoryCard(modifier: Modifier, item: MemoryItem, isTurned: Boolean, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = onClick
            )
            .background(
                Color.White, RoundedCornerShape(30.dp)
            )
            .height(200.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            if (isTurned) {
                Text(text = item.title)
                Image(painter = painterResource(id = item.image), contentDescription = item.title)
            } else {
                Image(
                    painter = painterResource(id = R.drawable.question),
                    contentDescription = item.title
                )
            }
        }
    }
}