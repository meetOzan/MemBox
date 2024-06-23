package com.mertozan.membox.profile

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertozan.membox.presentation.theme.ui.AngryColor
import com.mertozan.membox.presentation.theme.ui.AngryCryColor
import com.mertozan.membox.presentation.theme.ui.HappyColor
import com.mertozan.membox.presentation.theme.ui.LoveColor
import com.mertozan.membox.presentation.theme.ui.SadColor
import com.mertozan.membox.presentation.R.drawable as presDraw

@Composable
fun MoodGraphic(
    happy: Float,
    sad: Float,
    angry: Float,
    love: Float,
    angryCry: Float,
) {

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(CircleShape),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(happy)
                .background(HappyColor)
                .fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(id = presDraw.smile),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center)
            )
        }
        Box(
            modifier = Modifier
                .background(LoveColor)
                .weight(love)
                .fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(id = presDraw.heart_eyes),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center)
            )
        }
        Box(
            modifier = Modifier
                .background(SadColor)
                .weight(sad)
                .fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(id = presDraw.cry),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center)
            )
        }
        Box(
            modifier = Modifier
                .background(AngryColor)
                .weight(angry)
                .fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(id = presDraw.angry),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center)
            )
        }
        Box(
            modifier = Modifier
                .background(AngryCryColor)
                .weight(angryCry)
                .fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(id = presDraw.angry_cry),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun MoodGraphicsPreview() {
    MoodGraphic(
        happy = 0.2f,
        sad = 0.2f,
        angry = 0.2f,
        love = 0.2f,
        angryCry = 0.2f,
    )
}