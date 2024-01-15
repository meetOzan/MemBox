package com.mertozan.membox.domain

import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.repository.UserRepository
import com.mertozan.membox.source.network.dto.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(
        user: User,
        onNavigate: () -> Unit,
    ): Flow<ResponseState<Unit>> {
        return userRepository.signUpWithEmailAndPassword(user, onNavigate)
    }
}