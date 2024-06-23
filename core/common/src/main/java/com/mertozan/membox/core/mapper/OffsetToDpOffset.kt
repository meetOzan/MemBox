package com.mertozan.membox.core.mapper

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

fun Offset.toDpOffset(density: Density): DpOffset {
    val xDp = this.x / density.density
    val yDp = this.y / density.density
    return DpOffset(xDp.dp, yDp.dp)
}