package com.dev.thriftswap.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class TopBarConfig(
    val title: String = "",
    val navIcon: ImageVector? = null,
    val actionIcon: ImageVector? = null,
    val onBackArrowClicked: (() -> Unit)? = null,
    val onActionClicked: (() -> Unit)? = null
)

