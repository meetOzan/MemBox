package com.mertozan.membox.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CustomPlaceHolder(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector,
    imageColor : Color = Color.White
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.6f)
            .padding(top = 24.dp)
            .then(modifier)
    ) {
        Image(
            imageVector = icon,
            contentDescription = null,
            colorFilter = ColorFilter.tint(imageColor),
            modifier = Modifier
                .size(120.dp)
                .alpha(0.6f)
        )
        CustomText(
            text = text,
            fontSize = 18,
            modifier = Modifier
                .padding(start = 36.dp, end = 36.dp, top = 24.dp)
                .align(Alignment.CenterHorizontally)
                .alpha(0.6f)
        )
    }
}