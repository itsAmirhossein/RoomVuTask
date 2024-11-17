package com.jahanshahi.roomvu.presentation.component.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.amirhossein.friendfinder.presentation.common.component.ripple_effect.RippleEffectHandler
import com.jahanshahi.roomvu.presentation.component.topbar.TopBarLeftActionButton
import com.jahanshahi.roomvu.presentation.component.topbar.TopBarRightActionButton
import com.jahanshahi.roomvu.presentation.component.topbar.TopBarTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomVuTopBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    topBarTitle: TopBarTitle,
    topBarLeftButton: TopBarLeftActionButton,
    onLeftActionButtonClick: () -> Unit,
    topBarRightActionButton: TopBarRightActionButton = TopBarRightActionButton.MORE_ACTION_BUTTON,
    onRightActionButtonClick: () -> Unit,
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .drawBehind {
            val borderThickness = 1.dp.toPx() // Bottom border thickness
            drawLine(
                color = Color.Gray,
                start = androidx.compose.ui.geometry.Offset(
                    0f,
                    size.height
                ), // Starting at the bottom-left
                end = androidx.compose.ui.geometry.Offset(
                    size.width,
                    size.height
                ), // Ending at the bottom-right
                strokeWidth = borderThickness // Line thickness
            )
        }
    ) {
        CenterAlignedTopAppBar(
            modifier = modifier.fillMaxWidth(),
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.White,
            ),
            expandedHeight = 44.dp,
            title = {
                Text(
                    text = topBarTitle.text,
                    style = TextStyle(
                        fontFamily = topBarTitle.fontFamily,
                        fontSize = topBarTitle.fontSize,
                        color = topBarTitle.color,
                    )
                )
            },
            navigationIcon = {
                IconButton(onClick = onLeftActionButtonClick) {
                    Icon(
                        painter = painterResource(id = topBarLeftButton.icon),
                        contentDescription = topBarLeftButton.contentDescription,
                        tint = topBarLeftButton.color,
                        modifier = Modifier.size(topBarLeftButton.size)
                    )
                }
            },
            actions = {
                RippleEffectHandler(
                    hasRippleEffect = false
                ) {
                    IconButton(onClick = onRightActionButtonClick) {
                        Icon(
                            painter = painterResource(id = topBarRightActionButton.icon),
                            contentDescription = topBarRightActionButton.contentDescription,
                            tint = topBarRightActionButton.color,
                            modifier = Modifier.size(topBarRightActionButton.size)
                        )
                    }
                }
            }
        )
    }
}