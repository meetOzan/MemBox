package com.mertozan.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertozan.home.component.MemoryItem
import com.mertozan.membox.model.Memory
import com.mertozan.membox.model.MemoryList
import com.mertozan.membox.presentation.components.CustomPlaceHolder
import com.mertozan.membox.presentation.components.CustomText
import com.mertozan.membox.presentation.theme.ui.DarkWhite60
import com.mertozan.membox.presentation.theme.ui.DarkYellow
import com.mertozan.membox.presentation.theme.ui.LightBlack
import com.mertozan.membox.presentation.theme.ui.LightPink
import com.mertozan.membox.presentation.theme.ui.Pink
import com.mertozan.membox.presentation.theme.ui.Pink60
import com.mertozan.membox.presentation.theme.ui.Pink80
import com.mertozan.membox.presentation.theme.ui.TransparentBlue
import com.mertozan.membox.localization.R as locR

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    memoryList: List<Memory>,
    uiState: HomeUiState,
    onAction: (HomeAction) -> Unit,
    onDetailNavigate: (String) -> Unit,
    onAddMemoryNavigate: () -> Unit,
    onProfileNavigate: () -> Unit,
) {

    val memoryColumnList =
        memoryList.groupBy { it.date }.toSortedMap().map {
            MemoryList(
                date = it.key,
                list = it.value
            )
        }.sortedByDescending { it.date }

    memoryColumnList.forEach {
        if (it.date == "${onAction(HomeAction.GetCurrentDate)}") {
            onAction(HomeAction.ChangeNotificationStatus(false))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 8.dp
                ),
                shape = RectangleShape
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Pink80,
                                    TransparentBlue
                                ),
                                tileMode = TileMode.Clamp
                            )
                        ),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .align(Alignment.CenterHorizontally),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            CustomText(
                                text = stringResource(locR.string.membox),
                                modifier = Modifier.padding(
                                    vertical = 16.dp,
                                    horizontal = 8.dp
                                ),
                                fontSize = 30,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                            IconButton(
                                onClick = onProfileNavigate,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(8.dp)
                                    .background(
                                        color = DarkWhite60.copy(alpha = 0.7f),
                                        shape = MaterialTheme.shapes.small
                                    ),
                            ) {
                                Image(
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = stringResource(locR.string.profile),
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                        AnimatedVisibility(uiState.isMemoryAdded) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .padding(vertical = 8.dp, horizontal = 8.dp)
                                    .clip(MaterialTheme.shapes.extraLarge)
                                    .background(DarkWhite60.copy(0.4f)),
                            ) {
                                IconButton(
                                    onClick = {
                                        onAction(HomeAction.ChangeNotificationStatus(!uiState.isMemoryAdded))
                                    },
                                    modifier = Modifier
                                        .padding(end = 12.dp, top = 8.dp)
                                        .align(Alignment.End)
                                        .size(18.dp)
                                ) {
                                    Image(
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = null,
                                        modifier = Modifier,
                                        colorFilter = ColorFilter.tint(Color.DarkGray)
                                    )
                                }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .padding(horizontal = 16.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(0.6f)
                                    ) {
                                        CustomText(
                                            text = stringResource(locR.string.today_s_reminder),
                                            fontSize = 14,
                                            color = Color.DarkGray,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Start,
                                        )
                                        CustomText(
                                            text = stringResource(locR.string.you_haven_t_added_any_memories_today),
                                            fontSize = 10,
                                            color = Color.Black,
                                            textAlign = TextAlign.Start,
                                        )
                                    }
                                    Image(
                                        imageVector = Icons.Default.NotificationsActive,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(
                                                end = 20.dp,
                                                top = 8.dp,
                                                bottom = 8.dp
                                            )
                                            .size(64.dp)
                                            .rotate(15f),
                                        colorFilter = ColorFilter.tint(
                                            DarkYellow.copy(alpha = 0.8f)
                                        )
                                    )
                                }
                                Spacer(modifier = Modifier.height(18.dp))
                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(Color.White)
                    .fillMaxSize(),
            ) {
                when {
                    uiState.isLoading -> CircularProgressIndicator(
                        color = Pink,
                        modifier = Modifier.align(Alignment.Center)
                    )

                    uiState.isError -> {
                        throw RuntimeException(uiState.errorMessage)
                    }

                    else -> {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.dp, top = 8.dp, end = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                CustomText(
                                    text = stringResource(locR.string.your_memories),
                                    modifier = Modifier,
                                    fontSize = 20,
                                    color = Pink60,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start,
                                )
                            }
                            if (memoryList.isEmpty()) {
                                CustomPlaceHolder(
                                    text = stringResource(locR.string.no_memories_yet),
                                    icon = Icons.Filled.CloudOff,
                                    imageColor = Pink,
                                )
                            } else {
                                LazyColumn(
                                    content = {
                                        memoryColumnList.forEach { memoryList ->
                                            stickyHeader {
                                                CustomText(
                                                    text = memoryList.date,
                                                    modifier = Modifier
                                                        .padding(
                                                            start = 8.dp,
                                                            top = 8.dp,
                                                            end = 8.dp
                                                        )
                                                        .fillMaxWidth(),
                                                    fontSize = 14,
                                                    color = LightBlack,
                                                    textAlign = TextAlign.Start,
                                                )
                                            }
                                            items(memoryList.list.size) { memory ->
                                                MemoryItem(
                                                    title = memoryList.list[memory].title,
                                                    emoji = memoryList.list[memory].mood,
                                                    onDetailClick = {
                                                        onDetailNavigate(
                                                            memoryList.list[memory].title
                                                        )
                                                    },
                                                )
                                            }
                                        }
                                    },
                                    modifier = Modifier
                                        .padding(vertical = 8.dp)
                                        .clip(MaterialTheme.shapes.medium)
                                )
                            }
                        }
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = onAddMemoryNavigate,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 12.dp, end = 8.dp),
            containerColor = LightPink,
        ) {
            Image(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(locR.string.add_memory),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        memoryList = listOf(
            Memory(
                title = "title",
                content = "description",
                date = "date",
                image = listOf("image"),
                mood = 1
            )
        ),
        uiState = HomeUiState.initial(),
        onAction = {},
        onDetailNavigate = {},
        onAddMemoryNavigate = {},
        onProfileNavigate = {},
    )
}