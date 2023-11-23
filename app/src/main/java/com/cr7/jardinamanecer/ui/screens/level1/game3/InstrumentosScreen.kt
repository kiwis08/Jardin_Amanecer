package com.cr7.jardinamanecer.ui.screens.level1.game3

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.navigation.Screens


@Composable
fun InstrumntosScreen( navController : NavController) {
        Log.e("Instrumentos", "Entro")

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Box {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color(255, 109, 40))
                    ) {


                        //Encabezado(Blanco)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .weight(1f)
                                .height(100.dp),
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
                                text = "Instrumentos",
                                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 30.dp, start = 15.dp)
                            )

                        }
                        
                        Row (modifier = Modifier
                            .fillMaxSize()
                            .weight(6f)
                        ){
                            InstrumentosDisp()
                        }
                    }
                }
            }
        }
