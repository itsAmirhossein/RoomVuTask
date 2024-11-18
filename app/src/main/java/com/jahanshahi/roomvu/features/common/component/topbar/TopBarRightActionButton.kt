package com.jahanshahi.roomvu.features.common.component.topbar

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jahanshahi.roomvu.R

data class TopBarRightActionButton(
    val iconContent: IconContent,
    val contentDescription: String? = null,
    val color: Color = Color(0xff344054),
    val size : Dp = 18.dp,
){
    companion object{
        val MORE_ACTION_BUTTON = TopBarRightActionButton(
            iconContent = IconContent.ImageIcon(imageIcon = R.drawable.ic_more),
            contentDescription = "More Button"
        )
        val SAVE_ACTION_BUTTON = TopBarRightActionButton(
            iconContent = IconContent.TextIcon(text = "Save"),
            contentDescription = "Save Button"
        )
    }
}
