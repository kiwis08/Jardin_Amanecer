package com.cr7.jardinamanecer.ui.screens

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.navigation.Screens
import com.cr7.jardinamanecer.ui.screens.admin.AdminSignInViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AdminSignIn(navController: NavController, viewModel: AdminSignInViewModel = viewModel()) {
    val state by viewModel.state
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

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
                    .offset(x = 200.dp, y = 100.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.maestra),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 60.dp)
                        .width(600.dp)
                        .height(600.dp)
                )
            }

            //Boton de Regreso Alumno
            Box(
                modifier = Modifier
                    .offset(x = 103.dp, y = 50.dp)
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.regresar),
                    contentDescription = null,
                    modifier = Modifier
                        .size(45.dp)
                        .clickable {
                            navController.navigate(Screens.Start.route)
                        }
                )
            }


            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {

                Box(modifier = Modifier.fillMaxHeight()) {


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



                    //Campos User y Password
                    Box (
                        modifier = Modifier
                            .align(Alignment.Center)
                            .offset(x = 150.dp, y = 50.dp)
                    ) {

                        //Email
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .offset(x = -250.dp, y = 40.dp))
                        {

                            OutlinedTextField(
                                value = state.email,
                                singleLine = true,
                                onValueChange = viewModel::onEmailChange,
                                label = { Text("Email") },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    containerColor = Color.White,
                                    textColor = Color.Black,
                                ),
                                shape = CircleShape,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .width(350.dp),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                keyboardActions = KeyboardActions(onDone = {
                                    keyboardController?.hide()
                                })
                            )
                        }
                    }


                    //Password
                    Box (
                        modifier = Modifier
                            .align(Alignment.Center)
                            .offset(x = -100.dp, y = 180.dp)
                    ) {

                        OutlinedTextField(
                            value = state.password,
                            onValueChange = viewModel::onPasswordChange,
                            singleLine = true,
                            label = { Text("Password") },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                containerColor = Color.White,
                                textColor = Color.Black,
                            ),
                            shape = CircleShape,
                            modifier = Modifier
                                .padding(16.dp)
                                .width(350.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            keyboardActions = KeyboardActions(onDone = {
                                keyboardController?.hide()
                            })

                        )
                    }

                    Box (modifier = Modifier
                        .align(Alignment.Center)
                        .offset(x = -100.dp, y = 300.dp)) {

                        Button(
                            onClick = {
                                viewModel.viewModelScope.launch {
                                    println("Ingresando...")
                                    try {
                                        viewModel.authenticate(state.email, state.password)
                                        println("Ingresado como administrador")
                                        println(viewModel.getAdminId())
                                        navController.navigate(Screens.AdminHome.route)
                                    } catch (e: Exception) {
                                        Toast.makeText(
                                            context,
                                            "Error: ${e.message}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            },
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = Color(38, 171, 226),
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .padding(16.dp)
                                .height(50.dp)
                                .width(200.dp)
                        ) {
                            Text(text = "Ingresar", fontSize = 24.sp)
                        }
                    }
                }

            }
        }
    }
}

@Preview(widthDp = 1707,
    heightDp = 960)
@Composable
fun PreviewAdminLog(){
    val navController = rememberNavController()
    AdminSignIn(navController)
}