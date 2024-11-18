package com.jahanshahi.roomvu.features.video.info.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TagChip(
    modifier: Modifier = Modifier,
    factor: Float,
    content: @Composable RowScope.() -> Unit) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape((100*factor).dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF7C67E5)
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = (2*factor).dp, horizontal = (6*factor).dp),
        ) {
            content()
        }
    }
}