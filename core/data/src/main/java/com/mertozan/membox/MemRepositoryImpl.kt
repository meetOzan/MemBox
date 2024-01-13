package com.mertozan.membox

import android.content.Context
import android.net.Uri
import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.model.Memory
import com.mertozan.membox.network.dto.User
import com.mertozan.membox.network.firestore.FirebaseSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MemRepositoryImpl @Inject constructor(
    private val firebaseSource: FirebaseSource,
) : MemRepository {

    override fun signInUserWithEmailAndPassword(
        user: User,
        onNavigate: () -> Unit,
    ): Flow<ResponseState<Unit>> {
        return flow {
            emit(ResponseState.Loading)
            firebaseSource.signInWithEmailAndPassword(user, onNavigate)
            emit(ResponseState.Success(Unit))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }

    }

    override fun signUpWithEmailAndPassword(
        user: User,
        onNavigate: () -> Unit,
    ): Flow<ResponseState<Unit>> {
        return flow {
            emit(ResponseState.Loading)
            firebaseSource.signUpUserWithEmailAndPassword(user, onNavigate)
            emit(ResponseState.Success(Unit))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun signOut(): Flow<ResponseState<Unit>> {
        return flow {
            emit(ResponseState.Loading)
            firebaseSource.signOut()
            emit(ResponseState.Success(Unit))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun isUserSignedIn(): Flow<ResponseState<Boolean>> {
        return flow {
            emit(ResponseState.Loading)
            firebaseSource.isUserSignedIn()
            emit(ResponseState.Success(firebaseSource.isUserSignedIn()))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

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
}