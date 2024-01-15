package com.mertozan.membox.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mertozan.membox.core.converters.ListTypeConverter
import com.mertozan.membox.source.local.dao.MemoryDao
import com.mertozan.membox.source.local.dao.UserDao
import com.mertozan.membox.source.local.entity.MemoryEntity
import com.mertozan.membox.source.local.entity.UserEntity

@Database(
    entities = [
        MemoryEntity::class,
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListTypeConverter::class)
abstract class MemDatabase : RoomDatabase() {
    abstract fun memoryDao(): MemoryDao
    abstract fun userDao(): UserDao
}