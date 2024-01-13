package com.mertozan.membox.model

data class Memory(
    val title: String = "",
    val content: String = "",
    val date: String = "",
    val image: List<String> = emptyList(),
    val mood: Int = 0,
)