package com.cr7.jardinamanecer.ui.screens.level4.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cr7.jardinamanecer.ui.screens.level4.viewmodel.CommunicatorViewModel

@Composable
fun ComunicatorScreen(
    viewModel: CommunicatorViewModel = viewModel()
) {
    val state = viewModel.state.value
    val context = LocalContext.current

    Surface(
        color = Color(0xFFFADDB1),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 64.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(300.dp),
                modifier = Modifier.padding(8.dp),
            ) {
                state.items.forEach {
                    item {
                        ComunicatorCard(
                            modifier = Modifier
                                .padding(24.dp)
                                .height(150.dp),
                            item = it
                        ) {
                            viewModel.onItemSelected(it)
                        }
                    }
                }
            }
            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(8.dp).height(300.dp)
            ) {
                IconButton(
                    modifier = Modifier.size(64.dp),
                    onClick = {
                    viewModel.textToSpeech(context)

                }) {
                    Icon(Icons.Filled.PlayArrow, contentDescription = "Play", modifier = Modifier.background(Color.Black, CircleShape).size(64.dp), tint = Color.White)
                }
                LazyRow(
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier.padding(8.dp),
                ) {
                    state.selectedItems.forEach {
                        item {
                            ComunicatorCard(
                                modifier = Modifier
                                    .padding(24.dp)
                                    .height(150.dp),
                                item = it
                            ) {
                                viewModel.onItemRemoved(it)
                            }
                        }
                    }
                }
            }
        }
    }
}