package com.mertozan.membox.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mertozan.membox.localization.R.font as localizationFont

val poppinsFamily = FontFamily(
    Font(localizationFont.poppins_bold, FontWeight.Bold),
    Font(localizationFont.poppins_regular, FontWeight.Normal),
    Font(localizationFont.poppins_medium, FontWeight.Medium),
    Font(localizationFont.poppins_italic, FontWeight.Normal),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)