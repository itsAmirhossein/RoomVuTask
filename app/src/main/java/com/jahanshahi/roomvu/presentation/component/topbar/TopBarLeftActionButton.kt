package com.jahanshahi.roomvu.presentation.component.topbar

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jahanshahi.roomvu.R

data class TopBarLeftActionButton(
    @DrawableRes val icon: Int,
    val contentDescription: String? = null,
    val color: Color = Color(0xff007AFF),
    val size: Dp = 16.dp,
) {
    companion object {
        val BACK_BUTTON = TopBarLeftActionButton(
            icon = R.drawable.ic_back,
            contentDescription = "Back Button",
        )
    }
}
