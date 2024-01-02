package com.mertozan.membox.domain

import com.mertozan.membox.MemRepository
import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.network.dto.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val memRepository: MemRepository
) {
    operator fun invoke(user: User, onNavigate: () -> Unit): Flow<ResponseState<Unit>> {
        return memRepository.signUpWithEmailAndPassword(user, onNavigate)
    }
}