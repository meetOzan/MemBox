package com.mertozan.membox.source.local

import com.mertozan.membox.core.resource.ResourceProvider
import com.mertozan.membox.source.local.dao.MemoryDao
import com.mertozan.membox.source.local.dao.UserDao
import com.mertozan.membox.source.local.entity.MemoryEntity
import com.mertozan.membox.source.local.entity.UserEntity
import javax.inject.Inject
import com.mertozan.membox.localization.R as localR

class LocalSourceImpl @Inject constructor(
    private val userDao: UserDao,
    private val memoryDao: MemoryDao,
    private val resourceProvider: ResourceProvider,
) : LocalSource {

    override fun getAllMemories(): List<MemoryEntity> = memoryDao.getAllMemories()

    override fun getMemoryCount(): Int = memoryDao.getMemoryCount()

    override fun getLocalMoods(): Map<String, Float> {
        val moodMap = mutableMapOf(
            resourceProvider.getString(localR.string.love) to 0.1f,
            resourceProvider.getString(localR.string.happy) to 0.1f,
            resourceProvider.getString(localR.string.sad) to 0.1f,
            resourceProvider.getString(localR.string.angry) to 0.1f,
            resourceProvider.getString(localR.string.angry_cry) to 0.1f,
        )
        for (memory in memoryDao.getAllMemories()) {
            memory.memoryMoodName.let { mood ->
                if (moodMap.containsKey(mood)) {
                    moodMap[mood] = moodMap[mood]!! + 1f
                } else {
                    moodMap[mood] = 0.1f
                }
            }
        }
        return moodMap
    }

    override fun getMemoryByTitle(title: String): MemoryEntity {
        return memoryDao.getMemoryByTitle(title)
    }

    override fun addMemoryToLocal(memoryEntity: MemoryEntity) {
        memoryDao.addMemoryToLocal(memoryEntity)
    }

    override fun addAllMemoriesToLocal(memoryEntityList: List<MemoryEntity>) {
        memoryDao.addAllMemoriesToLocal(memoryEntityList)
    }

    override fun deleteMemoryFromLocal(memoryEntity: MemoryEntity) {
        memoryDao.deleteMemoryFromLocal(memoryEntity)
    }

    override fun deleteAllMemories() {
        memoryDao.deleteAllMemories()
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

    override fun transferMemoriesToLocal(memoryEntityList: List<MemoryEntity>) {
        memoryDao.addAllMemoriesToLocal(memoryEntityList)
    }

    override fun transferUserToLocal(userEntity: UserEntity) {
        userDao.addUserToLocal(userEntity)
    }

}
