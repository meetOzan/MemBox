package com.mertozan.membox.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertozan.membox.presentation.R.drawable as presDraw

@Composable
fun MoodGraphic() {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clip(CircleShape),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .background(Color.Blue)
                .weight(1f)
                .fillMaxWidth()
                .clip(CircleShape),
        ) {
            Image(
                painter = painterResource(id = presDraw.cry),
                contentDescription = "",
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.Center)
            )
        }
        Box(
            modifier = Modifier
                .background(Color.Yellow)
                .weight(0.5f)
                .fillMaxWidth()
                .clip(CircleShape),
        ) {
            Image(
                painter = painterResource(id = presDraw.angry),
                contentDescription = "",
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.Center)
            )
        }
        Box(
            modifier = Modifier
                .background(Color.Red)
                .weight(1.5f)
                .fillMaxWidth()
                .clip(CircleShape),
        ) {
            Image(
                painter = painterResource(id = presDraw.heart_eyes),
                contentDescription = "",
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.Center)
            )
        }
        Box(
            modifier = Modifier
                .background(Color.Green)
                .weight(1f)
                .fillMaxWidth()
                .clip(CircleShape),
        ) {
            Image(
                painter = painterResource(id = presDraw.angry_cry),
                contentDescription = "",
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.Center)
            )
        }
        Box(
            modifier = Modifier
                .background(Color.Magenta)
                .weight(2f)
                .fillMaxWidth()
                .clip(CircleShape),
        ) {
            Image(
                painter = painterResource(id = presDraw.smile),
                contentDescription = "",
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun MoodGraphicsPreview() {
    MoodGraphic()
}