package com.cr7.jardinamanecer.ui.screens.level4.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.cr7.jardinamanecer.ui.screens.level4.viewmodel.MemoryViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cr7.jardinamanecer.ui.screens.level4.model.MemoryItem

@Composable
fun MemoryScreen(
    viewModel: MemoryViewModel = viewModel(),
    navController: NavHostController
) {
    val state = viewModel.state.value

    BackHandler {
        if (state.selectedCategory.isNotEmpty()) {
            viewModel.onCategorySelected("")
            viewModel.resetState()
        } else {
            navController.popBackStack()
        }
    }

    Surface(
        color = Color(0xFF076187),
    ) {
        Column(
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 64.dp)
        ) {
            if ((state.guessedItems.count() * 2) == state.items.count()) {
                Text(text = "¡Felicidades!", fontSize = 64.sp, color = Color.White, fontWeight = FontWeight.Black)
            } else {
                if (state.selectedCategory.isEmpty()) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
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
                        columns = GridCells.Fixed(4),
                        modifier = Modifier.padding(8.dp),
                    ) {
                        state.items.filter { it.category == state.selectedCategory }.forEach {
                            item {
                                MemoryCard(
                                    modifier = Modifier
                                        .padding(24.dp),
                                    item = it,
                                    isTurned = viewModel.isTurned(it),
                                ) {
                                    viewModel.onItemSelected(it)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}