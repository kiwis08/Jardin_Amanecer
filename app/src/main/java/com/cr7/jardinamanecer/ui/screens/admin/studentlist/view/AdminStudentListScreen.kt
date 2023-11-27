package com.cr7.jardinamanecer.ui.screens.admin.studentlist.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.ui.screens.admin.studentlist.viewmodel.AdminStudentListViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cr7.jardinamanecer.ui.screens.admin.studentlist.model.Student
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AdminStudentListScreen(viewModel: AdminStudentListViewModel = viewModel()) {
    val studentList by viewModel.studentList.collectAsState(initial = emptyList())

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier.paint(
                painterResource(id = R.drawable.bolas),
                contentScale = ContentScale.FillBounds,
            )
        ) {
            Box(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(16.dp)
                    )
                    .background(Color.White)
                    .align(Alignment.Center)
            ) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(16.dp),
                    ) {
                        items(studentList, key = { it.id }) { student ->
                            StudentCard(student)
                        }
                    }
            }
        }
    }
}

@Composable
fun StudentCard(student: Student) {
    var dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    var birthDate = LocalDate.parse(student.birthdate, dateFormatter)
    var age = LocalDate.now().year - birthDate.year
    if (LocalDate.now().dayOfYear < birthDate.dayOfYear) {
        age--
    }


        Column {
            Text(student.name + " " + student.lastName, fontWeight = FontWeight.Bold)
            Text("$age años")
        }
}

@Preview
@Composable
fun StudentCardPreview() {
    StudentCard(
        student = Student(
            name = "Juan",
            lastName = "Perez",
            birthdate = "2003-08-15",
            cogLevel = 1,
            adminId = "123",
            games = mapOf(
                "level1" to listOf(1, 2, 3),
                "level2" to listOf(1, 2, 3),
                "level3" to listOf(1, 2, 3),
                "level4" to listOf(1, 2, 3),
            )
        )
    )
}