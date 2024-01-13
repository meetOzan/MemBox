package com.mertozan.membox.addmemory.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mertozan.membox.addmemory.AddMemoryAction

@Composable
fun MoodIconButton(
    emojiDrawable: Int,
    emojiName: String = "",
    emojiColor: Color = Color.Transparent,
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