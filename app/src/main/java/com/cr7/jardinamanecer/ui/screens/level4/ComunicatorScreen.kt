package com.cr7.jardinamanecer.ui.screens.level4

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cr7.jardinamanecer.model.level4.ComunicatorItem

@Composable
fun ComunicatorScreen() {
    val items: List<ComunicatorItem> = listOf(ComunicatorItem("Lápiz", "https://chedrauimx.vtexassets.com/arquivos/ids/20944211-800-auto?v=638337192895700000&width=800&height=auto&aspect=true"))
    var selectedItems = remember { mutableStateListOf<ComunicatorItem>() }
    Surface(
        color = Color(0xFFFADDB1),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 64.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(5),
                modifier = Modifier.padding(8.dp),
            ) {
                items(10) {
                    ComunicatorCard(
                        modifier = Modifier.padding(24.dp).height(200.dp),
                        item = items[0]
                    ) {
                        selectedItems.add(items[0])
                    }
                }
            }
            LazyRow(
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.padding(8.dp),
            ) {
                selectedItems.forEach {
                    item {
                        ComunicatorCard(
                            modifier = Modifier.padding(24.dp).height(150.dp),
                            item = it
                        ) {
                            selectedItems.remove(it)
                        }
                    }
                }
            }
        }
    }
}