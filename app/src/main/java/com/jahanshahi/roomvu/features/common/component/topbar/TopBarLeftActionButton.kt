package com.jahanshahi.roomvu.features.common.component.topbar

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jahanshahi.roomvu.R

data class TopBarLeftActionButton(
    val iconContent: IconContent,
    val contentDescription: String? = null,
    val color: Color = Color(0xff007AFF),
    val size: Dp = 18.dp,
) {
    companion object {
        val BACK_BUTTON = TopBarLeftActionButton(
            iconContent = IconContent.ImageIcon(imageIcon = R.drawable.ic_back),
            contentDescription = "Back Button",
        )
        val CANCEL_BUTTON = TopBarLeftActionButton(
            iconContent = IconContent.TextIcon("Cancel"),
            contentDescription = "Cancel Button",
        )
    }
}
