package com.mertozan.membox.profile

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertozan.membox.core.consts.Constants.moodList
import com.mertozan.membox.presentation.components.CustomAlertDialog
import com.mertozan.membox.presentation.components.CustomText
import com.mertozan.membox.presentation.theme.ui.Blue
import com.mertozan.membox.presentation.theme.ui.DarkPink
import com.mertozan.membox.presentation.theme.ui.DarkWhite60
import com.mertozan.membox.presentation.theme.ui.Pink
import com.mertozan.membox.presentation.theme.ui.TransparentBlue
import com.mertozan.membox.presentation.theme.ui.TransparentGray
import com.mertozan.membox.localization.R as localR
import com.mertozan.membox.presentation.R.drawable as presDraw

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    profileUiState: ProfileUiState,
    onAction: ProfileAction.() -> Unit,
    onNavigate: () -> Unit,
) {

    Surface(
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            when {
                profileUiState.isLoading -> CircularProgressIndicator(
                    color = Pink,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )

                profileUiState.isError -> CustomText(
                    text = profileUiState.errorMessage,
                    fontSize = 16,
                    color = Color.Red,
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                profileUiState.isSuccess -> {
                    Card(
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = (-10).dp,
                            disabledElevation = 0.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = Blue
                        ),
                        shape = RoundedCornerShape(0.dp, 0.dp, 12.dp, 12.dp)
                    ) {
                        CustomText(
                            text = stringResource(
                                localR.string.welcome,
                                profileUiState.profileName
                            ),
                            fontSize = 28,
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(shape = ShapeDefaults.Medium)
                            .background(
                                DarkWhite60
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            CustomText(
                                text = stringResource(localR.string.statistics_of_memories),
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
                            ElevatedButton(
                                onClick = {
                                    onAction(ProfileAction.ChangeDialogState)
                                },
                                modifier = Modifier
                                    .padding(top = 32.dp, start = 16.dp, end = 16.dp)
                                    .fillMaxWidth(),
                                colors = ButtonDefaults.elevatedButtonColors(
                                    containerColor = Color.White
                                ),
                                elevation = ButtonDefaults.elevatedButtonElevation(
                                    defaultElevation = 5.dp,
                                    pressedElevation = 8.dp,
                                    disabledElevation = 0.dp
                                )
                            ) {
                                CustomText(
                                    text = stringResource(localR.string.settings),
                                    fontSize = 16,
                                    fontWeight = FontWeight.Bold,
                                    color = DarkPink
                                )
                            }
                            ElevatedButton(
                                onClick = {
                                    onAction(ProfileAction.ChangeDialogState)
                                },
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                colors = ButtonDefaults.elevatedButtonColors(
                                    containerColor = Color.White
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
                                    color = DarkPink,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            CustomText(
                                stringResource(id = localR.string.summary_of_your_memories),
                                fontSize = 22,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 16.dp)
                            )
                            Card(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                                    .fillMaxWidth(),
                                shape = ShapeDefaults.Medium,
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 5.dp,
                                    pressedElevation = 8.dp,
                                    disabledElevation = 0.dp
                                ),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                )
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .padding(vertical = 8.dp)
                                        .background(Color.White)
                                        .fillMaxWidth(),
                                ) {
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
    }

    if (profileUiState.isSettingsOpen) {
        AlertDialog(
            onDismissRequest = {
                onAction(ProfileAction.ChangeSettingsState)
            },
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .clip(MaterialTheme.shapes.large),
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                color = DarkWhite60
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CustomText(
                        text = stringResource(localR.string.settings),
                        fontSize = 24,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                            .clip(shape = ShapeDefaults.Medium)
                            .background(TransparentGray)
                            .fillMaxWidth()
                            .clickable {

                            },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = null,
                            modifier = Modifier.padding(start = 16.dp),
                            colorFilter = ColorFilter.tint(Blue)
                        )
                        CustomText(
                            text = stringResource(localR.string.edit_profile),
                            fontSize = 20,
                            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp, end = 16.dp),
                            color = Color.Black
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(vertical = 16.dp, horizontal = 16.dp)
                            .clip(shape = ShapeDefaults.Medium)
                            .background(TransparentGray)
                            .fillMaxWidth()
                            .clickable {
                                onAction(ProfileAction.ChangeSettingsState)
                                onAction(ProfileAction.SignOut)
                                onNavigate()
                                if (profileUiState.isSignedOut) {
                                    onAction(ProfileAction.DeleteAllMemoriesFromLocal)
                                    onAction(ProfileAction.DeleteUser)
                                }
                            },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            imageVector = Icons.Filled.Logout, contentDescription = null,
                            modifier = Modifier.padding(start = 16.dp),
                            colorFilter = ColorFilter.tint(Blue)
                        )
                        CustomText(
                            text = stringResource(localR.string.sign_out),
                            fontSize = 20,
                            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp, end = 16.dp),
                            color = Color.Black
                        )
                    }
                    TextButton(
                        onClick = { onAction(ProfileAction.ChangeSettingsState) },
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .padding(top = 24.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .background(
                                TransparentBlue
                            )
                    ) {
                        CustomText(
                            text = stringResource(localR.string.close),
                            fontSize = 16,
                            color = Color.Black
                        )

                    }
                }
            }
        }
    }

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
                onAction(ProfileAction.DeleteAllMemoriesNetwork)
                onAction(ProfileAction.DeleteAllMemoriesFromLocal)
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
            moodValueMap = mapOf(
                "😍" to 0.1f,
                "😭" to 0.1f,
                "😡" to 0.1f,
                "😊" to 0.1f,
                "😢" to 0.1f,
            ),
            memoryList = listOf(),
            isLoading = false,
            isError = false,
            isSuccess = true,
            errorMessage = "",
            isDialogOpen = false,
            isSettingsOpen = false,
            isSignedOut = false
        ),
        onAction = {},
        onNavigate = {}
    )
}