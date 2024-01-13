package com.mertozan.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertozan.home.component.MemoryItem
import com.mertozan.membox.model.Memory
import com.mertozan.membox.presentation.components.CustomPlaceHolder
import com.mertozan.membox.presentation.components.CustomText
import com.mertozan.membox.presentation.theme.ui.Pink
import com.mertozan.membox.presentation.theme.ui.Pink80
import com.mertozan.membox.presentation.theme.ui.TransparentBlue
import com.mertozan.membox.localization.R as locR

@Composable
fun HomeScreen(
    memoryList: List<Memory>,
    uiState: HomeUiState,
    onAddMemoryNavigate: () -> Unit,
    onProfileNavigate: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp, top = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .align(alignment = Alignment.CenterEnd),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomText(
                        text = stringResource(locR.string.membox),
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
                        fontSize = 30,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(
                        onClick = onProfileNavigate,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(8.dp)
                    ) {
                        Image(
                            imageVector = Icons.Filled.Person,
                            contentDescription = stringResource(locR.string.profile),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Pink80,
                                TransparentBlue
                            ),
                            tileMode = TileMode.Clamp
                        )
                    )
                    .fillMaxWidth()
                    .fillMaxHeight(0.85f)
                    .align(alignment = Alignment.CenterHorizontally)
            ) {
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

                        uiState.isSuccess -> {
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
                                        color = Pink,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Start,
                                    )
                                    IconButton(
                                        onClick = { },
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                    ) {
                                        Image(
                                            imageVector = Icons.Filled.Sort,
                                            contentDescription = "sort",
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                }
                                if (memoryList.isEmpty()) {
                                    CustomPlaceHolder(
                                        text = "No memories yet :(",
                                        icon = Icons.Filled.CloudOff,
                                        imageColor = Pink,
                                    )
                                } else {
                                    LazyColumn(
                                        content = {
                                            items(memoryList.size) { memory ->
                                                MemoryItem(
                                                    title = memoryList[memory].title,
                                                    description = memoryList[memory].content,
                                                    emoji = memoryList[memory].mood,
                                                )
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
        }
        FloatingActionButton(
            onClick = onAddMemoryNavigate,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 12.dp, end = 8.dp),
            containerColor = Pink,
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
        onAddMemoryNavigate = {},
        onProfileNavigate = {}
    )
}