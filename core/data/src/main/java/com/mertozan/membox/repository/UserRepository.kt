package com.mertozan.membox.repository

import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.source.network.dto.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    // Network
    fun signInUserWithEmailAndPassword(
        user: User,
        onNavigate: () -> Unit,
    ): Flow<ResponseState<Unit>>

    fun signUpWithEmailAndPassword(
        user: User,
        onNavigate: () -> Unit,
    ): Flow<ResponseState<Unit>>

    fun signOut(): Flow<ResponseState<Unit>>

    fun isUserSignedIn(): Flow<ResponseState<Boolean>>

    fun getUserNetwork(): Flow<ResponseState<User>>

    // Local
    suspend fun addUserToLocal(user: User)

    fun getLocalUser(): Flow<ResponseState<User?>>

    fun deleteUserFromLocal(): Flow<ResponseState<Unit>>

    suspend fun updateUser(userEntity: User)

    // Transfer
    fun transferUserToLocal(user: User): Flow<ResponseState<Unit>>

}