package com.mertozan.membox.source.local

import com.mertozan.membox.source.local.entity.MemoryEntity
import com.mertozan.membox.source.local.entity.UserEntity

interface LocalSource {

    // Memory
    fun getAllMemories(): List<MemoryEntity>

    fun getMemoryCount(): Int

    fun getLocalMoods() : Map<String, Float>

    fun getMemoryByTitle(title: String): MemoryEntity

    fun addMemoryToLocal(memoryEntity: MemoryEntity)

    fun addAllMemoriesToLocal(memoryEntityList: List<MemoryEntity>)

    fun deleteMemoryFromLocal(memoryEntity: MemoryEntity)

    fun updateMemory(memoryEntity: MemoryEntity)

    // User
    fun getUser(): UserEntity

    fun addUserToLocal(userEntity: UserEntity)

    fun deleteUserFromLocal(userEntity: UserEntity)

    fun updateUser(userEntity: UserEntity)

    // Transfer
    fun transferMemoriesToLocal(memoryEntityList: List<MemoryEntity>)

    fun transferUserToLocal(userEntity: UserEntity)
    fun deleteAllMemories()
}