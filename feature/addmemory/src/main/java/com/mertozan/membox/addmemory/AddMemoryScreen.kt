package com.mertozan.membox.addmemory

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mertozan.membox.model.Memory
import com.mertozan.membox.presentation.components.CustomText
import com.mertozan.membox.presentation.components.CustomTextField
import com.mertozan.membox.presentation.theme.ui.Blue
import com.mertozan.membox.presentation.theme.ui.DarkWhite60
import com.mertozan.membox.presentation.theme.ui.DarkWhite80
import com.mertozan.membox.presentation.theme.ui.DarkYellow
import com.mertozan.membox.presentation.theme.ui.Pink
import com.mertozan.membox.presentation.theme.ui.TransparentBlue
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.mertozan.membox.localization.R as locR
import com.mertozan.membox.presentation.R.drawable as presDrawable

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddMemoryScreen(
    onNavigate: () -> Unit,
    onAction: (AddMemoryAction) -> Unit,
    uiState: AddMemoryState,
) {

    val localDensity = LocalDensity.current
    var componentHeight by remember { mutableStateOf(0.dp) }
    val animatedComponentHeight by animateDpAsState(
        targetValue = componentHeight,
        label = "",
        animationSpec = tween(
            durationMillis = 800,
            easing = FastOutLinearInEasing
        )
    )

    val color by remember { mutableStateOf(Animatable(Pink)) }

    LaunchedEffect(Unit) {
        while (true) {
            color.animateTo(
                targetValue = Blue,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 8000),
                    repeatMode = RepeatMode.Reverse
                )
            )
            color.animateTo(
                targetValue = TransparentBlue,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 8000),
                    repeatMode = RepeatMode.Reverse
                )
            )
            color.animateTo(
                targetValue = Pink,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 8000),
                    repeatMode = RepeatMode.Reverse
                )
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomText(
                text = stringResource(id = locR.string.add_memory),
                color = color.value,
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 28,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                ) {
                    CustomTextField(
                        textTitle = uiState.title,
                        onValueChange = { newText ->
                            onAction(
                                AddMemoryAction.ChangeTitle(
                                    newTitle = newText
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp)
                            .border(
                                0.5.dp,
                                shape = ShapeDefaults.Medium,
                                color = DarkWhite80
                            )
                            .clip(ShapeDefaults.Medium),
                        placeHolderText = "Today's title",
                    )
                    Row(
                        modifier = Modifier
                            .padding(vertical = 16.dp),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .height(animatedComponentHeight)
                                .border(
                                    0.5.dp,
                                    shape = ShapeDefaults.Medium,
                                    color = Color.Black
                                )
                                .width(10.dp)
                                .clip(ShapeDefaults.Medium)
                                .background(
                                    color = uiState.emojiColor,
                                )
                        )
                        CustomTextField(
                            textTitle = uiState.description,
                            onValueChange = { newText ->
                                onAction(
                                    AddMemoryAction.ChangeDescription(
                                        newDescription = newText
                                    )
                                )
                            },
                            placeHolderText = "Long, long time ago...",
                            maxLines = 4,
                            modifier = Modifier
                                .weight(1f)
                                .border(
                                    0.5.dp,
                                    color = DarkWhite80,
                                    shape = ShapeDefaults.Medium
                                )
                                .clip(ShapeDefaults.Medium)
                                .animateContentSize(
                                    animationSpec = spring(
                                        dampingRatio = Spring.DampingRatioLowBouncy,
                                        stiffness = Spring.StiffnessLow
                                    )
                                )
                                .onGloballyPositioned {
                                    componentHeight = with(localDensity) { it.size.height.toDp() }
                                }
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        EmojiIconButton(
                            emojiDrawable = presDrawable.smile,
                            emojiName = "Happy",
                            emojiColor = Color.Magenta,
                            onAction = onAction
                        )
                        EmojiIconButton(
                            emojiDrawable = presDrawable.heart_eyes,
                            emojiName = "Love",
                            emojiColor = Color.Red,
                            onAction = onAction
                        )
                        EmojiIconButton(
                            emojiDrawable = presDrawable.cry,
                            emojiName = "Sad",
                            emojiColor = Color.Blue,
                            onAction = onAction
                        )
                        EmojiIconButton(
                            emojiDrawable = presDrawable.angry,
                            emojiName = "Angry",
                            emojiColor = DarkYellow,
                            onAction = onAction
                        )
                        EmojiIconButton(
                            emojiDrawable = presDrawable.angry_cry,
                            emojiName = "AngryCry",
                            emojiColor = Color.Green,
                            onAction = onAction
                        )
                    }
                    AnimatedVisibility(
                        visible = uiState.emojiName != "",
                        enter = slideInVertically(
                            tween(
                                durationMillis = 500,
                                easing = LinearOutSlowInEasing
                            )
                        )
                    ) {
                        CustomText(
                            text = uiState.emojiName,
                            color = uiState.emojiColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                CustomText(
                    text = "Add Photos for this memory",
                    fontSize = 20,
                    color = Pink,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    AddPhotoBox()
                    AddPhotoBox()
                    AddPhotoBox()
                    AddPhotoBox()
                }

            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, end = 8.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomText(
                text = "Save to Memories",
                fontSize = 18,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )
            IconButton(
                onClick = {
                    onAction(
                        AddMemoryAction.AddMemory(
                            memory = Memory(
                                title = uiState.title,
                                content = uiState.description,
                                date = getCurrentDate(),
                                mood = uiState.mood,
                                image = emptyList(),
                            ),
                            onNavigate = onNavigate
                        )
                    )
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Blue)
            ) {
                Image(
                    imageVector = Icons.Filled.CloudDone,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(4.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        }
    }
}

@Composable
fun AddPhotoBox(
    onClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .clip(ShapeDefaults.Medium)
            .background(
                color = DarkWhite60,
            )
    ) {
        IconButton(
            onClick = {
                // TODO: Add Photo
                onClick()
            },
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 16.dp)
        ) {
            Image(
                imageVector = Icons.Filled.AddAPhoto,
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
            )
        }
    }
}

@Composable
fun EmojiIconButton(
    emojiDrawable: Int,
    emojiName: String,
    emojiColor: Color,
    onAction: (AddMemoryAction) -> Unit,
) {
    IconButton(
        onClick = {
            onAction(
                AddMemoryAction.ChangeMood(
                    newMood = emojiDrawable,
                    newEmojiName = emojiName,
                    newEmojiColor = emojiColor
                )
            )
        }
    ) {
        Image(
            painter = painterResource(id = emojiDrawable),
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentDate(): String {
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return currentDate.format(formatter).toString()
}