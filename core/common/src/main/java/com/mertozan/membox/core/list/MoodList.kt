package com.mertozan.membox.core.list

import com.mertozan.membox.model.Mood
import com.mertozan.membox.presentation.theme.ui.AngryColor
import com.mertozan.membox.presentation.theme.ui.AngryCryColor
import com.mertozan.membox.presentation.theme.ui.HappyColor
import com.mertozan.membox.presentation.theme.ui.LoveColor
import com.mertozan.membox.presentation.theme.ui.SadColor
import com.mertozan.membox.presentation.R.drawable as presDraw

val moodList = listOf(
    Mood(
        emojiDrawable = presDraw.smile,
        emojiName = "Happy",
        emojiColor = HappyColor.hashCode()
    ),
    Mood(
        emojiDrawable = presDraw.heart_eyes,
        emojiName = "Love",
        emojiColor = LoveColor.hashCode()
    ),
    Mood(
        emojiDrawable = presDraw.cry,
        emojiName = "Sad",
        emojiColor = SadColor.hashCode()
    ),
    Mood(
        emojiDrawable = presDraw.angry,
        emojiName = "Angry",
        emojiColor = AngryColor.hashCode()
    ),
    Mood(
        emojiDrawable = presDraw.angry_cry,
        emojiName = "Angry Cry",
        emojiColor = AngryCryColor.hashCode()
    )
)