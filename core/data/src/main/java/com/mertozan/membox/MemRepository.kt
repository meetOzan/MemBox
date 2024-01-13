package com.mertozan.membox

import android.content.Context
import android.net.Uri
import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.model.Memory
import com.mertozan.membox.network.dto.User
import kotlinx.coroutines.flow.Flow

interface MemRepository {

    fun signInUserWithEmailAndPassword(
        user: User,
        onNavigate: () -> Unit,
    ): Flow<ResponseState<Unit>>

    fun signUpWithEmailAndPassword(user: User, onNavigate: () -> Unit): Flow<ResponseState<Unit>>

    fun signOut(): Flow<ResponseState<Unit>>

    fun isUserSignedIn(): Flow<ResponseState<Boolean>>

    fun addMemory(memory: Memory, onNavigate: () -> Unit) : Flow<ResponseState<Unit>>

    fun getAllMemories() : Flow<ResponseState<List<Memory>>>

    fun getMemoryByDate(date: String) : Flow<ResponseState<List<Memory>>>

    fun uploadImageStorage(
        uri: Uri,
        context: Context,
        onSuccess: (String, String) -> Unit = { _, _ -> },
        onFailure: (String) -> Unit = { _ -> },
    ): Flow<ResponseState<Unit>>

    fun uploadImageToFirestore(
        imagesUrl: List<String>,
        imageName: String,
        onSuccess: () -> Unit = {},
        onFailure: (String) -> Unit = { _ -> },
    ): Flow<ResponseState<Unit>>

}