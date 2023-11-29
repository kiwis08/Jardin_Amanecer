package com.cr7.jardinamanecer.ui.screens.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.ui.screens.AdminSignIn
import com.cr7.jardinamanecer.ui.theme.lexendFamily

@Composable
fun DefaultScreen(admin: Administrator) {

    Column(
        modifier = Modifier
            .offset(x = 100.dp, y = 100.dp)
    )
    {

        Box(
            modifier = Modifier
                .offset(x = 100.dp, y = 10.dp)
                .padding(bottom = 70.dp)

        ) {
            androidx.compose.material3.Text(
                text = "Tus Alumnos ${admin.name}",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(104, 180, 72)
                ),
                fontSize = 80.sp
            )
        }



        Row(
            modifier = Modifier
                .width(1000.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            //Nivel 1
            Column(
                modifier = Modifier
                    .background(
                        Color(244, 41, 175),
                        RoundedCornerShape(10.dp)
                    )
                    .width(160.dp)
                    .height(120.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Numeros
                Text(
                    text = admin.level1Students.toString(),
                    modifier = Modifier,
                    fontSize = 50.sp,
                    fontFamily = lexendFamily,
                    color = Color.White
                )

                // Texto
                Text(
                    text = "Estudiantes Nivel 1",
                    modifier = Modifier
                        .padding(top = 20.dp),
                    fontSize = 15.sp,
                    fontFamily = lexendFamily,
                    color = Color.White
                )
            }

            //Nivel 2
            Column(
                modifier = Modifier
                    .background(
                        Color(255, 109, 40),
                        RoundedCornerShape(10.dp)
                    )
                    .width(160.dp)
                    .height(120.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Numeros
                Text(
                    text = admin.level2Students.toString(),
                    modifier = Modifier,
                    fontSize = 50.sp,
                    fontFamily = lexendFamily,
                    color = Color.White
                )

                // Texto
                Text(
                    text = "Estudiantes Nivel 2",
                    modifier = Modifier
                        .padding(top = 20.dp),
                    fontSize = 15.sp,
                    fontFamily = lexendFamily,
                    color = Color.White
                )
            }
        }

        //Fila 2
        Row(
            modifier = Modifier
                .width(1000.dp)
                .padding(top = 40.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            //Nivel 1
            Column(
                modifier = Modifier
                    .background(
                        Color(12, 192, 223),
                        RoundedCornerShape(10.dp)
                    )
                    .width(160.dp)
                    .height(120.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Numeros
                Text(
                    text = admin.level3Students.toString(),
                    modifier = Modifier,
                    fontSize = 50.sp,
                    fontFamily = lexendFamily,
                    color = Color.White
                )

                // Texto
                Text(
                    text = "Estudiantes Nivel 3",
                    modifier = Modifier
                        .padding(top = 20.dp),
                    fontSize = 15.sp,
                    fontFamily = lexendFamily,
                    color = Color.White
                )
            }

            //Nivel 2
            Column(
                modifier = Modifier
                    .background(
                        Color(104, 180, 72),
                        RoundedCornerShape(10.dp)
                    )
                    .width(160.dp)
                    .height(120.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Numeros
                Text(
                    text = admin.level4Students.toString(),
                    modifier = Modifier,
                    fontSize = 50.sp,
                    fontFamily = lexendFamily,
                    color = Color.White
                )

                // Texto
                Text(
                    text = "Estudiantes Nivel 4",
                    modifier = Modifier
                        .padding(top = 20.dp),
                    fontSize = 15.sp,
                    fontFamily = lexendFamily,
                    color = Color.White
                )
            }



        }
    }

}

@Preview(widthDp = 1707,
    heightDp = 960)
@Composable
fun PreviewDefaultScreen(){
    DefaultScreen(admin = Administrator())
}