package com.mertozan.membox

import com.mertozan.membox.core.ResponseState
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

}