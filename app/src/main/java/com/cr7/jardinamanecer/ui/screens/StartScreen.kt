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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cr7.jardinamanecer.navigation.Screens

@Composable
fun StartScreen(navController: NavController) {
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
                Text(
                    text = "Nuevo Amanecer",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displayLarge
                )

                Spacer(modifier = Modifier.fillMaxHeight(0.4f))

                Button(
                    onClick = {
                        navController.navigate(Screens.AdminSignIn.route)
                    },
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = Color.White,
                        contentColor = Color.Gray
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                        .height(50.dp)
                        .width(200.dp)
                ) {
                    Text(text = "Admin", fontSize = 24.sp)
                }

                Button(
                    onClick = {
                        navController.navigate(Screens.GameMenu.route)
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
                    Text(text = "Alumno", fontSize = 24.sp)
                }


            }
        }
    }
}