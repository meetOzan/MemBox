package com.mertozan.membox.core.consts

import com.mertozan.membox.model.Mood
import com.mertozan.membox.presentation.R
import com.mertozan.membox.presentation.theme.ui.AngryColor
import com.mertozan.membox.presentation.theme.ui.AngryCryColor
import com.mertozan.membox.presentation.theme.ui.HappyColor
import com.mertozan.membox.presentation.theme.ui.LoveColor
import com.mertozan.membox.presentation.theme.ui.SadColor

object Constants {

    const val ARGS_NAME = "name"

    const val DATABASE_NAME = "mem_database.db"

    val moodList = listOf(
        Mood(
            emojiDrawable = R.drawable.smile,
            emojiName = "Happy",
            emojiColor = HappyColor.hashCode()
        ),
        Mood(
            emojiDrawable = R.drawable.heart_eyes,
            emojiName = "Love",
            emojiColor = LoveColor.hashCode()
        ),
        Mood(
            emojiDrawable = R.drawable.cry,
            emojiName = "Sad",
            emojiColor = SadColor.hashCode()
        ),
        Mood(
            emojiDrawable = R.drawable.angry,
            emojiName = "Angry",
            emojiColor = AngryColor.hashCode()
        ),
        Mood(
            emojiDrawable = R.drawable.angry_cry,
            emojiName = "Angry Cry",
            emojiColor = AngryCryColor.hashCode()
        )
    )

}