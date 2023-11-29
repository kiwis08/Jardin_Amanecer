package com.cr7.jardinamanecer.ui.screens.menu

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.dataStore
import com.cr7.jardinamanecer.navigation.Screens
import com.cr7.jardinamanecer.ui.screens.AppSettings
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameMenu(navController: NavController, viewModel: GameMenuViewModel = viewModel()) {
    val context = LocalContext.current

    val student = LocalContext.current.dataStore.data.collectAsState(initial = AppSettings()).value.currentStudent

    val gamelists = viewModel.getStudentGames(student)


    Box(modifier = Modifier
        .paint(painterResource(id = R.drawable.menuscreen),
            contentScale = ContentScale.FillBounds)
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            state = rememberLazyListState(),
            contentPadding = PaddingValues(horizontal = 5.dp, vertical = 0.dp),
        ) {
        items(gamelists.size) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        navController.navigate(gamelists[page].route)
                    }
                ) {
                    Image(
                        painter = painterResource(id = gamelists[page].image),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }


        Box(
            modifier = Modifier
                .offset(x = 80.dp, y = 50.dp)
        ) {
        Image(
            painter = painterResource(id = R.drawable.regresar),
            contentDescription = null,
            modifier = Modifier
                .size(45.dp)

                .clickable {
                    viewModel.viewModelScope.launch {
                        context.dataStore.updateData {
                            it.copy(currentStudent = null)
                        }
                    }
                    navController.navigate(Screens.Start.route)
                }
        )
        Text(
            style = MaterialTheme.typography.displayLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            fontSize = 40.sp,
            modifier = Modifier
                .offset(x = 60.dp, y = -5.dp),
            text = "¡Hola ${student?.name}!"
        )
    }
    }

}
