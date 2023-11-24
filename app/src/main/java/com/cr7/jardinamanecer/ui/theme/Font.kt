package com.cr7.jardinamanecer.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.cr7.jardinamanecer.R

@RequiresApi(Build.VERSION_CODES.Q)


val lexendFamily = FontFamily(
    Font(R.font.lexend_variablefont_wght, FontWeight.Light),
    Font(R.font.lexend_variablefont_wght, FontWeight.Normal),
    Font(R.font.lexend_variablefont_wght, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.lexend_variablefont_wght, FontWeight.Medium),
    Font(R.font.lexend_variablefont_wght, FontWeight.Light, FontStyle.Italic),
    Font(R.font.lexend_variablefont_wght, FontWeight.Medium, FontStyle.Italic),
)