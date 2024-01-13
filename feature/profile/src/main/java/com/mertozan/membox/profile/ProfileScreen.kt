package com.mertozan.membox.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertozan.membox.core.list.moodList
import com.mertozan.membox.presentation.components.CustomText
import com.mertozan.membox.presentation.theme.ui.Blue
import com.mertozan.membox.presentation.theme.ui.DarkWhite60
import com.mertozan.membox.presentation.theme.ui.Pink
import com.mertozan.membox.localization.R as localR

@Composable
fun ProfileScreen(
    profileUiState: ProfileUiState,
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 12.dp, top = 16.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomText(
                    text = stringResource(
                        localR.string.welcome,
                        profileUiState.profileName
                    ), fontSize = 28,
                    fontWeight = FontWeight.Bold
                )
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    Image(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Settings",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(shape = ShapeDefaults.Medium)
                    .background(
                        DarkWhite60
                    )
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CustomText(
                        text = stringResource(localR.string.statics_of_memories),
                        fontSize = 24,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                        color = Pink,
                        fontWeight = FontWeight.Bold
                    )
                    StaticsRow(
                        staticsName = stringResource(localR.string.total_memory),
                        staticsValue = profileUiState.profileTotalMemory,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    StaticsRow(
                        staticsName = stringResource(localR.string.memory_streak),
                        staticsValue = profileUiState.profileMemoryStreak,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    ElevatedButton(
                        onClick = { },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = Pink
                        ),
                        elevation = ButtonDefaults.elevatedButtonElevation(
                            defaultElevation = 5.dp,
                            pressedElevation = 8.dp,
                            disabledElevation = 0.dp
                        )
                    ) {
                        CustomText(
                            text = stringResource(localR.string.delete_all_memories),
                            fontSize = 16,
                            color = Color.White
                        )
                    }
                }
                Surface(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(shape = ShapeDefaults.Medium)
                        .background(Color.White)
                        .fillMaxWidth(),
                    shadowElevation = 24.dp
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxWidth(),
                    ) {
                        CustomText(
                            "Summary of your memories",
                            fontSize = 20,
                            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                        )
                        MoodGraphic(
                            happy = profileUiState.moodValueList[0],
                            sad = profileUiState.moodValueList[1],
                            angry = profileUiState.moodValueList[2],
                            love = profileUiState.moodValueList[3],
                            angryCry = profileUiState.moodValueList[4],
                        )
                        LazyColumn(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .clip(shape = ShapeDefaults.Medium)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            items(moodList.size) { index ->
                                StaticsRow(
                                    staticsName = moodList[index].emojiName,
                                    staticsValue =
                                    if (profileUiState.moodValueList[index] == 0.1f) {
                                        "0"
                                    } else {
                                        "${profileUiState.moodValueList[index]}"
                                    },
                                    nameColor = Color(moodList[index].emojiColor),
                                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StaticsRow(
    staticsName: String,
    staticsValue: String,
    nameColor: Color = Blue,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomText(
            text = staticsName,
            fontSize = 16,
            color = nameColor,
            fontWeight = FontWeight.Bold
        )
        CustomText(
            text = staticsValue,
            fontSize = 16,
            color = Color.Black,
            modifier = Modifier.padding(end = 8.dp),
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    ProfileScreen(
        profileUiState = ProfileUiState(
            profileName = "Mert",
            profileTotalMemory = "10",
            profileMemoryStreak = "5",
            moodValueList = mutableListOf(0.1f, 0.1f, 0.1f, 0.1f, 0.1f)
        )
    )
}