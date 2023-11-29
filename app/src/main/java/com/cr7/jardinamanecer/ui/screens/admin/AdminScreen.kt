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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cr7.jardinamanecer.R

data class Admins(
    val id: Int,
    val nombre: String,
    val edad: Int,
    val Nivel1: Int,
    val Nivel2: Int,
    val Nivel3: Int,
    val Nivel4: Int,
    val imagen: Int,
    val Total: Int
)

@Composable
fun AdminScreen(){


    Box(modifier = Modifier
        .paint(
            painterResource(id = R.drawable.menuscreen),
            contentScale = ContentScale.FillBounds)
        ) {


            //Datos de la base de datos
            val PruebaAdmin = listOf<Admins>(
                Admins(1, "Juan", 20, 1, 2, 2, 1, imagen = R.drawable.adminb, Total = Int.MAX_VALUE),
                Admins(1, "Juan", 20, 3, 2, 2, 1, imagen = R.drawable.adminb, Total = Int.MAX_VALUE),
                Admins(1, "Marta", 20, 4, 2, 2, 1, imagen = R.drawable.adming, Total = Int.MAX_VALUE),
                Admins(1, "Juan", 20, 1, 2, 2, 1, imagen = R.drawable.adminb, Total = Int.MAX_VALUE),
                Admins(1, "Sofia", 20, 1, 3, 2, 1, imagen = R.drawable.adming, Total = Int.MAX_VALUE),
                Admins(1, "Juan", 20, 1, 2, 2, 1, imagen = R.drawable.adminb, Total = Int.MAX_VALUE),
                Admins(1, "Juan", 20, 0, 2, 2, 1, imagen = R.drawable.adminb, Total = Int.MAX_VALUE),
                Admins(1, "Juan", 20, 1, 2, 1, 1, imagen = R.drawable.adminb, Total = Int.MAX_VALUE),

            )
            TablaAdmins(data = PruebaAdmin)


        }
    }



@Composable
fun TablaAdmins(data: List<Admins>) {
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
                AdminTableHeader()
            }
            itemsIndexed(data) { _, admin ->
                TableCell(admin)
            }
        }

    }
}


@Composable
fun AdminTableHeader() {
    Row(
        modifier = Modifier
            .width(1500.dp)
            .height(50.dp)
            .background(Color.White, RoundedCornerShape(10.dp))
            .border(1.dp, Color(173, 172, 172), RoundedCornerShape(10.dp))
            .padding(8.dp)
    ) {
        androidx.compose.material.Text(
            text = "Administradores",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color(244, 124, 0),
            modifier = Modifier.weight(1f)
        )

        // Encabezados de Nivel 1 a Nivel 4
        for (i in 1..4) {
            androidx.compose.material.Text(
                text = "Nivel $i",
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = Color(173, 172, 172),
                modifier = Modifier.weight(1f)
            )
        }

        // Total
        androidx.compose.material.Text(
            text = "Total",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color(244, 124, 0),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun TableCell(Admin: Admins) {
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
                painter = painterResource(id = Admin.imagen),
                contentDescription = "Imagen Alumno",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
            )

            Column (modifier = Modifier
                .weight(1f)) {
                //Nombre
                androidx.compose.material.Text(
                    text = Admin.nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp)
                )

                //Edad
                androidx.compose.material.Text(
                    text = "${Admin.edad} años",
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)



                )
            }

            // Nivel 1
            Text(text = Admin.Nivel1.toString(), modifier = Modifier. weight(1f))

            // Nivel 2
            Text(text = Admin.Nivel2.toString(), modifier = Modifier. weight(1f))


            // Nivel 3
            Text(text = Admin.Nivel3.toString(), modifier = Modifier. weight(1f))


            // Nivel 4
            Text(text = Admin.Nivel4.toString(), modifier = Modifier. weight(1f))

            // Total

            val total = Admin.Nivel1 + Admin.Nivel2 + Admin.Nivel3 + Admin.Nivel4
            Text(text = total.toString(), modifier = Modifier. weight(1f))
        }
    }
}

@Preview
@Composable
fun TablaAdminsPreview() {
    val Prueba = listOf<Admins>(
    )
    TablaAdmins(data = Prueba)
}
