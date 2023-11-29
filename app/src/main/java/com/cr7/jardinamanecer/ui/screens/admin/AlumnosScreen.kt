package com.cr7.jardinamanecer.ui.screens.admin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cr7.jardinamanecer.R

data class Alumno(
    val id: Int,
    val nombre: String,
    val edad: Int,
    val Nivel1: Boolean,
    val Nivel2: Boolean,
    val Nivel3: Boolean,
    val Nivel4: Boolean,
    val imagen: Int
)


@Composable
fun AlumnosScreen(){
    Box(modifier = Modifier
        .paint(
            painterResource(id = R.drawable.menuscreen),
            contentScale = ContentScale.FillBounds)
    ) {


        //Datos de la base de datos
        val Prueba = listOf<Alumno>(
            Alumno(1, "Juan", 20, true, Nivel2 = false, Nivel3 = false, Nivel4 = true, imagen = R.drawable.nino ),
            Alumno(2, "Maria", 20, false, Nivel2 = false, Nivel3 = true, Nivel4 = true, imagen = R.drawable.nina),
            Alumno(3, "Jose", 20, false, Nivel2 = true, Nivel3 = false, Nivel4 = true, imagen = R.drawable.nino),
            Alumno(4, "Juan", 20, true, Nivel2 = false, Nivel3 = false, Nivel4 = false, imagen = R.drawable.nino),
            Alumno(2, "Maria", 20, false, Nivel2 = false, Nivel3 = true, Nivel4 = false, imagen = R.drawable.nina),
            Alumno(3, "Jose", 20, false, Nivel2 = true, Nivel3 = false, Nivel4 = false, imagen = R.drawable.nino),
            Alumno(4, "Juan", 20, true, Nivel2 = false, Nivel3 = false, Nivel4 = false, imagen = R.drawable.nino),
            Alumno(2, "Maria", 20, false, Nivel2 = false, Nivel3 = true, Nivel4 = false, imagen = R.drawable.nina),
            Alumno(3, "Jose", 20, false, Nivel2 = false, Nivel3 = false, Nivel4 = true, imagen = R.drawable.nino),
            Alumno(4, "Juan", 20, true, Nivel2 = false, Nivel3 = false, Nivel4 = false, imagen = R.drawable.nino),
            Alumno(2, "Maria", 20, false, Nivel2 = false, Nivel3 = true, Nivel4 = false, imagen = R.drawable.nina),
            Alumno(3, "Jose", 20, false, Nivel2 = false, Nivel3 = false, Nivel4 = true, imagen = R.drawable.nino),
            Alumno(4, "Juan", 20, true, Nivel2 = false, Nivel3 = false, Nivel4 = false, imagen = R.drawable.nino),
        )
        TablaAlumnos(data = Prueba)


    }
}


@Composable
fun TablaAlumnos(data: List<Alumno>) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxSize()
        .padding(16.dp),
        contentAlignment = Alignment.Center)
    {
        LazyColumn(
            modifier = Modifier
                .width(1500.dp)
                .height(700.dp)
        ) {
            item{
                TableHeader()
            }
            itemsIndexed(data) { _, alumno ->
                TableCell(alumno)
            }
            }

    }
}


@Composable
fun TableHeader() {
    Row(
        modifier = Modifier
            .width(1500.dp)
            .height(50.dp)
            .background(Color.White, RoundedCornerShape(10.dp))
            .border(1.dp, Color(173, 172, 172), RoundedCornerShape(10.dp))
            .padding(8.dp)
    ) {
        Text(
            text = "Alumnos",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color(104, 180, 72),
            modifier = Modifier.weight(2f) // Ajusta el peso según sea necesario
        )

        // Encabezados de Nivel 1 a Nivel 4
        for (i in 1..4) {
            Text(
                text = "Nivel $i",
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = Color(173, 172, 172),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun TableCell(Alumno: Alumno) {
    Box(
        modifier = Modifier
            .width(1500.dp)
            .height(80.dp)
            .background(Color(237, 237, 237), RoundedCornerShape(10.dp))
            .border(1.dp, Color(173, 172, 172), RoundedCornerShape(10.dp))
            .padding(8.dp)

    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier. fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = Alumno.imagen),
                contentDescription = "Imagen Alumno",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
            )

            Column (modifier = Modifier
                .weight(1f)) {
                //Nombre
                Text(
                    text = Alumno.nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp)
                )

                //Edad
                Text(
                    text = "${Alumno.edad} años",
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)



                )
            }

            // Nivel 1
            if (Alumno.Nivel1){
            Icon(imageVector = Icons.Default.CheckCircle,
                contentDescription = "Pertenece Nivel 1",
                tint = Color(244, 13, 165),
                modifier = Modifier.weight(1f)
            )
            }else{
                Icon(imageVector = Icons.Default.CheckCircle,
                    contentDescription = "No Pertenece Nivel 1",
                    tint = Color.White,
                    modifier = Modifier.weight(1f)
                )
            }

            // Nivel 2
            if (Alumno.Nivel2){
                Icon(imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Pertenece Nivel 1",
                    tint = Color(244, 13, 165),
                    modifier = Modifier.weight(1f)
                )
            }else{
                Icon(imageVector = Icons.Default.CheckCircle,
                    contentDescription = "No Pertenece Nivel 1",
                    tint = Color.White,
                    modifier = Modifier.weight(1f)
                )
            }

            // Nivel 3
            if (Alumno.Nivel3){
                Icon(imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Pertenece Nivel 1",
                    tint = Color(244, 13, 165),
                    modifier = Modifier.weight(1f)
                )
            }else{
                Icon(imageVector = Icons.Default.CheckCircle,
                    contentDescription = "No Pertenece Nivel 1",
                    tint = Color.White,
                    modifier = Modifier.weight(1f)
                )
            }

            // Nivel 4
            if (Alumno.Nivel4){
                Icon(imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Pertenece Nivel 1",
                    tint = Color(244, 13, 165),
                    modifier = Modifier.weight(1f)
                )
            }else{
                Icon(imageVector = Icons.Default.CheckCircle,
                    contentDescription = "No Pertenece Nivel 1",
                    tint = Color.White,
                    modifier = Modifier.weight(1f)
                )
            }

        }
    }
}

@Preview
@Composable
fun TablaPreview() {
    val Prueba = listOf<Alumno>(
        Alumno(1, "Juan Jose Suarez Mena", 20, true, Nivel2 = false, Nivel3 = false, Nivel4 = false, imagen = R.drawable.nino ),
        Alumno(2, "Maria del Rocio Cavazos Lara", 20, false, Nivel2 = false, Nivel3 = false, Nivel4 = true, imagen = R.drawable.nina),
        Alumno(3, "Damian Joel Martinez Sosa", 20, false, Nivel2 = true, Nivel3 = false, Nivel4 = true, imagen = R.drawable.nino),
        Alumno(4, "Juan Manuel Garcia Sosa", 20, true, Nivel2 = false, Nivel3 = false, Nivel4 = true, imagen = R.drawable.nino),
    )
    TablaAlumnos(data = Prueba)
}
