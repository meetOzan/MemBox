package com.mertozan.membox.domain

import com.mertozan.membox.MemRepository
import com.mertozan.membox.core.ResponseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val memRepository: MemRepository,
) {
    operator fun invoke(
        user: com.mertozan.membox.source.dto.User,
        onNavigate: () -> Unit,
    ): Flow<ResponseState<Unit>> {
        return memRepository.signInUserWithEmailAndPassword(user, onNavigate)
    }
}