package com.mertozan.membox.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.mertozan.membox.source.local.entity.MemoryEntity

@Dao
interface MemoryDao {

    @Upsert
    fun addMemoryToLocal(memory: MemoryEntity)

    @Upsert
    fun addAllMemoriesToLocal(memoryList: List<MemoryEntity>)

    @Delete
    fun deleteMemoryFromLocal(memory: MemoryEntity)

    @Query("SELECT * FROM memory_entity")
    fun getAllMemories(): List<MemoryEntity>

    @Query("SELECT COUNT(*) FROM memory_entity")
    fun getMemoryCount(): Int

    @Update
    fun updateMemory(memory: MemoryEntity)

}