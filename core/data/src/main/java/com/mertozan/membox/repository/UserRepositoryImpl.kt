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

    override suspend fun addUserToLocal(user: User) {
        localSource.addUserToLocal(user.mapModel {
            UserEntity(
                userId = it.id,
                userName = it.name,
                userSurname = it.surname,
                userEmail = it.email,
                userPassword = it.password,
            )
        })
    }

    override fun getLocalUser(): Flow<ResponseState<User>> {
        return flow {
            emit(ResponseState.Loading)
            localSource.getUser()
            emit(ResponseState.Success(localSource.getUser().mapModel {
                User(
                    id = it.userId,
                    name = it.userName,
                    surname = it.userSurname,
                    email = it.userEmail,
                    password = it.userPassword,
                )
            }))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun deleteUserFromLocal(userEntity: User) {
        localSource.deleteUserFromLocal(userEntity.mapModel {
            UserEntity(
                userId = it.id,
                userName = it.name,
                userSurname = it.surname,
                userEmail = it.email,
                userPassword = it.password,
            )
        })
    }

    override suspend fun updateUser(userEntity: User) {
        localSource.updateUser(userEntity.mapModel {
            UserEntity(
                userId = it.id,
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
            localSource.transferUserToLocal(user.mapModel {
                UserEntity(
                    userId = it.id,
                    userName = it.name,
                    userSurname = it.surname,
                    userEmail = it.email,
                    userPassword = it.password,
                )
            })
            emit(ResponseState.Success(Unit))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }
}