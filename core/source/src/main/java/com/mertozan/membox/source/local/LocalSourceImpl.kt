package com.mertozan.membox.source.local

import com.mertozan.membox.core.mapper.mapList
import com.mertozan.membox.core.mapper.mapModel
import com.mertozan.membox.domain.infrastructure.ResourceProvider
import com.mertozan.membox.domain.source.LocalSource
import com.mertozan.membox.model.Memory
import com.mertozan.membox.model.User
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

    override fun getAllMemories(): List<Memory> = memoryDao.getAllMemories().mapList {
        Memory(
            id = it.memoryId.toString(),
            title = it.memoryTitle,
            content = it.memoryContent,
            date = it.memoryDate,
            image = it.memoryImage,
            mood = it.memoryMoodImage,
            moodName = it.memoryMoodName,
        )
    }

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

    override fun getMemoryByTitle(title: String): Memory {
        return memoryDao.getMemoryByTitle(title).mapModel {
            Memory(
                id = it.memoryId.toString(),
                title = it.memoryTitle,
                content = it.memoryContent,
                date = it.memoryDate,
                image = it.memoryImage,
                mood = it.memoryMoodImage,
                moodName = it.memoryMoodName,
            )
        }
    }

    override fun addMemoryToLocal(memoryEntity: Memory) {
        memoryDao.addMemoryToLocal(memoryEntity.mapModel {
            MemoryEntity(
                memoryTitle = it.title,
                memoryContent = it.content,
                memoryDate = it.date,
                memoryImage = it.image,
                memoryMoodImage = it.mood,
                memoryMoodName = it.moodName,
            )
        })
    }

    override fun addAllMemoriesToLocal(memoryEntityList: List<Memory>) {
        memoryDao.addAllMemoriesToLocal(memoryEntityList.mapList {
            MemoryEntity(
                memoryTitle = it.title,
                memoryContent = it.content,
                memoryDate = it.date,
                memoryImage = it.image,
                memoryMoodImage = it.mood,
                memoryMoodName = it.moodName,
            )
        })
    }

    override fun deleteMemoryFromLocal(memoryEntity: Memory) {
        memoryDao.deleteMemoryFromLocal(memoryEntity.mapModel {
            MemoryEntity(
                memoryTitle = it.title,
                memoryContent = it.content,
                memoryDate = it.date,
                memoryImage = it.image,
                memoryMoodImage = it.mood,
                memoryMoodName = it.moodName,
            )
        })
    }

    override fun deleteAllMemories() {
        memoryDao.deleteAllMemories()
    }

    override fun updateMemory(memoryEntity: Memory) {
        memoryDao.updateMemory(memoryEntity.mapModel {
            MemoryEntity(
                memoryTitle = it.title,
                memoryContent = it.content,
                memoryDate = it.date,
                memoryImage = it.image,
                memoryMoodImage = it.mood,
                memoryMoodName = it.moodName,
            )
        })
    }

    override fun addUserToLocal(userEntity: User) {
        userDao.addUserToLocal(userEntity.mapModel {
            UserEntity(
                userId = 1,
                userName = it.name,
                userSurname = it.surname,
                userEmail = it.email,
                userPassword = it.password,
            )
        })
    }

    override fun deleteUserFromLocal() {
        userDao.deleteAllUsers()
    }

    override fun getUser(): User = userDao.getSingleUser().mapModel {
        User(
            name = it.userName,
            surname = it.userSurname,
            email = it.userEmail,
            password = it.userPassword,
        )
    }


    override fun updateUser(userEntity: User) {
        userDao.updateUser(userEntity.mapModel {
            UserEntity(
                userId = 1,
                userName = it.name,
                userSurname = it.surname,
                userEmail = it.email,
                userPassword = it.password,
            )
        })
    }

    override fun transferMemoriesToLocal(memoryEntityList: List<Memory>) {
        memoryDao.addAllMemoriesToLocal(memoryEntityList.mapList {
            MemoryEntity(
                memoryTitle = it.title,
                memoryContent = it.content,
                memoryDate = it.date,
                memoryImage = it.image,
                memoryMoodImage = it.mood,
                memoryMoodName = it.moodName,
            )
        })
    }

    override fun transferUserToLocal(userEntity: User) {
        userDao.addUserToLocal(userEntity.mapModel {
            UserEntity(
                userId = 1,
                userName = it.name,
                userSurname = it.surname,
                userEmail = it.email,
                userPassword = it.password,
            )
        })
    }

}
