package com.cr7.jardinamanecer.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.dataStore
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.navigation.Screens
import com.cr7.jardinamanecer.MainActivity
import com.cr7.jardinamanecer.dataStore
import com.cr7.jardinamanecer.ui.screens.admin.Student
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(navController: NavController, viewModel: StudentSignInViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    val items = viewModel.studentList.collectAsState(initial = emptyList()).value
    var selectedIndex by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier.paint(
                painterResource(id = R.drawable.bolas),
                contentScale = ContentScale.FillBounds
            )
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .fillMaxHeight()
                    .clip(RectangleShape)
                    .background(Color.White)
                    .align(Alignment.CenterEnd),
            )

            Box(
                modifier = Modifier
                    .offset(x = 200.dp, y = 200.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ninos),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 60.dp)
                        .width(600.dp)
                        .height(600.dp)
                )
            }


            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {

                Box (modifier = Modifier.fillMaxHeight()) {


                    //Boton de Acceso a admin
                    Box(
                        modifier = Modifier
                            .offset(x = 430.dp, y = 50.dp)
                    )
                    {
                        Image(
                            painter = painterResource(id = R.drawable.user),
                            contentDescription = null,
                            modifier = Modifier
                                .size(45.dp)
                                .clickable {
                                    navController.navigate(Screens.AdminSignIn.route)
                                }
                        )
                    }

                    //Titulo - Jardin
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .offset(x = -100.dp, y = -150.dp)

                    ) {
                        Text(
                            text = "Jardín",
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(104, 180, 72)
                            ),
                            fontSize = 80.sp
                        )
                    }

                    //Titulo - Amanecer
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .offset(x = -100.dp, y = -100.dp)
                    ) {
                        Text(
                            text = "Amanecer",
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(255, 77, 22)
                            ),
                            fontSize = 90.sp
                        )
                    }

                    Spacer(modifier = Modifier.fillMaxHeight(0.4f))

                    //Seleccion de alumnos y boton
                    if (items.size > 0) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .offset(x = -100.dp, y = 30.dp)
                        ) {

                            //Boton de seleccion de alumnos
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.Center)
                                    .offset(x = 10.dp, y = 40.dp)
                            )
                            {
                                ExposedDropdownMenuBox(
                                    expanded = expanded,
                                    onExpandedChange = { expanded = it },
                                    modifier = Modifier
                                ) {
                                    TextField(
                                        modifier = Modifier
                                            .menuAnchor()
                                            .fillMaxWidth(0.3f),
                                        readOnly = true,
                                        value = items[selectedIndex].name,
                                        onValueChange = { },
                                        label = { Text("Alumno") },
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expanded
                                            )
                                        },
                                        colors = ExposedDropdownMenuDefaults.textFieldColors(
                                            containerColor = Color(237, 237, 237),
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent
                                        ),
                                        shape = CircleShape,
                                    )
                                    ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                                        items.forEachIndexed { index, s ->
                                            DropdownMenuItem(text = {
                                                Text(s.name)
                                            }, onClick = {
                                                selectedIndex = index
                                                expanded = false
                                            })

                                        }
                                    }
                                }
                            }

                            //Boton de entrar
                            Box(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .offset(x = 10.dp, y = 250.dp)
                            ) {
                                Button(
                                    onClick = {
                                        viewModel.viewModelScope.launch {
                                            setUser(context, items[selectedIndex])
                                        }
                                        navController.navigate(Screens.GameMenu.route)
                                    },
                                    colors = ButtonDefaults.elevatedButtonColors(
                                        containerColor = Color(38, 171, 226),
                                        contentColor = Color.White
                                    ),
                                    modifier = Modifier
                                        .size(150.dp)
                                        .clip(CircleShape),
                                ) {
                                    Text(text = "Entrar", fontSize = 20.sp)
                                }
                            }

                        }
                    }
                }

            }
        }
    }
}

suspend fun setUser(context: Context, student: Student) {
    context.dataStore.updateData { settings ->
        settings.copy(
            currentStudent = student
        )
    }
}

@Preview(widthDp = 1707,
    heightDp = 960)
@Composable
fun PreviewStartScreen(){
    val navController = rememberNavController()
    StartScreen(navController)
}
