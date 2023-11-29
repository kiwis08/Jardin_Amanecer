package com.cr7.jardinamanecer.ui.screens.admin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.ui.theme.lexendFamily
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun PerfilAlumno(navController: NavController, alumno: Alumno, viewModel: StudentProfileViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val student by viewModel.getStudentProfile(alumno.id).collectAsState(initial = null)
    var dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    var birthDate = LocalDate.parse(student?.birthdate ?: "2000-01-01", dateFormatter)
    var age = LocalDate.now().year - birthDate.year
    if (LocalDate.now().dayOfYear < birthDate.dayOfYear) {
        age--
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Row(
            modifier = Modifier
                .background(Color.White)
        ) {
            Column {
                //Cuadro Perfil
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .fillMaxHeight()
                        .clip(RectangleShape)
                        .background(Color(12, 192, 223))
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {

                        Spacer(modifier = Modifier.height(60.dp))

                        Image(
                            modifier = Modifier
                                .size(300.dp)
                                .clip(CircleShape)
                                .align(Alignment.CenterHorizontally),
                            painter = painterResource(id = alumno.imagen),
                            contentDescription = "Imagen"
                        )

                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 20.dp)
                        ) {

                            // Datos
                            // Nombre
                            Text(
                                text = "Nombre",
                                modifier = Modifier
                                    .padding(start = 16.dp, top = 8.dp, bottom = 3.dp),
                                fontSize = 20.sp,
                                fontFamily = lexendFamily,
                                color = Color(209, 207, 207)
                            )

                            Text(
                                text = alumno.nombre,
                                modifier = Modifier
                                    .padding(start = 16.dp, top = 3.dp, bottom = 20.dp),
                                fontSize = 25.sp,
                                fontFamily = lexendFamily,
                                color = Color.White

                            )


                            // Edad
                            Text(
                                text = "Edad",
                                modifier = Modifier
                                    .padding(start = 16.dp, top = 20.dp, bottom = 3.dp),
                                fontSize = 20.sp,
                                fontFamily = lexendFamily,
                                color = Color(209, 207, 207)
                            )

                            Text(
                                text = "${alumno.edad} años",
                                modifier = Modifier
                                    .padding(start = 16.dp, top = 3.dp, bottom = 20.dp),
                                fontSize = 25.sp,
                                fontFamily = lexendFamily,
                                color = Color.White


                            )

                            // Nivel Cognitivo
                            Text(
                                text = "Nivel Cognitivo",
                                modifier = Modifier
                                    .padding(start = 16.dp, top = 20.dp, bottom = 3.dp),
                                fontSize = 20.sp,
                                fontFamily = lexendFamily,
                                color = Color(209, 207, 207)
                            )


                            Text(
                                text = "Nivel ${student?.cogLevel}",
                                modifier = Modifier
                                    .padding(start = 16.dp, top = 3.dp, bottom = 8.dp),
                                fontSize = 25.sp,
                                fontFamily = lexendFamily,
                                color = Color.White

                            )
                        }
                    }
                }
            }


            if (student != null) {
                //Pantalla con Juegos
                Column(
                    modifier = Modifier
                        .offset(x = 100.dp, y = 100.dp)
                ) {
                    var isSelected by remember { mutableStateOf(false) }

                    //Nivel 1
                    Text(text = "Nivel 1", Modifier.padding(bottom = 40.dp, top = 40.dp))

                    Row(
                        modifier = Modifier
                            .width(1000.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        //Juego 1
                        Button(
                            modifier = Modifier
                                .height(50.dp)
                                .weight(1f)
                                .padding(start = 10.dp),
                            onClick = {
                                if (student!!.games["level1"]?.contains(1) == true) {
                                    viewModel.disableGameForStudent(alumno.id, 1, 1)
                                } else {
                                    viewModel.enableGameForStudent(alumno.id, 1, 1)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (student!!.games["level1"]?.contains(1) == true) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(ButtonDefaults.IconSize)
                                    .absoluteOffset(x = (-40).dp)
                                    .graphicsLayer(translationX = 0.6f),
                                tint = if (student!!.games["level1"]?.contains(1) == true) Color.White else Color.Unspecified
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text(
                                text = "Animales",
                                modifier = Modifier
                                    .padding(start = 16.dp),
                                fontSize = 15.sp,
                                fontFamily = lexendFamily,
                                color = Color.White
                            )
                        }


                        //Juego 2
                        Button(
                            modifier = Modifier
                                .height(50.dp)
                                .weight(1f)
                                .padding(start = 10.dp),
                            onClick = {
                                if (student!!.games["level1"]?.contains(2) == true) {
                                    viewModel.disableGameForStudent(alumno.id, 1, 2)
                                } else {
                                    viewModel.enableGameForStudent(alumno.id, 1, 2)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (student!!.games["level1"]?.contains(2) == true) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(ButtonDefaults.IconSize)
                                    .absoluteOffset(x = (-40).dp)
                                    .graphicsLayer(translationX = 0.6f),
                                tint = if (student!!.games["level1"]?.contains(2) == true) Color.White else Color.Unspecified
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text(
                                text = "Partes del Cuerpo",
                                modifier = Modifier
                                    .padding(start = 16.dp),
                                fontSize = 15.sp,
                                fontFamily = lexendFamily,
                                color = Color.White
                            )
                        }

                        //Juego 3
                        Button(
                            modifier = Modifier
                                .height(50.dp)
                                .weight(1f)
                                .padding(start = 10.dp),
                            onClick = {
                                if (student!!.games["level1"]?.contains(3) == true) {
                                    viewModel.disableGameForStudent(alumno.id, 1, 3)
                                } else {
                                    viewModel.enableGameForStudent(alumno.id, 1, 3)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (student!!.games["level1"]?.contains(3) == true) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(ButtonDefaults.IconSize)
                                    .absoluteOffset(x = (-40).dp)
                                    .graphicsLayer(translationX = 0.6f),
                                tint = if (student!!.games["level1"]?.contains(3) == true) Color.White else Color.Unspecified
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text(
                                text = "Instrumentos",
                                modifier = Modifier
                                    .padding(start = 16.dp),
                                fontSize = 15.sp,
                                fontFamily = lexendFamily,
                                color = Color.White
                            )
                        }
                    }


                    //Nivel 2
                    Text(text = "Nivel 2", Modifier.padding(bottom = 40.dp, top = 40.dp))

                    Row(
                        modifier = Modifier
                            .width(1000.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        //Juego 1
                        Button(
                            modifier = Modifier
                                .height(50.dp)
                                .weight(1f)
                                .padding(start = 10.dp),
                            onClick = {
                                if (student!!.games["level2"]?.contains(1) == true) {
                                    viewModel.disableGameForStudent(alumno.id, 2, 1)
                                } else {
                                    viewModel.enableGameForStudent(alumno.id, 2, 1)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (student!!.games["level2"]?.contains(1) == true) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(ButtonDefaults.IconSize)
                                    .absoluteOffset(x = (-40).dp)
                                    .graphicsLayer(translationX = 0.6f),
                                tint = if (student!!.games["level2"]?.contains(1) == true) Color.White else Color.Unspecified
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text(
                                text = "Figurines",
                                modifier = Modifier
                                    .padding(start = 16.dp),
                                fontSize = 15.sp,
                                fontFamily = lexendFamily,
                                color = Color.White
                            )
                        }


                        //Juego 2
                        Button(
                            modifier = Modifier
                                .height(50.dp)
                                .weight(1f)
                                .padding(start = 10.dp),
                            onClick = {
                                if (student!!.games["level2"]?.contains(2) == true) {
                                    viewModel.disableGameForStudent(alumno.id, 2, 2)
                                } else {
                                    viewModel.enableGameForStudent(alumno.id, 2, 2)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (student!!.games["level2"]?.contains(2) == true) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(ButtonDefaults.IconSize)
                                    .absoluteOffset(x = (-40).dp)
                                    .graphicsLayer(translationX = 0.6f),
                                tint = if (student!!.games["level2"]?.contains(2) == true) Color.White else Color.Unspecified
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text(
                                text = "Alistate",
                                modifier = Modifier
                                    .padding(start = 16.dp),
                                fontSize = 15.sp,
                                fontFamily = lexendFamily,
                                color = Color.White
                            )
                        }

                        //Juego 3
                        Button(
                            modifier = Modifier
                                .height(50.dp)
                                .weight(1f)
                                .padding(start = 10.dp),
                            onClick = {
                                if (student!!.games["level2"]?.contains(3) == true) {
                                    viewModel.disableGameForStudent(alumno.id, 2, 3)
                                } else {
                                    viewModel.enableGameForStudent(alumno.id, 2, 3)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (student!!.games["level2"]?.contains(3) == true) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(ButtonDefaults.IconSize)
                                    .absoluteOffset(x = (-40).dp)
                                    .graphicsLayer(translationX = 0.6f),
                                tint = if (student!!.games["level2"]?.contains(3) == true) Color.White else Color.Unspecified
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text(
                                text = "Colorea",
                                modifier = Modifier
                                    .padding(start = 16.dp),
                                fontSize = 15.sp,
                                fontFamily = lexendFamily,
                                color = Color.White
                            )
                        }
                    }

                    //Nivel 3
                    Text(text = "Nivel 3", Modifier.padding(bottom = 40.dp, top = 40.dp))

                    Row(
                        modifier = Modifier
                            .width(1000.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        //Juego 1
                        Button(
                            modifier = Modifier
                                .height(50.dp)
                                .weight(1f)
                                .padding(start = 10.dp),
                            onClick = {
                                if (student!!.games["level3"]?.contains(1) == true) {
                                    viewModel.disableGameForStudent(alumno.id, 3, 1)
                                } else {
                                    viewModel.enableGameForStudent(alumno.id, 3, 1)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (student!!.games["level3"]?.contains(1) == true) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(ButtonDefaults.IconSize)
                                    .absoluteOffset(x = (-40).dp)
                                    .graphicsLayer(translationX = 0.6f),
                                tint = if (student!!.games["level3"]?.contains(1) == true) Color.White else Color.Unspecified
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text(
                                text = "Letras y Animales",
                                modifier = Modifier
                                    .padding(start = 16.dp),
                                fontSize = 15.sp,
                                fontFamily = lexendFamily,
                                color = Color.White
                            )
                        }


                        //Juego 2
                        Button(
                            modifier = Modifier
                                .height(50.dp)
                                .weight(1f)
                                .padding(start = 10.dp),
                            onClick = {
                                if (student!!.games["level3"]?.contains(2) == true) {
                                    viewModel.disableGameForStudent(alumno.id, 3, 2)
                                } else {
                                    viewModel.enableGameForStudent(alumno.id, 3, 2)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (student!!.games["level3"]?.contains(2) == true) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(ButtonDefaults.IconSize)
                                    .absoluteOffset(x = (-40).dp)
                                    .graphicsLayer(translationX = 0.6f),
                                tint = if (student!!.games["level3"]?.contains(2) == true) Color.White else Color.Unspecified
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text(
                                text = "Ordena Numeros",
                                modifier = Modifier
                                    .padding(start = 16.dp),
                                fontSize = 15.sp,
                                fontFamily = lexendFamily,
                                color = Color.White
                            )
                        }

                        //Juego 3
                        Button(
                            modifier = Modifier
                                .height(50.dp)
                                .weight(1f)
                                .padding(start = 10.dp),
                            onClick = {
                                if (student!!.games["level3"]?.contains(3) == true) {
                                    viewModel.disableGameForStudent(alumno.id, 3, 3)
                                } else {
                                    viewModel.enableGameForStudent(alumno.id, 3, 3)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (student!!.games["level3"]?.contains(3) == true) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(ButtonDefaults.IconSize)
                                    .absoluteOffset(x = (-40).dp)
                                    .graphicsLayer(translationX = 0.6f),
                                tint = if (student!!.games["level3"]?.contains(3) == true) Color.White else Color.Unspecified
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text(
                                text = "Colores",
                                modifier = Modifier
                                    .padding(start = 16.dp),
                                fontSize = 15.sp,
                                fontFamily = lexendFamily,
                                color = Color.White
                            )
                        }
                    }

                    //Nivel 4
                    Text(text = "Nivel 4", Modifier.padding(bottom = 40.dp, top = 40.dp))

                    Row(
                        modifier = Modifier
                            .width(1000.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        //Juego 1
                        Button(
                            modifier = Modifier
                                .height(50.dp)
                                .weight(1f)
                                .padding(start = 10.dp),
                            onClick = {
                                if (student!!.games["level4"]?.contains(1) == true) {
                                    viewModel.disableGameForStudent(alumno.id, 4, 1)
                                } else {
                                    viewModel.enableGameForStudent(alumno.id, 4, 1)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (student!!.games["level4"]?.contains(1) == true) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(ButtonDefaults.IconSize)
                                    .absoluteOffset(x = (-40).dp)
                                    .graphicsLayer(translationX = 0.6f),
                                tint = if (student!!.games["level4"]?.contains(1) == true) Color.White else Color.Unspecified
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text(
                                text = "Comunicador",
                                modifier = Modifier
                                    .padding(start = 16.dp),
                                fontSize = 15.sp,
                                fontFamily = lexendFamily,
                                color = Color.White
                            )
                        }


                        //Juego 2
                        Button(
                            modifier = Modifier
                                .height(50.dp)
                                .weight(1f)
                                .padding(start = 10.dp),
                            onClick = {
                                if (student!!.games["level4"]?.contains(2) == true) {
                                    viewModel.disableGameForStudent(alumno.id, 4, 2)
                                } else {
                                    viewModel.enableGameForStudent(alumno.id, 4, 2)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (student!!.games["level4"]?.contains(2) == true) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(ButtonDefaults.IconSize)
                                    .absoluteOffset(x = (-40).dp)
                                    .graphicsLayer(translationX = 0.6f),
                                tint = if (student!!.games["level4"]?.contains(2) == true) Color.White else Color.Unspecified
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text(
                                text = "Memorama",
                                modifier = Modifier
                                    .padding(start = 16.dp),
                                fontSize = 15.sp,
                                fontFamily = lexendFamily,
                                color = Color.White
                            )
                        }

                        //Juego 3
                        Button(
                            modifier = Modifier
                                .height(50.dp)
                                .weight(1f)
                                .padding(start = 10.dp),
                            onClick = {
                                if (student!!.games["level4"]?.contains(3) == true) {
                                    viewModel.disableGameForStudent(alumno.id, 4, 3)
                                } else {
                                    viewModel.enableGameForStudent(alumno.id, 4, 3)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (student!!.games["level4"]?.contains(3) == true) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(ButtonDefaults.IconSize)
                                    .absoluteOffset(x = (-40).dp)
                                    .graphicsLayer(translationX = 0.6f),
                                tint = if (student!!.games["level4"]?.contains(3) == true) Color.White else Color.Unspecified
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text(
                                text = "Rompecabezas",
                                modifier = Modifier
                                    .padding(start = 16.dp),
                                fontSize = 15.sp,
                                fontFamily = lexendFamily,
                                color = Color.White
                            )
                        }


                    }

                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}