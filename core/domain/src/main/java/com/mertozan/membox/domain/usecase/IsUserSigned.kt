package com.mertozan.membox.domain.usecase

import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsUserSigned @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(): Flow<ResponseState<Boolean>> {
        return userRepository.isUserSignedIn()
    }
}