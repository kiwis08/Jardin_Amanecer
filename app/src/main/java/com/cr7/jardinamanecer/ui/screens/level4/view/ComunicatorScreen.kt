package com.cr7.jardinamanecer.ui.screens.level4.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.navigation.Screens
import com.cr7.jardinamanecer.ui.screens.level4.viewmodel.CommunicatorViewModel

@Composable
fun ComunicatorScreen(
    viewModel: CommunicatorViewModel = viewModel(),
    navController: NavHostController
) {
    val state = viewModel.state.value
    val context = LocalContext.current

    BackHandler(enabled = true) {
        if (state.selectedCategory.isNotEmpty()) {
            viewModel.onCategorySelected("")
        } else {
            navController.popBackStack()
        }
    }

    Surface(
        color = Color(0xFFFADDB1),
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                //Boton hace pantalla anterior
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
                    text = "Comunicador",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, start = 15.dp)
                )

            }
            Column(
                modifier = Modifier.padding(horizontal = 64.dp)
            ) {
                if (state.selectedCategory.isEmpty()) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(5),
                        modifier = Modifier.padding(8.dp),
                    ) {
                        state.categories.forEach {
                            item {
                                CategoryCard(
                                    modifier = Modifier
                                        .padding(24.dp),
                                    category = it,
                                    onClick = {
                                        viewModel.onCategorySelected(it)
                                    }
                                )
                            }
                        }
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(300.dp),
                        modifier = Modifier.padding(8.dp),
                    ) {
                        state.items.filter { it.category == state.selectedCategory }.forEach {
                            item {
                                ComunicatorCard(
                                    modifier = Modifier
                                        .padding(24.dp)
                                        .height(150.dp),
                                    item = it,
                                    internetConnection = viewModel.checkInternetConnection(context)
                                ) {
                                    viewModel.onItemSelected(it)
                                }
                            }
                        }
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(8.dp)
                        .height(300.dp)
                ) {
                    IconButton(
                        modifier = Modifier.size(64.dp),
                        onClick = {
                            viewModel.textToSpeech(context)

                        }) {
                        Icon(
                            Icons.Filled.PlayArrow, contentDescription = "Play", modifier = Modifier
                                .background(Color.Black, CircleShape)
                                .size(64.dp), tint = Color.White
                        )
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
                                    item = it,
                                    internetConnection = viewModel.checkInternetConnection(context)
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
}