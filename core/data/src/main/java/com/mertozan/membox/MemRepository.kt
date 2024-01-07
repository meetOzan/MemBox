package com.mertozan.membox

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

}