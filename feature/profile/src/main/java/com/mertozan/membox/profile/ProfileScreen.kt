package com.mertozan.membox.profile

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertozan.membox.core.list.moodList
import com.mertozan.membox.presentation.components.CustomAlertDialog
import com.mertozan.membox.presentation.components.CustomText
import com.mertozan.membox.presentation.theme.ui.Blue
import com.mertozan.membox.presentation.theme.ui.DarkWhite60
import com.mertozan.membox.presentation.theme.ui.Pink
import com.mertozan.membox.localization.R as localR
import com.mertozan.membox.presentation.R.drawable as presDraw

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(
    profileUiState: ProfileUiState,
    onAction: ProfileAction.() -> Unit = {},
) {

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            when {
                profileUiState.isLoading -> CircularProgressIndicator(
                    color = Pink,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxSize()
                )

                profileUiState.isError -> CustomText(
                    text = profileUiState.errorMessage,
                    fontSize = 16,
                    color = Color.Red,
                    modifier = Modifier.fillMaxSize(),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                profileUiState.isSuccess -> {
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
                            onClick = {
                            },
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        ) {
                            Image(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = stringResource(localR.string.settings),
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
                                staticsValue = profileUiState.memoryList.size.toString(),
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                            StaticsRow(
                                staticsName = stringResource(localR.string.memory_streak),
                                staticsValue = profileUiState.profileMemoryStreak,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                            ElevatedButton(
                                onClick = {
                                    onAction(ProfileAction.ChangeDialogState)
                                },
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
                                    stringResource(id = localR.string.summary_of_your_memories),
                                    fontSize = 20,
                                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                                )
                                MoodGraphic(
                                    happy = profileUiState.moodValueMap[stringResource(id = localR.string.happy)]
                                        ?: 0.1f,
                                    sad = profileUiState.moodValueMap[stringResource(id = localR.string.sad)]
                                        ?: 0.1f,
                                    angry = profileUiState.moodValueMap[stringResource(id = localR.string.angry)]
                                        ?: 0.1f,
                                    love = profileUiState.moodValueMap[stringResource(id = localR.string.love)]
                                        ?: 0.1f,
                                    angryCry = profileUiState.moodValueMap[stringResource(id = localR.string.angry_cry)]
                                        ?: 0.1f,
                                )
                                LazyColumn(
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp, vertical = 8.dp)
                                        .clip(shape = ShapeDefaults.Medium)
                                        .fillMaxWidth(),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    items(moodList.size, key = { it }) { index ->
                                        StaticsRow(
                                            staticsName = moodList[index].emojiName,
                                            staticsValue =
                                            if (profileUiState.moodValueMap[moodList[index].emojiName] == 0.1f) {
                                                stringResource(localR.string._0)
                                            } else {
                                                "${profileUiState.moodValueMap[moodList[index].emojiName]?.toInt()}"
                                            },
                                            nameColor = Color(moodList[index].emojiColor),
                                            modifier = Modifier
                                                .animateItemPlacement(
                                                    animationSpec = tween(
                                                        durationMillis = 500,
                                                        delayMillis = 100 * index
                                                    )
                                                )
                                                .padding(horizontal = 24.dp, vertical = 8.dp)
                                        )
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
    }

    // TODO Add Animation
    if (profileUiState.isDialogOpen) {
        CustomAlertDialog(
            title = stringResource(localR.string.are_you_sure),
            body = stringResource(localR.string.are_you_wanted_to_delete_all_this_memories),
            positiveButtonName = stringResource(localR.string.yes),
            negativeButtonName = stringResource(localR.string.no),
            drawable = presDraw.shocked,
            onDismissClick = {
                onAction(ProfileAction.ChangeDialogState)
            },
            onPositiveAction = {
                onAction(ProfileAction.DeleteAllMemories)
                onAction(ProfileAction.ChangeDialogState)
            },
            onNegativeAction = {
                onAction(ProfileAction.ChangeDialogState)
            }
        )
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
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
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
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    ProfileScreen(
        profileUiState = ProfileUiState(
            profileName = "Mert",
            profileMemoryStreak = "5",
        )
    )
}