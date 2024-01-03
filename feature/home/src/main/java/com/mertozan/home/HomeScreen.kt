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
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertozan.membox.presentation.components.CustomText
import com.mertozan.membox.presentation.theme.ui.Pink
import com.mertozan.membox.presentation.theme.ui.Pink80
import com.mertozan.membox.presentation.theme.ui.TransparentBlue
import com.mertozan.membox.presentation.R.drawable as presR

@Composable
fun HomeScreen() {
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
                        text = "Welcome Mert",
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
                        fontSize = 30,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "profile",
                            modifier = Modifier.size(24.dp)
                        )
                        Image(
                            imageVector = Icons.Filled.Logout,
                            contentDescription = "logout",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
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
                    .fillMaxWidth(0.95f)
                    .fillMaxHeight(0.75f)
                    .align(alignment = Alignment.CenterHorizontally)
            ) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(Color.White)
                        .fillMaxSize(),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CustomText(
                            text = "Your Memories",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            fontSize = 20,
                            color = Pink,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start,
                        )
                        LazyColumn(
                            content = {
                                items(20) {
                                    MemoryItem(
                                        title = "Memory Title",
                                        description = "Memory Description",
                                        emoji = presR.heart_eyes,
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
        FloatingActionButton(
            onClick = { },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp),
            containerColor = Pink,
        ) {
            Image(
                imageVector = Icons.Filled.Add,
                contentDescription = "add_memory",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    Text(text = "Home Screen")
}