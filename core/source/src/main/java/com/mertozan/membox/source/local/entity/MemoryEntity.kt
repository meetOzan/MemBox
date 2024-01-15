package com.mertozan.membox.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("memory_entity")
data class MemoryEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("memory_id")
    val memoryId: Int,

    @ColumnInfo("memory_title")
    val memoryTitle: String,

    @ColumnInfo("memory_content")
    val memoryContent: String,

    @ColumnInfo("memory_date")
    val memoryDate: String,

    @ColumnInfo("memory_image")
    val memoryImage: List<String>,

    @ColumnInfo("memory_mood_image")
    val memoryMoodImage: Int,

    @ColumnInfo("memory_mood_name")
    val memoryMoodName: String

)