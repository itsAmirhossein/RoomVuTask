package com.jahanshahi.roomvu.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jahanshahi.roomvu.R

val SFBoldFontFamily = FontFamily(
    Font(R.font.sf_pro_display_bold, weight = FontWeight.Bold),
)
val SFMediumFontFamily = FontFamily(
    Font(R.font.sf_pro_display_medium, weight = FontWeight.Medium),
)

val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = SFMediumFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = SFBoldFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
)