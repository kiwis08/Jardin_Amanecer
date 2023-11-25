package com.cr7.jardinamanecer.ui.screens.level4.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.navigation.Screens
import com.cr7.jardinamanecer.ui.screens.level4.model.DragTarget
import com.cr7.jardinamanecer.ui.screens.level4.model.DropTarget
import com.cr7.jardinamanecer.ui.screens.level4.viewmodel.PuzzleViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cr7.jardinamanecer.ui.screens.level4.model.LongPressDraggable

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PuzzleScreen(navController: NavHostController, viewModel: PuzzleViewModel = viewModel()) {
    Surface(
        color = Color(0xFF076187),
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
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
                    text = "Rompecabezas",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, start = 15.dp)
                )

            }
            LongPressDraggable {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp),
                ) {
                    // Two grids, one with the images in random order
                    // Another one with the white squares where we will drop the images
                    // The images will be draggable
                    // The white squares will be droppable
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier
                            .padding(16.dp)
                            .weight(1f),
                        contentPadding = PaddingValues(4.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(9) { index ->
                            DragTarget<Int>(
                                context = LocalContext.current,
                                pagerSize = 0,
                                modifier = Modifier,
                                dataToDrop = viewModel.state.value.currentRandomImagesInSelectables[index],
                                enabled = viewModel.state.value.currentRandomImagesInSelectables[index] != R.drawable.white_rectangle,
                            ) {
                                Image(
                                    painter = painterResource(id = viewModel.state.value.currentRandomImagesInSelectables[index]!!),
                                    contentDescription = null,
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(64.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier
                            .padding(16.dp)
                            .weight(1f),
                        contentPadding = PaddingValues(4.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(9) {
                            DropTarget<Int>(
                                modifier = Modifier
                            ) { isInBound, droppedImage ->
                                if (droppedImage != null && viewModel.state.value.imagesCorrectPositions[droppedImage] == it) {
                                    viewModel.state.value.currentImageInPuzzle = viewModel.state.value.currentImageInPuzzle.toMutableMap().apply {
                                        this[it] = droppedImage
                                    }
                                    val i = viewModel.state.value.currentRandomImagesInSelectables.keys.firstOrNull { key -> viewModel.state.value.currentRandomImagesInSelectables[key] == droppedImage }
                                    if (i != null) {
                                        viewModel.state.value.currentRandomImagesInSelectables = viewModel.state.value.currentRandomImagesInSelectables.toMutableMap().apply {
                                            this[i] = R.drawable.white_rectangle
                                        }
                                    }
                                }
                                Image(
                                    painter = painterResource(id = viewModel.state.value.currentImageInPuzzle[it]!!),
                                    contentDescription = null,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}