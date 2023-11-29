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
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cr7.jardinamanecer.R
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cr7.jardinamanecer.navigation.Screens
import java.time.LocalDate
import java.time.format.DateTimeFormatter



@Composable
fun AlumnosScreen(navController: NavController = rememberNavController(), viewModel: AdminStudentListViewModel = viewModel()){
    val studentList by viewModel.studentList.collectAsState(initial = emptyList())

    val students = studentList.map { student ->
        var dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        var birthDate = LocalDate.parse(student.birthdate, dateFormatter)
        var age = LocalDate.now().year - birthDate.year
        if (LocalDate.now().dayOfYear < birthDate.dayOfYear) {
            age--
        }
        Alumno(
            id = student.id,
            nombre = student.name,
            edad = age,
            Nivel1 = student.cogLevel == 1,
            Nivel2 = student.cogLevel == 2,
            Nivel3 = student.cogLevel == 3,
            Nivel4 = student.cogLevel == 4,
            imagen = if (student.gender == "male") R.drawable.nino else R.drawable.nina
        )
    }


    Box(modifier = Modifier
        .paint(
            painterResource(id = R.drawable.adminfondo),
            contentScale = ContentScale.FillBounds)

    ) {
        TablaAlumnos(data = students, navController)


    }
}


@Composable
fun TablaAlumnos(data: List<Alumno>, navController: NavController) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxSize()
        .padding(16.dp),
        contentAlignment = Alignment.Center)
    {
        LazyColumn(
            modifier = Modifier
                .width(1300.dp)
                .height(500.dp)
        ) {
            item{
                TableHeader()
            }
            itemsIndexed(data) { _, alumno ->
                TableCell(alumno, navController)
            }
            }

    }
}


@Composable
fun TableHeader() {
    Row(
        modifier = Modifier
            .width(1500.dp)
            .height(70.dp)
            .background(Color.White, RoundedCornerShape(10.dp))
            .padding(8.dp)
    ) {
        Text(
            text = "Alumnos",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color(104, 180, 72),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(2f)
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
fun TableCell(Alumno: Alumno, navController: NavController) {
    Box(
        modifier = Modifier
            .width(1500.dp)
            .height(90.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(1.dp, Color(242, 242, 240), RoundedCornerShape(12.dp))
            .padding(5.dp)

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
                TextButton(
                    onClick = {
                        try {
                            navController.navigate(Screens.AdminPerfilAlumno.route)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = Alumno.nombre,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

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



