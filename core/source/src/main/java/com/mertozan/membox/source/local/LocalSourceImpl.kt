package com.mertozan.membox.source.local

import com.mertozan.membox.source.local.dao.MemoryDao
import com.mertozan.membox.source.local.dao.UserDao
import com.mertozan.membox.source.local.entity.MemoryEntity
import com.mertozan.membox.source.local.entity.UserEntity
import javax.inject.Inject

class LocalSourceImpl @Inject constructor(
    private val userDao: UserDao,
    private val memoryDao: MemoryDao,
) : LocalSource {

    override fun getAllMemories(): List<MemoryEntity> = memoryDao.getAllMemories()


    override fun getMemoryCount(): Int = memoryDao.getMemoryCount()


    override fun addMemoryToLocal(memoryEntity: MemoryEntity) {
        memoryDao.addMemoryToLocal(memoryEntity)
    }

    override fun addAllMemoriesToLocal(memoryEntityList: List<MemoryEntity>) {
        memoryDao.addAllMemoriesToLocal(memoryEntityList)
    }

    override fun deleteMemoryFromLocal(memoryEntity: MemoryEntity) {
        memoryDao.deleteMemoryFromLocal(memoryEntity)
    }

    override fun updateMemory(memoryEntity: MemoryEntity) {
        memoryDao.updateMemory(memoryEntity)
    }

    override fun addUserToLocal(userEntity: UserEntity) {
        userDao.addUserToLocal(userEntity)
    }

    override fun deleteUserFromLocal(userEntity: UserEntity) {
        userDao.deleteUserFromLocal(userEntity)
    }

    override fun getUser(): UserEntity = userDao.getSingleUser()


    override fun updateUser(userEntity: UserEntity) {
        userDao.updateUser(userEntity)
    }

    override fun transferMemoriesToLocale(memoryEntityList: List<MemoryEntity>) {
        memoryDao.addAllMemoriesToLocal(memoryEntityList)
    }

    override fun transferUserToLocal(userEntity: UserEntity) {
        userDao.addUserToLocal(userEntity)
    }

}
