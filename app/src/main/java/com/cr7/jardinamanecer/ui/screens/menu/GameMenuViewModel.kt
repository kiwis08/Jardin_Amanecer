package com.cr7.jardinamanecer.ui.screens.menu

import androidx.lifecycle.ViewModel
import com.cr7.jardinamanecer.R
import com.cr7.jardinamanecer.navigation.Screens
import com.cr7.jardinamanecer.ui.screens.AppSettings
import com.cr7.jardinamanecer.ui.screens.admin.Student

class GameMenuViewModel: ViewModel() {
    val games = mutableListOf<Game>(
        Game(image = R.drawable.l1_animales, level = 1, number = 1, route = Screens.Level1Game1.route),
        Game(image = R.drawable.l1_partes_cuerpo, level = 1, number = 2, route = Screens.Level1Game2.route),
        Game(image = R.drawable.l1_instrumentos, level = 1, number = 3, route = Screens.Level1Game3.route),
        Game(image = R.drawable.l2_figurines, level = 2, number = 1, route = Screens.Level2Game1.route),
        Game(image = R.drawable.l2_colorea, level = 2, number = 2, route = Screens.Level2Game2.route),
        Game(image = R.drawable.l2_alistate, level = 2, number = 3, route = Screens.Level2Game3.route),
        Game(image = R.drawable.l3_ordena_numeros, level = 3, number = 1, route = Screens.Level3Game1.route),
        Game(image = R.drawable.l3_colores, level = 3, number = 2, route = Screens.Level3Game2.route),
        Game(image = R.drawable.l3_letrasanimales, level = 3, number = 3, route = Screens.Level3Game3.route),
        Game(image = R.drawable.l4_comunicador, level = 4, number = 1, route = Screens.Level4Game1.route),
        Game(image = R.drawable.l4_memorama, level = 4, number = 2, route = Screens.Level4Game2.route),
        Game(image = R.drawable.l4_rompecabezas, level = 4, number = 3, route = Screens.Level4Game3.route)
    )

    fun getStudentGames(student: Student?): List<Game> {
        return games.filter {
            student?.games?.get("level${it.level}")?.contains(it.number) ?: false
        }
    }




}