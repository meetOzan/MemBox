package com.mertozan.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertozan.home.component.MemoryItem
import com.mertozan.membox.model.Memory
import com.mertozan.membox.model.MemoryList
import com.mertozan.membox.presentation.components.CustomText
import com.mertozan.membox.presentation.components.SearchBar
import com.mertozan.membox.presentation.theme.ui.DarkGray
import com.mertozan.membox.presentation.theme.ui.LightWhite
import com.mertozan.membox.presentation.theme.ui.Pink80
import com.mertozan.membox.presentation.theme.ui.TransparentBlue
import com.mertozan.membox.presentation.theme.ui.goldmanFamily
import kotlinx.coroutines.launch
import com.mertozan.membox.localization.R as locR
import com.mertozan.membox.presentation.R.drawable as presentationR

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

    val memoryDayPagerState = rememberPagerState(pageCount = { 2 })
    val coroutineScope = rememberCoroutineScope()

    val isCurrentDaySelected by remember {
        mutableStateOf(
            true
        )
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
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CustomText(
                                text = stringResource(locR.string.membox),
                                fontSize = 30,
                                color = DarkGray,
                                fontWeight = FontWeight.Bold,
                                fontFamily = goldmanFamily
                            )
                            Row(
                                verticalAlignment = Alignment.Bottom
                            ) {
                                IconButton(
                                    onClick = onProfileNavigate,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(8.dp)
                                        .background(
                                            color = LightWhite,
                                            shape = MaterialTheme.shapes.small
                                        )
                                ) {
                                    Image(
                                        painter = painterResource(id = presentationR.notification_none),
                                        contentDescription = stringResource(locR.string.profile),
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                                IconButton(
                                    onClick = onProfileNavigate,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(8.dp)
                                        .background(
                                            color = LightWhite,
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
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CustomText(
                                text = "Hello, ",
                                textAlign = TextAlign.Start,
                                fontSize = 20,
                                fontWeight = FontWeight.Medium,
                            )
                            CustomText(
                                text = uiState.networkUser.username,
                                textAlign = TextAlign.Start,
                                fontSize = 20,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        }
                        CustomText(
                            text = "Let's look at your Memories",
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 24.dp),
                            fontSize = 20,
                            color = LightWhite,
                            fontWeight = FontWeight.Medium
                        )
                        SearchBar(hint = "Search in your memories")
                    }
                }
            }
            Box(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(Color.White)
                    .fillMaxSize(),
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                ) {
                    item {
                        CustomText(
                            text = "My Memories",
                            fontSize = 18,
                            fontWeight = FontWeight.Medium
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(32.dp)
                        ) {
                            CustomText(
                                text = "Today",
                                fontSize = 15,
                                fontWeight = if (memoryDayPagerState.currentPage == 0) FontWeight.Bold else FontWeight.Normal,
                                modifier = Modifier.clickable {
                                    coroutineScope.launch {
                                        memoryDayPagerState.animateScrollToPage(1)
                                    }
                                }
                            )
                            CustomText(
                                text = "Yesterday",
                                fontSize = 15,
                                fontWeight = if (memoryDayPagerState.currentPage == 1) FontWeight.Bold else FontWeight.Normal,
                                modifier = Modifier.clickable {
                                    coroutineScope.launch {
                                        memoryDayPagerState.animateScrollToPage(1)
                                    }
                                }
                            )
                        }
                        HorizontalPager(state = memoryDayPagerState) {
                            when (memoryDayPagerState.currentPage) {
                                0 -> {
                                }
                                1 ->{

                                }
                            }
                        }
                    }
                }

                /*when {
                    uiState.isLoading -> CircularProgressIndicator(
                        color = Pink,
                        modifier = Modifier.align(Alignment.Center)
                    )

                    uiState.isError -> {
                        throw RuntimeException(uiState.errorMessage)
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                        ) {
                            item {
                                CustomText(text = "My Memories", fontSize = 18)
                            }
                        }
                    }
                }*/
            }
        }

    }
}

@Composable
fun DayMemoriesList(modifier: Modifier = Modifier, items: List<Memory>) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
    ) {
        items(items.size) { index ->

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