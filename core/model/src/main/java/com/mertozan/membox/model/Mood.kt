package com.mertozan.membox.model

data class Mood(
    val emojiDrawable: Int,
    val emojiName: String,
    val emojiColor: Int,
    val isVisible : Boolean = true,
)