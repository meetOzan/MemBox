package com.mertozan.membox.domain.source

import com.mertozan.membox.model.Memory
import com.mertozan.membox.model.User

interface LocalSource {

    // Memory
    fun getAllMemories(): List<Memory>

    fun getMemoryCount(): Int

    fun getLocalMoods() : Map<String, Float>

    fun getMemoryByTitle(title: String): Memory

    fun addMemoryToLocal(memoryEntity: Memory)

    fun addAllMemoriesToLocal(memoryEntityList: List<Memory>)

    fun deleteMemoryFromLocal(memoryEntity: Memory)

    fun updateMemory(memoryEntity: Memory)

    // User
    fun getUser(): User?

    fun addUserToLocal(userEntity: User)

    fun deleteUserFromLocal()

    fun updateUser(userEntity: User)

    // Transfer
    fun transferMemoriesToLocal(memoryEntityList: List<Memory>)

    fun transferUserToLocal(userEntity: User)
    fun deleteAllMemories()
}