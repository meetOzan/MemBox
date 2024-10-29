package com.mertozan.membox.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertozan.membox.presentation.theme.ui.LightGray200
import com.mertozan.membox.localization.R.string as localStr
import com.mertozan.membox.presentation.R.drawable as presDraw

@Composable
fun MemoryDayItem(
    color: Int,
    title: String,
    detail: String,
    onDetailClick: () -> Unit
) {

    var animateState by remember {
        mutableStateOf(true)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Box(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 18.dp)
                    .width(25.dp)
                    .clip(ShapeDefaults.Medium)
                    .background(color = colorResource(id = color))
            )
            Column(
                modifier = Modifier.padding(start = 10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                CustomText(text = title, fontWeight = FontWeight.Medium, fontSize = 16)
                CustomText(text = detail, fontSize = 14)
            }
        }
        Row {
            Image(
                painter = painterResource(id = presDraw.arrow_right),
                contentDescription = stringResource(id = localStr.arrow_right),
                modifier = Modifier.clickable {
                    animateState = !animateState
                }
            )
            AnimatedVisibility(visible = animateState) {
                ElevatedButton(
                    onClick = {
                        onDetailClick.invoke()
                    },
                    modifier = Modifier.clip(RoundedCornerShape(8.dp)),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = LightGray200
                    )
                ) {
                    CustomText(text = stringResource(id = localStr.detail))
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewOfMemoryDayItem() {
    MemoryDayItem(
        color = Color.Black.hashCode(),
        title = "Title",
        detail = "Detail",
        onDetailClick = {}
    )
}