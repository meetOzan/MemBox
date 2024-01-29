package com.mertozan.membox.repository

import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.core.mapper.mapModel
import com.mertozan.membox.source.local.LocalSource
import com.mertozan.membox.source.local.entity.UserEntity
import com.mertozan.membox.source.network.FirebaseSource
import com.mertozan.membox.source.network.dto.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseSource: FirebaseSource,
    private val localSource: LocalSource,
) : UserRepository {

    override fun signInUserWithEmailAndPassword(
        user: User,
        onNavigate: () -> Unit,
    ): Flow<ResponseState<Unit>> {
        return flow {
            emit(ResponseState.Loading)
            firebaseSource.signInWithEmailAndPassword(user) {
                onNavigate()
            }
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
            firebaseSource.signUpUserWithEmailAndPassword(user) {
                onNavigate()
            }
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

    override fun getUserNetwork(): Flow<ResponseState<User>> {
        return flow {
            emit(ResponseState.Loading)
            firebaseSource.getUserNetwork()
            emit(ResponseState.Success(firebaseSource.getUserNetwork()))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun addUserToLocal(user: User) {
        localSource.addUserToLocal(user.mapModel {
            UserEntity(
                userId = 1,
                userName = it.name,
                userSurname = it.surname,
                userEmail = it.email,
                userPassword = it.password,
            )
        })
    }

    override fun getLocalUser(): Flow<ResponseState<User?>> {
        return flow {
            emit(ResponseState.Loading)
            localSource.getUser()
            emit(ResponseState.Success(localSource.getUser().mapModel { userEntity ->
                userEntity?.let {
                    User(
                        name = it.userName,
                        surname = it.userSurname,
                        email = it.userEmail,
                        password = it.userPassword,
                    )
                }
            }))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun deleteUserFromLocal(): Flow<ResponseState<Unit>> {
        return flow {
            emit(ResponseState.Loading)
            localSource.deleteUserFromLocal()
            emit(ResponseState.Success(localSource.deleteAllMemories()))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun updateUser(userEntity: User) {
        localSource.updateUser(userEntity.mapModel {
            UserEntity(
                userId = 1,
                userName = it.name,
                userSurname = it.surname,
                userEmail = it.email,
                userPassword = it.password,
            )
        })
    }

    override fun transferUserToLocal(user: User): Flow<ResponseState<Unit>> {
        return flow {
            emit(ResponseState.Loading)
            addUserToLocal(user)
            emit(ResponseState.Success(Unit))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }
}