package com.jahanshahi.roomvu.presentation.component.topbar

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jahanshahi.roomvu.R

data class TopBarRightActionButton(
    @DrawableRes val icon :Int,
    val contentDescription: String? = null,
    val color: Color = Color(0xff344054),
    val size : Dp = 16.dp,
){
    companion object{
        val MORE_ACTION_BUTTON = TopBarRightActionButton(
            icon = R.drawable.ic_more,
            contentDescription = "Option Button"
        )
    }
}
