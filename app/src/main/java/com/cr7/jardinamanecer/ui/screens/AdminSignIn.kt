package com.cr7.jardinamanecer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminSignIn(navController: NavController) {
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color(0xFFD4F6F6),
    ) {
        Box {
            Box(
                modifier = Modifier
                    .size(400.dp)
                    .clip(CircleShape)
                    .background(Color.Yellow)
                    .align(Alignment.Center),
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .clip(RectangleShape)
                    .background(Color(0xFF26ABE2))
                    .align(Alignment.BottomCenter),
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.fillMaxHeight(0.15f))

                Text(
                    text = "Nuevo Amanecer",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displayLarge
                )

                Spacer(modifier = Modifier.fillMaxHeight(0.5f))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.White,
                        textColor = Color.White,
                    ),
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(16.dp),
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.White,
                        textColor = Color.White,
                    ),
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(16.dp),
                )

                Button(
                    onClick = {
                        println("Ingresar")
                    },
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = Color.White,
                        contentColor = Color.Gray
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                        .height(50.dp)
                        .width(200.dp),
                ) {
                    Text(text = "Ingresar", fontSize = 24.sp)
                }


            }
        }
    }
}