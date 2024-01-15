package com.mertozan.membox.repository

import android.content.Context
import android.net.Uri
import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.core.mapper.mapList
import com.mertozan.membox.core.mapper.mapModel
import com.mertozan.membox.model.Memory
import com.mertozan.membox.source.local.LocalSource
import com.mertozan.membox.source.local.entity.MemoryEntity
import com.mertozan.membox.source.network.FirebaseSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MemRepositoryImpl @Inject constructor(
    private val firebaseSource: FirebaseSource,
    private val localSource: LocalSource,
) : MemRepository {

    override fun addMemory(memory: Memory, onNavigate: () -> Unit): Flow<ResponseState<Unit>> {
        return flow {
            emit(ResponseState.Loading)
            firebaseSource.addMemory(memory, onNavigate)
            emit(ResponseState.Success(Unit))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun getAllMemories(): Flow<ResponseState<List<Memory>>> {
        return flow {
            emit(ResponseState.Loading)
            firebaseSource.getAllMemories()
            emit(ResponseState.Success(firebaseSource.getAllMemories()))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun getMemoryByDate(date: String): Flow<ResponseState<List<Memory>>> {
        return flow {
            emit(ResponseState.Loading)
            firebaseSource.getMemoryByDate(date)
            emit(ResponseState.Success(firebaseSource.getMemoryByDate(date)))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun uploadImageStorage(
        uri: Uri,
        context: Context,
        onSuccess: (String, String) -> Unit,
        onFailure: (String) -> Unit,
    ): Flow<ResponseState<Unit>> {
        return flow {
            emit(ResponseState.Loading)
            firebaseSource.uploadImageToStorage(uri, context, onSuccess, onFailure)
            emit(ResponseState.Success(Unit))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun uploadImageToFirestore(
        imagesUrl: List<String>,
        imageName: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
    ): Flow<ResponseState<Unit>> {
        return flow {
            emit(ResponseState.Loading)
            firebaseSource.uploadImageToFirestore(imagesUrl, imageName, onSuccess, onFailure)
            emit(ResponseState.Success(Unit))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun getMoodsFromMemories(): Flow<ResponseState<Map<String, Float>>> {
        return flow {
            emit(ResponseState.Loading)
            firebaseSource.getMoodsFromMemories()
            emit(ResponseState.Success(firebaseSource.getMoodsFromMemories()))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun deleteAllMemories(): Flow<ResponseState<Unit>> {
        return flow {
            emit(ResponseState.Loading)
            firebaseSource.deleteAllMemories()
            emit(ResponseState.Success(Unit))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun deleteMemoryFromLocale(memory: Memory): Flow<ResponseState<Unit>> {
        return flow {
            emit(ResponseState.Loading)
            localSource.deleteMemoryFromLocal(memory.mapModel {
                MemoryEntity(
                    it.id,
                    it.title,
                    it.content,
                    it.date,
                    it.image,
                    it.mood,
                    it.moodName
                )
            })
            emit(ResponseState.Success(Unit))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun updateMemory(memory: Memory): Flow<ResponseState<Unit>> {
        return flow {
            emit(ResponseState.Loading)
            localSource.updateMemory(memory.mapModel {
                MemoryEntity(
                    it.id,
                    it.title,
                    it.content,
                    it.date,
                    it.image,
                    it.mood,
                    it.moodName
                )
            })
            emit(ResponseState.Success(Unit))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun getMemoryCount(): Flow<ResponseState<Int>> {
        return flow {
            emit(ResponseState.Loading)
            localSource.getMemoryCount()
            emit(ResponseState.Success(localSource.getMemoryCount()))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun getAllLocalMemories(): Flow<ResponseState<List<Memory>>> {
        return flow {
            emit(ResponseState.Loading)
            localSource.getAllMemories()
            emit(ResponseState.Success(localSource.getAllMemories().mapList { memoryEntity ->
                Memory(
                    memoryEntity.memoryId,
                    memoryEntity.memoryTitle,
                    memoryEntity.memoryContent,
                    memoryEntity.memoryDate,
                    memoryEntity.memoryImage,
                    memoryEntity.memoryMoodImage,
                    memoryEntity.memoryMoodName
                )
            }))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun addMemoryToLocal(memory: Memory): Flow<ResponseState<Unit>> {
        return flow {
            emit(ResponseState.Loading)
            localSource.addMemoryToLocal(memory.mapModel {
                MemoryEntity(
                    it.id,
                    it.title,
                    it.content,
                    it.date,
                    it.image,
                    it.mood,
                    it.moodName
                )
            })
            emit(ResponseState.Success(Unit))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    // Transfer
    override fun transferMemoriesToLocale(): Flow<ResponseState<Unit>> {
        return flow {
            emit(ResponseState.Loading)
            firebaseSource.getAllMemories()
            localSource.addAllMemoriesToLocal(firebaseSource.getAllMemories().mapList {
                MemoryEntity(
                    it.id,
                    it.title,
                    it.content,
                    it.date,
                    it.image,
                    it.mood,
                    it.moodName
                )

            })
            emit(ResponseState.Success(Unit))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }
}