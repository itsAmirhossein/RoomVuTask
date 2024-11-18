package com.jahanshahi.roomvu.features.common.component.topbar

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.jahanshahi.roomvu.ui.theme.Typography

data class TopBarTitle(
    val text :String,
    val fontFamily: FontFamily? = Typography.bodyLarge.fontFamily, // TODO
    val fontSize: TextUnit = 16.sp,
    val color: Color = Color.Black,
)
