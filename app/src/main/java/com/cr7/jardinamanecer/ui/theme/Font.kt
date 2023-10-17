package com.cr7.jardinamanecer.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.cr7.jardinamanecer.R

@RequiresApi(Build.VERSION_CODES.Q)
val dmMonoFamily = FontFamily(
    Font(R.font.dmmono_light, FontWeight.Light),
    Font(R.font.dmmono_regular, FontWeight.Normal),
    Font(R.font.dmmono_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.dmmono_medium, FontWeight.Medium),
    Font(R.font.dmmono_lightitalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.dmmono_mediumitalic, FontWeight.Medium, FontStyle.Italic),
)