package com.mertozan.membox.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertozan.membox.presentation.components.CoilImage
import com.mertozan.membox.presentation.components.CustomText
import com.mertozan.membox.presentation.theme.ui.LightPink
import com.mertozan.membox.presentation.theme.ui.Pink
import com.mertozan.membox.presentation.theme.ui.TransparentBlue
import com.mertozan.membox.localization.R as localR
import com.mertozan.membox.presentation.R.drawable as presDraw

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailScreen(
    uiState: DetailUiState
) {

    val scrollState = rememberScrollState()

    val pagerState = rememberPagerState(pageCount = {
        var pageCount = 0
        for (i in uiState.memory.image) {
            if (i != "") {
                pageCount += 1
            }
        }
        pageCount
    })

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(0.dp, 0.dp, 12.dp, 12.dp),
            colors = CardDefaults.cardColors(
                containerColor = LightPink
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 12.dp
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 4.dp, top = 8.dp)
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    CustomText(
                        text = stringResource(localR.string.details_of),
                        modifier = Modifier,
                        textAlign = TextAlign.Start,
                        fontSize = 18,
                        fontStyle = FontStyle.Italic
                    )
                    CustomText(
                        text = uiState.memory.title,
                        modifier = Modifier.basicMarquee(),
                        textAlign = TextAlign.Center,
                        fontSize = 24,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )
                }
                Image(
                    painter = painterResource(id = presDraw.angry),
                    contentDescription = stringResource(localR.string.mood_image),
                    modifier = Modifier.size(72.dp)
                )
            }
            Row(
                modifier = Modifier.padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomText(
                    text = "Date of Memory: ",
                    modifier = Modifier.padding(start = 16.dp, end = 2.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16,
                )
                CustomText(
                    text = uiState.memory.date,
                    modifier = Modifier.padding(start = 2.dp, end = 16.dp),
                    fontSize = 16,
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomText(
                text = stringResource(localR.string.memory_title),
                fontWeight = FontWeight.Bold,
                fontSize = 20,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                textAlign = TextAlign.Start
            )
            CustomText(
                text = uiState.memory.title,
                fontSize = 18,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomText(
                text = "Memory Content",
                fontWeight = FontWeight.Bold,
                fontSize = 20,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                textAlign = TextAlign.Start
            )
            CustomText(
                text = uiState.memory.content,
                fontSize = 18,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Start
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth()
                .size(400.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(TransparentBlue),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            CustomText(
                text = stringResource(localR.string.images_of_your_memory),
                modifier = Modifier.padding(vertical = 8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20,
                color = Color.White,
            )
            Box(
                modifier = Modifier
                    .padding(bottom = 24.dp, start = 24.dp, end = 24.dp)
                    .fillMaxSize(1f)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                HorizontalPager(state = pagerState) { page ->
                    CoilImage(
                        data = uiState.memory.image[page],
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp))
                            .padding(horizontal = 8.dp)
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            ElevatedButton(
                onClick = { },
                colors = ButtonDefaults.elevatedButtonColors(containerColor = Pink)
            ) {
                CustomText(
                    text = "Update Memory",
                    modifier = Modifier.padding(vertical = 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16,
                    color = Color.White,
                )
            }
            Spacer(
                modifier = Modifier.width(8.dp)
            )
            ElevatedButton(
                onClick = { },
                colors = ButtonDefaults.elevatedButtonColors(containerColor = Pink)
            ) {
                CustomText(
                    text = "Delete Memory",
                    modifier = Modifier.padding(vertical = 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16,
                    color = Color.White,
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun DetailScreenPrev() {
    DetailScreen(DetailUiState.initial)
}