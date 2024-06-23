package com.mertozan.home.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertozan.membox.presentation.components.CustomText
import com.mertozan.membox.presentation.theme.ui.LightPink
import com.mertozan.membox.presentation.theme.ui.Pink
import com.mertozan.membox.localization.R as locR
import com.mertozan.membox.presentation.R.drawable as presR

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MemoryItem(
    title: String,
    emoji: Int = presR.angry_cry,
    onDetailClick: () -> Unit = {}
) {

    var isExtended by remember {
        mutableStateOf(false)
    }

    val rotation by animateFloatAsState(targetValue = if (isExtended) 0f else 90f, label = "")

    Row(
        Modifier
            .fillMaxWidth()
            .height(80.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp),
            shape = CardDefaults.elevatedShape,
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(Color.White),
            onClick = {
                isExtended = !isExtended
            }
        ) {
            Row(
                modifier = Modifier
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioNoBouncy,
                            stiffness = Spring.StiffnessMediumLow
                        )
                    ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(if (isExtended) 0.6f else 1f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = emoji),
                            contentDescription = stringResource(locR.string.emoji_mood),
                            modifier = Modifier
                                .size(50.dp)
                        )
                        CustomText(
                            title,
                            fontSize = 14,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .fillMaxWidth(if (isExtended) 0.7f else 0.8f)
                                .basicMarquee()
                        )
                    }
                    Image(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = stringResource(locR.string.arrow_right),
                        modifier = Modifier
                            .padding(end = 8.dp, start = 8.dp)
                            .size(32.dp)
                            .rotate(rotation)
                    )
                }
            }
        }
        AnimatedVisibility(visible = isExtended) {
            TextButton(
                onClick = onDetailClick,
                modifier = Modifier
                    .clip(ShapeDefaults.Large)
                    .padding(horizontal = 6.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = (LightPink).copy(alpha = 0.5f)
                )
            ) {
                CustomText(
                    text = stringResource(locR.string.details),
                    fontSize = 14,
                    color = Pink,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MemoryItemPrev() {
    MemoryItem("This is a memory item".repeat(2))
}