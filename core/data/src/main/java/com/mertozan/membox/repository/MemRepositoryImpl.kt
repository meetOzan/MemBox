package com.mertozan.membox.repository

import android.content.Context
import android.net.Uri
import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.domain.repository.MemRepository
import com.mertozan.membox.domain.source.FirebaseSource
import com.mertozan.membox.domain.source.LocalSource
import com.mertozan.membox.model.Memory
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

    override fun getAllMemoriesList(): List<Memory> {
        return firebaseSource.getAllMemories()
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

    override fun getMemoryByTitle(id: String): Flow<ResponseState<Memory>> {
        return flow {
            emit(ResponseState.Loading)
            firebaseSource.getMemoryByTitle(id)
            emit(ResponseState.Success(firebaseSource.getMemoryByTitle(id)))
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

    override fun deleteMemoryFromLocal(memory: Memory): Flow<ResponseState<Unit>> {
        return flow {
            emit(ResponseState.Loading)
            localSource.deleteMemoryFromLocal(memory)
            emit(ResponseState.Success(Unit))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun deleteAllMemoriesFromLocal(): Flow<ResponseState<Unit>> {
        return flow {
            emit(ResponseState.Loading)
            localSource.deleteAllMemories()
            emit(ResponseState.Success(Unit))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun updateMemory(memory: Memory): Flow<ResponseState<Unit>> {
        return flow {
            emit(ResponseState.Loading)
            localSource.updateMemory(memory)
            emit(ResponseState.Success(Unit))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun getMemoryCount(): Int {
        return localSource.getMemoryCount()
    }

    override fun getAllLocalMemories(): Flow<ResponseState<List<Memory>>> {
        return flow {
            emit(ResponseState.Loading)
            localSource.getAllMemories()
            emit(ResponseState.Success(localSource.getAllMemories()))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun getLocalMoods(): Flow<ResponseState<Map<String, Float>>> {
        return flow {
            emit(ResponseState.Loading)
            localSource.getLocalMoods()
            emit(ResponseState.Success(localSource.getLocalMoods()))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun getMemoryByTitleFromLocal(title: String): Flow<ResponseState<Memory>> {
        return flow {
            emit(ResponseState.Loading)
            localSource.getMemoryByTitle(title)
            emit(
                ResponseState.Success(
                    localSource.getMemoryByTitle(title)
                )
            )
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun addMemoryToLocal(
        memory: Memory,
    ): Flow<ResponseState<Unit>> {
        return flow {
            emit(ResponseState.Loading)
            localSource.addMemoryToLocal(memory)
            emit(ResponseState.Success(Unit))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun addAllMemoriesToLocal(memoryList: List<Memory>) {
        localSource.addAllMemoriesToLocal(memoryList)
    }

    // Transfer
    override fun transferMemoriesToLocal(memoryList: List<Memory>) {
        addAllMemoriesToLocal(memoryList)
    }
}
