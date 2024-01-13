package com.mertozan.home.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertozan.membox.presentation.components.CustomText
import com.mertozan.membox.presentation.theme.ui.DarkWhite60
import com.mertozan.membox.presentation.theme.ui.Pink
import com.mertozan.membox.presentation.R.drawable as presR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoryItem(
    title: String = "This is a memory item",
    description: String = "This is a memory description ".repeat(8),
    emoji: Int = presR.angry_cry,

    ) {

    var isExtended by remember {
        mutableStateOf(false)
    }

    val rotation by animateFloatAsState(targetValue = if (isExtended) 90f else 0f, label = "")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(DarkWhite60),
        onClick = {
            isExtended = !isExtended
        }
    ) {
        Column(
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = emoji),
                        contentDescription = "emoji_mood",
                        modifier = Modifier
                            .size(50.dp)
                    )
                    CustomText(
                        title,
                        fontSize = 18,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Image(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = "arrow_right",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(32.dp)
                        .rotate(rotation)
                )
            }
            if (isExtended) {
                Column(
                    Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth()
                ) {
                    CustomText(
                        description,
                        fontSize = 18,
                        modifier = Modifier.padding(8.dp)
                    )
                    TextButton(
                        onClick = { },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        CustomText(
                            text = "Read more",
                            fontSize = 16,
                            color = Pink,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MemoryItemPrev() {
    MemoryItem()
}