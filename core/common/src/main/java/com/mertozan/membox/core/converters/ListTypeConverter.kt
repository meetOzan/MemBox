package com.mertozan.membox.core.converters

import androidx.room.TypeConverter

class ListTypeConverter {

    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(",")
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(",")
    }

}